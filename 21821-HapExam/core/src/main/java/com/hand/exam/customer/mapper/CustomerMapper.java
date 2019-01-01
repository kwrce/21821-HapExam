package com.hand.exam.customer.mapper;

import com.hand.exam.companys.dto.Companys;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.exam.customer.dto.Customer;

import java.util.List;

public interface CustomerMapper extends Mapper<Customer>{
    List<Customer> selectLOV(Customer customer);
}