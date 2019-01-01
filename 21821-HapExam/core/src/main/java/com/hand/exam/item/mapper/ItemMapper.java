package com.hand.exam.item.mapper;

import com.hand.exam.customer.dto.Customer;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.exam.item.dto.Item;

import java.util.List;

public interface ItemMapper extends Mapper<Item>{
    List<Item> selectLOV(Item item);
}