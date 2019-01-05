package com.hand.exam.censu.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.exam.censu.mapper.CensuMapper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.DTOStatus;
import com.hand.hap.system.service.IBaseService;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.exam.censu.dto.Censu;
import com.hand.exam.censu.service.ICensuService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
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
    public List<Censu> selectByCensuDetail(IRequest requestContext, Censu censu, int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return censuMapper.selectByCensuDetail(censu);
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

    @Override
    public int addCensu(IRequest iRequest, Censu censu) {
        censu.setLastUpdateDate(new Date());
        int row =censuMapper.addOneCensu(censu);
        return row;
    }

    @Override
    public int updateCensu(IRequest iRequest, Censu censu) {
        censu.setLastUpdateDate(new Date());
        int row =censuMapper.updateOneCensu(censu);
        return row;
    }

    @Override
    public int deleteCensu(IRequest iRequest, Censu censu) {
        int row =censuMapper.deleteOneCensu(censu);
        return row;
    }


}