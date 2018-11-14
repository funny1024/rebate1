package com.cmsy.rebate.service.impl;

import com.cmsy.rebate.entity.Account;
import com.cmsy.rebate.mapper.AccountMapper;
import com.cmsy.rebate.service.IAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kaka_fun
 * @since 2018-11-13
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
