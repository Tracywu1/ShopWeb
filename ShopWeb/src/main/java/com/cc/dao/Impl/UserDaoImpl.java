package com.cc.dao.Impl;

import com.cc.dao.UserDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.User;
import com.cc.utils.CRUDUtils;
import com.cc.utils.RandomUsernameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void insertSelective(User user) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_user");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (user.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (user.getStoreId() != null) {
            columnsBuilder.append("`storeId`,");
            valuesBuilder.append("?,");
        }
        if (user.getUsername() != null) {
            columnsBuilder.append("`username`,");
            valuesBuilder.append("?,");
        }
        if (user.getNickname() != null) {
            columnsBuilder.append("`nickname`,");
            valuesBuilder.append("?,");
        }
        if (user.getPassword() != null) {
            columnsBuilder.append("`password`,");
            valuesBuilder.append("md5(?),");
        }
        if (user.getAddress() != null) {
            columnsBuilder.append("`address`,");
            valuesBuilder.append("?,");
        }
        if (user.getEmail() != null) {
            columnsBuilder.append("`email`,");
            valuesBuilder.append("?,");
        }
        if (user.getPhoneNum() != null) {
            columnsBuilder.append("`phoneNum`,");
            valuesBuilder.append("?,");
        }
        if (user.getImage() != null) {
            columnsBuilder.append("`image`,");
            valuesBuilder.append("?,");
        }
        if (user.getUserRole() != null) {
            columnsBuilder.append("`userRole`,");
            valuesBuilder.append("?,");
        }
        if (user.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
            valuesBuilder.append("?,");
        }
        if (user.getUpdateTime() != null) {
            columnsBuilder.append("`updateTime`,");
            valuesBuilder.append("?,");
        }

        //删掉最后一个逗号
        columnsBuilder.deleteCharAt(columnsBuilder.length() - 1);
        valuesBuilder.deleteCharAt(valuesBuilder.length() - 1);

        columnsBuilder.append(")");
        valuesBuilder.append(")");

        sqlBuilder.append(columnsBuilder);
        sqlBuilder.append(" ");
        sqlBuilder.append("values");
        sqlBuilder.append(" ");
        sqlBuilder.append(valuesBuilder);

        int count = 0;

        if (user.getId() != null) {
            count++;
        }
        if (user.getStoreId() != null) {
            count++;
        }
        if (user.getNickname() != null && !user.getNickname().isEmpty()) {
            count++;
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            count++;
        }
        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
            count++;
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            count++;
        }
        if (user.getPhoneNum() != null && !user.getPhoneNum().isEmpty()) {
            count++;
        }
        if (user.getImage() != null && !user.getImage().isEmpty()) {
            count++;
        }
        if (user.getUserRole() != null) {
            count++;
        }
        if (user.getCreateTime() != null) {
            count++;
        }
        if (user.getUpdateTime() != null) {
            count++;
        }

        Object[] params = new Object[count + 1];

        int index = 0;

        if (user.getId() != null) {
            params[index] = user.getId();
            index++;
        }
        if (user.getStoreId() != null) {
            params[index] = user.getStoreId();
            index++;
        }

        String username;
        do {
            // 生成随机用户名（保证其不重复）
            username = RandomUsernameGenerator.generate();
        } while (selectByUsername(username) != null);
        params[index] = username;

        if (user.getNickname() != null && !user.getNickname().isEmpty()) {
            params[index] = user.getNickname();
            index++;
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            params[index] = user.getPassword();
            index++;
        }
        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
            params[index] = user.getAddress();
            index++;
        }
        if (user.getEmail() != null && user.getEmail().isEmpty()) {
            params[index] = user.getEmail();
            index++;
        }
        if (user.getPhoneNum() != null && user.getPhoneNum().isEmpty()) {
            params[index] = user.getPhoneNum();
            index++;
        }
        if (user.getImage() != null && !user.getImage().isEmpty()) {
            params[index] = user.getImage();
            index++;
        }
        if (user.getUserRole() != null) {
            params[index] = user.getUserRole();
            index++;
        }
        if (user.getCreateTime() != null) {
            params[index] = user.getCreateTime();
            index++;
        }
        if (user.getUpdateTime() != null) {
            params[index] = user.getUpdateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);

        if (update == 0) {
            throw new MyException(ResultCode.INSERT_FAILED);
        }

        logger.debug("update:" + update);
    }

    @Override
    public User selectById(Integer id) throws Exception {
        String sql = "select * from tb_user where id =?";
        User user = CRUDUtils.query(sql, User.class, id);
        logger.debug(String.valueOf(user));
        return user;
    }

    @Override
    public User selectByUsername(String username) throws Exception {
        String sql = "select * from tb_user where username =?";
        User user = CRUDUtils.query(sql, User.class, username);
        logger.debug(String.valueOf(user));
        return user;
    }

    @Override
    public User selectByUsernameAndPwd(String username, String password) throws Exception {
        Object[] params = {username, password};
        String sql = "select * from tb_user where username = ? and password = md5(?)";
        User user1 = CRUDUtils.query(sql, User.class, params);
        logger.debug(String.valueOf(user1));
        return user1;
    }

    @Override
    public void updateByIdSelective(User user) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("update tb_user");
        sqlBuilder.append(" ");
        sqlBuilder.append("set");

        if (user.getUsername() != null) {
            sqlBuilder.append("`username` = ?,");
        }
        if (user.getNickname() != null) {
            sqlBuilder.append("`nickname` = ?,");
        }
        if (user.getPassword() != null) {
            sqlBuilder.append("`password` = ?,");
        }
        if (user.getAddress() != null) {
            sqlBuilder.append("`address` = ?,");
        }
        if (user.getEmail() != null) {
            sqlBuilder.append("`email` = ?,");
        }
        if (user.getCreateTime() != null) {
            sqlBuilder.append("`createTime` = ?,");
        }
        if (user.getUpdateTime() != null) {
            sqlBuilder.append("`updateTime` = ?,");
        }

        // 删除最后一个逗号
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" ");
        sqlBuilder.append("where id = ?");

        Object[] params;
        int count = 0;

        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            count++;
        }
        if (user.getNickname() != null && !user.getNickname().isEmpty()) {
            count++;
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            count++;
        }
        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
            count++;
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            count++;
        }
        if (user.getPhoneNum() != null && !user.getPhoneNum().isEmpty()) {
            count++;
        }
        if (user.getImage() != null && !user.getImage().isEmpty()) {
            count++;
        }
        if (user.getUserRole() != null) {
            count++;
        }

        params = new Object[count + 1];

        int index = 0;

        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            params[index] = user.getUsername();
            index++;
        }
        if (user.getNickname() != null && !user.getNickname().isEmpty()) {
            params[index] = user.getNickname();
            index++;
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            params[index] = user.getPassword();
            index++;
        }
        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
            params[index] = user.getAddress();
            index++;
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            params[index] = user.getEmail();
            index++;
        }
        if (user.getPhoneNum() != null && !user.getPhoneNum().isEmpty()) {
            params[index] = user.getPhoneNum();
            index++;
        }
        if (user.getImage() != null && !user.getImage().isEmpty()) {
            params[index] = user.getImage();
            index++;
        }
        if (user.getUserRole() != null) {
            params[index] = user.getUserRole();
        }

        params[params.length - 1] = user.getId();

        int update = CRUDUtils.update(sqlBuilder.toString(), params);
        logger.debug("update:" + update);

        if (update == 0) {
            throw new MyException(ResultCode.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from tb_user where id =?";
        int update = CRUDUtils.update(sql, id);

        if (update == 0) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }

        logger.debug("update:" + update);
    }

}
