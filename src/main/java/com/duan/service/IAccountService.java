package com.duan.service;


import com.duan.pojo.Account;

import java.util.List;

/**
 * 账户的业务层接口
 * */
public interface IAccountService {

    /**
     * 查询所有
     * */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * */
    void saveAccount(Account account);

    /**
     * 更新
     * */
    void updateAccount(Account account);

    /**
     * 删除
     * */
    void deleteAccount(Integer accountId);

    /**
     * 转账
     * @Param sourceName：转出账户名称
     * @Param targetName：转入账户名称
     * @Param money：转账金额
     * */
    void transfer(String sourceName,String targetName,Float money);
}
















