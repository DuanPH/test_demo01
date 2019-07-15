package com.duan.utils;


/**
 * 和事务管理相关的工具类：
 *              1.开启事务
 *              2.提交事务
 *              3.回滚事务
 *              4.释放事务
 * */
public class TransactionManager {

    private ConnectionUtils connectionUtils;


    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }


    /**
     * 开启事务
     * */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     * */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     * */
    public void rollBack(){
        try {
            connectionUtils.getThreadConnection().rollback();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 释放事务
     * */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();  //还回了连接池中
            connectionUtils.removeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
