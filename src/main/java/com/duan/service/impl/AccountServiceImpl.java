package com.duan.service.impl;


import com.duan.dao.IAccountDao;
import com.duan.pojo.Account;
import com.duan.service.IAccountService;
import com.duan.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类(业务层应该调用持久层)
 *
 * 事物控制应该都是在业务层的
 * */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao iAccountDao;
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setiAccountDao(IAccountDao iAccountDao) {
        this.iAccountDao = iAccountDao;
    }



    /**
     * 以下几种方法是账户的基本操作方法,并加入事务控制
     * */
    /**
     * 查询全部
     * */
    @Override
    public List<Account> findAllAccount() {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行事务
            List<Account> accounts = iAccountDao.findAllAccount();
            //3.提交事务
            transactionManager.commit();
            //4.返回结果
            return accounts;
        }catch(Exception e){
            //5.回滚操作
            transactionManager.rollBack();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            transactionManager.release();
        }
    }

    /**
     * 根据id查询
     * */
    @Override
    public Account findAccountById(Integer accountId) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行事务
            Account account = iAccountDao.findAccountById(accountId);
            //3.提交事务
            transactionManager.commit();
            //4.返回结果
            return account;
        }catch(Exception e){
            //5.回滚操作
            transactionManager.rollBack();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            transactionManager.release();
        }
    }

    /**
     * 保存
     * */
    @Override
    public void saveAccount(Account account) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行事务
            iAccountDao.saveAccount(account);
            //3.提交事务
            transactionManager.commit();
        }catch(Exception e){
            //4.回滚操作
            transactionManager.rollBack();
        }finally {
            //5.释放连接
            transactionManager.release();
        }
    }

    /**
     * 更新
     * */
    @Override
    public void updateAccount(Account account) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行事务
            iAccountDao.updateAccount(account);
            //3.提交事务
            transactionManager.commit();
        }catch(Exception e){
            //4.回滚操作
            transactionManager.rollBack();
        }finally {
            //5.释放连接
            transactionManager.release();
        }
    }

    /**
     * 删除
     * */
    @Override
    public void deleteAccount(Integer accountId) {
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行事务
            iAccountDao.deleteAccount(accountId);
            //3.提交事务
            transactionManager.commit();
        }catch(Exception e){
            //4.回滚操作
            transactionManager.rollBack();
        }finally {
            //5.释放连接
            transactionManager.release();
        }
    }


    /**
     * 转账入账
     * */
    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        /*加入事务控制*/
        try{
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行事务
                    //2-1.根据名称查询转出账户
                    Account source = iAccountDao.findAccountByName(sourceName);
                    //2-2.根据名称查询转入账户
                    Account target = iAccountDao.findAccountByName(targetName);
                    //2-3.转出账户减钱
                    source.setMoney(source.getMoney()-money);
                    //2-4.转入账户加钱
                    target.setMoney(target.getMoney()+money);
                    //2-5.更新转出账户
                    iAccountDao.updateAccount(source);
                    //2-6.更新转入账户
                    iAccountDao.updateAccount(target);
            //3.提交事务
            transactionManager.commit();
        }catch(Exception e){
            //4.回滚操作
            transactionManager.rollBack();
        }finally {
            //5.释放连接
            transactionManager.release();
        }
    }
}
