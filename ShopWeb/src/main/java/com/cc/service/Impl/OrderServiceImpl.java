package com.cc.service.Impl;

import com.cc.common.Constants;
import com.cc.dao.CartDao;
import com.cc.dao.Impl.CartDaoImpl;
import com.cc.dao.Impl.OderItemDaoImpl;
import com.cc.dao.Impl.OrderDaoImpl;
import com.cc.dao.Impl.ProductDaoImpl;
import com.cc.dao.OrderDao;
import com.cc.dao.OrderItemDao;
import com.cc.dao.ProductDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.filter.LoginCheckFilter;
import com.cc.po.OrderItem;
import com.cc.po.PageBean;
import com.cc.po.Order;
import com.cc.po.Product;
import com.cc.service.CartService;
import com.cc.service.OrderService;
import com.cc.service.UserService;
import com.cc.utils.OrderNoFactory;
import com.cc.vo.CartVO;
import com.cc.vo.OrderItemVO;
import com.cc.vo.OrderVO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OderItemDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private CartDao cartDao = new CartDaoImpl();
    private CartService cartService = new CartServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public List<Order> getAll() throws Exception {
        return orderDao.selectAll();
    }

    @Override
    public String create() throws Exception {
        //拿到用户ID
        Integer userId = LoginCheckFilter.currentUser.getId();

        //从购物车查找已经勾选的商品
        List<CartVO> cartVOList = cartService.list(userId);
        ArrayList<CartVO> cartVOListTemp = new ArrayList<>();
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            if (cartVO.getIsSelected().equals(Constants.IsSelected.SELECTED)) {
                cartVOListTemp.add(cartVO);
            }
        }
        cartVOList = cartVOListTemp;
        //如果购物车已勾选的为空，报错
        if (cartVOList.size() == 0) {
            throw new MyException(ResultCode.CART_EMPTY);
        }
        //判断商品是否存在、库存
        validSaleStatusAndStock(cartVOList);
        //把购物车对象转为订单item对象
        List<OrderItem> orderItemList = cartVOListToOrderItemList(cartVOList);
        //扣库存
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            Product product = productDao.select(orderItem.getProductId());
            int stock = product.getProductCount() - orderItem.getCount();
            if (stock < 0) {
                throw new MyException(ResultCode.NOT_ENOUGH);
            }
            product.setProductCount(stock);
            productDao.updateByIdSelective(product);
        }
        //把购物车中的已勾选商品删除
        cleanCart(cartVOList);
        //生成订单
        Order order = new Order();
        //生成订单号，有独立的规则
        String orderNo = OrderNoFactory.getOrderCode(Long.valueOf(userId));
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setTotalPrice(totalPrice(orderItemList));
        order.setStatus(Constants.OrderStatus.NOT_SHIPPED.getNum());
        //插入到Order表
        orderDao.insertSelective(order);

        //循环保存每个商品到order_item表
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            orderItem.setOrderNo(order.getOrderNo());
            orderItemDao.insertSelective(orderItem);
        }
        //把结果返回
        return orderNo;
    }

    private void validSaleStatusAndStock(List<CartVO> cartVOList) throws Exception {
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            Product product = productDao.select(cartVO.getProductId());
            //判断商品是否存在，商品是否上架
            if (product == null) {
                throw new MyException(ResultCode.NOT_SALE);
            }
            //判断商品库存
            if (cartVO.getCount() > product.getProductCount()) {
                throw new MyException(ResultCode.NOT_ENOUGH);
            }
        }
    }

    private List<OrderVO> orderListToOrderVOList(List<Order> orderList) throws Exception {
        List<OrderVO> orderVOList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            OrderVO orderVO = getOrderVO(order);
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }

    private OrderVO getOrderVO(Order order) throws Exception {
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderNo(order.getOrderNo());
        orderVO.setUserId(order.getUserId());
        orderVO.setTotalPrice(order.getTotalPrice());
        orderVO.setCreateTime(order.getCreateTime());
        orderVO.setUpdateTime(order.getUpdateTime());
        orderVO.setStatus(order.getStatus());

        //获取订单对应的orderItemVOList
        List<OrderItem> orderItemList = orderItemDao.selectByOrderNo(order.getOrderNo());
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            OrderItemVO orderItemVO = new OrderItemVO();
            orderItemVO.setOrderNo(orderItem.getOrderNo());
            orderItemVO.setProductId(orderItem.getProductId());
            orderItemVO.setProductName(orderItem.getProductName());
            orderItemVO.setProductImg(orderItem.getProductImage());
            orderItemVO.setUnitPrice(orderItem.getUnitPrice());
            orderItemVO.setCount(orderItem.getCount());
            orderItemVO.setTotalPrice(orderItem.getTotalPrice());
            orderItemVOList.add(orderItemVO);
        }
        orderVO.setOrderItemVOList(orderItemVOList);
        orderVO.setOrderStatusName(Constants.OrderStatus.fromValue(orderVO.getStatus()));
        return orderVO;
    }

    private BigDecimal totalPrice(List<OrderItem> orderItemList) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            totalPrice = totalPrice.add(orderItem.getTotalPrice());
        }
        return totalPrice;
    }

    private List<OrderItem> cartVOListToOrderItemList(List<CartVO> cartVOList) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartVO.getProductId());
            //记录商品快照信息
            orderItem.setProductName(cartVO.getProductName());
            orderItem.setProductImage(cartVO.getProductImage());
            orderItem.setUnitPrice(cartVO.getPrice());
            orderItem.setCount(cartVO.getCount());
            orderItem.setTotalPrice(cartVO.getTotalPrice());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    private void cleanCart(List<CartVO> cartVOList) throws Exception {
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            cartDao.delete(cartVO.getId());
        }
    }

    @Override
    public void delete(int id) throws Exception {
        Order orderOld = orderDao.select(id);

        //查询不到该数据，无法删除
        if (orderOld == null) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }

        orderDao.delete(id);
    }

    @Override
    public void update(Order updateOrder) throws Exception {
        orderDao.updateByIdSelective(updateOrder);
    }

    @Override
    public PageBean<Order> selectByPage(int currentPage, int pageSize) throws Exception {
        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 查询当前页数据
        List<Order> rows = orderDao.selectByPage(begin, size);

        //查询总记录数
        int totalCount = orderDao.selectTotalCount();

        //封装PageBean对象
        PageBean<Order> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    @Override
    public PageBean<Order> selectByPageAndCondition(int currentPage, int pageSize, String orderNo) throws Exception {
        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 查询当前页数据
        List<Order> rows = orderDao.selectByPageAndCondition(begin, size, orderNo);

        // 查询总记录数
        int totalCount = orderDao.selectTotalCountByCondition(orderNo);

        // 封装PageBean对象
        PageBean<Order> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    @Override
    public void deliver(String orderNo) throws Exception {
        Order order = orderDao.selectByOrderNo(orderNo);
        //查询不到订单，报错
        if (order == null) {
            throw new MyException(ResultCode.NO_ORDER);
        }
        if (order.getStatus() == Constants.OrderStatus.NOT_SHIPPED.getNum()) {
            order.setStatus(Constants.OrderStatus.DELIVERED.getNum());
            orderDao.updateByIdSelective(order);
        } else {
            throw new MyException(ResultCode.WRONG_ORDER_STATUS);
        }

    }

    @Override
    public void pay(String orderNo) throws Exception {
        Order order = orderDao.selectByOrderNo(orderNo);
        //查不到订单，报错
        if (order == null) {
            throw new MyException(ResultCode.NO_ORDER);
        }
        if (order.getStatus() == Constants.OrderStatus.NOT_PAID.getNum()) {
            order.setStatus(Constants.OrderStatus.NOT_SHIPPED.getNum());
            orderDao.updateByIdSelective(order);
        } else {
            throw new MyException(ResultCode.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public void finish(String orderNo) throws Exception {
        Order order = orderDao.selectByOrderNo(orderNo);
        //查不到订单，报错
        if (order == null) {
            throw new MyException(ResultCode.NO_ORDER);
        }
        //普通用户无法完结订单
        if(!userService.checkManager(LoginCheckFilter.currentUser)){
            throw new MyException(ResultCode.NOT_YOUR_ORDER);
        }
        //发货后可以完结订单
        if (order.getStatus() == Constants.OrderStatus.DELIVERED.getNum()) {
            order.setStatus(Constants.OrderStatus.AFTER_SALES_SERVICE.getNum());
            orderDao.updateByIdSelective(order);
        } else {
            throw new MyException(ResultCode.WRONG_ORDER_STATUS);
        }
    }


}
