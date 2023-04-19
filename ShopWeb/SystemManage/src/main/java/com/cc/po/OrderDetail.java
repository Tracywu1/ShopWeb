package com.cc.po;

public class OrderDetail {
    /**
     * 订单详情ID
     */
    private Integer id;
    /**
     * 商品
     */
    private Product product;
    /**
     * 店铺
     */
    private Store store;
    /**
     * 订单
     */
    private Order order;
    /**
     * 商品单价
     */
    private Double price;
    /**
     * 商品数量
     */
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", product=" + product +
                ", store=" + store +
                ", order=" + order +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
