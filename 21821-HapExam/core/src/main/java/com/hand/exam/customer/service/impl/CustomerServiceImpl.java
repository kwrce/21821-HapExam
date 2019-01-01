package com.hand.exam.customer.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.hand.exam.customer.dto.Customer;
import com.hand.exam.customer.service.ICustomerService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements ICustomerService{

}