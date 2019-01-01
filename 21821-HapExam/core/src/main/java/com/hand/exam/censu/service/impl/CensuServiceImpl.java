package com.hand.exam.censu.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.exam.censu.mapper.CensuMapper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.DTOStatus;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.exam.censu.dto.Censu;
import com.hand.exam.censu.service.ICensuService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CensuServiceImpl extends BaseServiceImpl<Censu> implements ICensuService{
    @Autowired
    private CensuMapper censuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor =
            Exception.class)
    public List<Censu> selectByCensu(IRequest requestContext, Censu censu, int
            page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return censuMapper.selectByCensu(censu);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Censu> batchUpdate(IRequest requestContext, List<Censu> census){
        for (Censu censu : census) {
            if (censu.get__status() != null) {
                switch (censu.get__status()) {
                    case DTOStatus.ADD:
                        censuMapper.insertCensu(censu);
                        break;
                    case DTOStatus.UPDATE:
                        censuMapper.updateCensu(censu);
                        break;
                    default:
                        break;
                }
            }
        }
        return census;
    }
}