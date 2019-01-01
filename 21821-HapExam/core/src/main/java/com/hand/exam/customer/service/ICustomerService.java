package com.hand.exam.customer.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.exam.customer.dto.Customer;

public interface ICustomerService extends IBaseService<Customer>, ProxySelf<ICustomerService>{

}