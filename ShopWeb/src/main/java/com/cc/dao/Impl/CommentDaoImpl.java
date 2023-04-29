package com.cc.dao.Impl;

import com.cc.dao.CommentDao;
import com.cc.exception.MyException;
import com.cc.exception.ResultCode;
import com.cc.po.Comment;
import com.cc.utils.CRUDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private static final Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class);

    @Override
    public List<Comment> selectByProductId(Integer productId) throws Exception {
        String sql ="select * from tb_comment where productId = ?";
        List<Comment> commentList = CRUDUtils.queryMore(sql,Comment.class,productId);
        logger.debug("commentList:"+commentList);
        return commentList;
    }

    @Override
    public void delete(Integer id) throws Exception {
        String sql ="delete from tb_comment where id = ?";
        int update = CRUDUtils.update(sql,id);

        if (update == 0) {
            throw new MyException(ResultCode.DELETE_FAILED);
        }
        
        logger.debug("update:"+update);
    }

    @Override
    public void insertSelective(Comment comment) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder("insert into tb_comment");
        StringBuilder columnsBuilder = new StringBuilder("(");
        StringBuilder valuesBuilder = new StringBuilder("(");
        if (comment.getId() != null) {
            columnsBuilder.append("`id`,");
            valuesBuilder.append("?,");
        }
        if (comment.getCreatorId() != null) {
            columnsBuilder.append("`creatorId`,");
            valuesBuilder.append("?,");
        }
        if (comment.getProductId() != null) {
            columnsBuilder.append("`productId`,");
            valuesBuilder.append("?,");
        }
        if(comment.getNickName()!= null){
            columnsBuilder.append("`nickName`,");
            valuesBuilder.append("?,");
        }
        if (comment.getImage()!=null){
            columnsBuilder.append("`image`,");
            valuesBuilder.append("?,");
        }
        if (comment.getContent() != null) {
            columnsBuilder.append("`content`,");
            valuesBuilder.append("?,");
        }
        if (comment.getCreateTime() != null) {
            columnsBuilder.append("`createTime`,");
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

        if (comment.getId() != null) {
            count++;
        }
        if (comment.getCreatorId() != null) {
            count++;
        }
        if (comment.getProductId() != null) {
            count++;
        }
        if (comment.getNickName() != null &&!comment.getNickName().isEmpty()){
            count++;
        }
        if (comment.getImage() != null && !comment.getImage().isEmpty()){
            count++;
        }
        if (comment.getContent() != null && !comment.getContent().isEmpty()) {
            count++;
        }
        if (comment.getCreateTime() != null) {
            count++;
        }

        Object[] params = new Object[count + 1];

        int index = 0;

        if (comment.getId() != null) {
            params[index] = comment.getId();
            index++;
        }
        if (comment.getCreatorId() != null) {
            params[index] = comment.getCreatorId();
            index++;
        }
        if (comment.getProductId() != null) {
            params[index] = comment.getProductId();
            index++;
        }
        if (comment.getNickName() != null && !comment.getNickName().isEmpty()){
            params[index] = comment.getNickName();
            index++;
        }
        if (comment.getImage() != null && !comment.getImage().isEmpty()){
            params[index] = comment.getImage();
            index++;
        }
        if (comment.getContent() != null && !comment.getContent().isEmpty()) {
            params[index] = comment.getContent();
            index++;
        }
        if (comment.getCreateTime() != null) {
            params[index] = comment.getCreateTime();
        }

        int update = CRUDUtils.update(sqlBuilder.toString(), params);

        if (update == 0) {
            throw new MyException(ResultCode.INSERT_FAILED);
        }

        logger.debug("update:" + update);
    }
}
