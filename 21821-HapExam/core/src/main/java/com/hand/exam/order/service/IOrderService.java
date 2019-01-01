package com.hand.exam.order.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.exam.order.dto.Order;

public interface IOrderService extends IBaseService<Order>, ProxySelf<IOrderService>{

}