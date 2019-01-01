package com.hand.exam.header.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.hand.exam.header.dto.Header;
import com.hand.exam.header.service.IHeaderService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HeaderServiceImpl extends BaseServiceImpl<Header> implements IHeaderService{

}