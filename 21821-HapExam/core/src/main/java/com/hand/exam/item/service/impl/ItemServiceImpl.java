package com.hand.exam.item.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.hand.exam.item.dto.Item;
import com.hand.exam.item.service.IItemService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ItemServiceImpl extends BaseServiceImpl<Item> implements IItemService{

}