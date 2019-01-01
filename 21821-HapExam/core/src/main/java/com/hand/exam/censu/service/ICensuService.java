package com.hand.exam.censu.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.service.IBaseService;
import com.hand.exam.censu.dto.Censu;

import java.util.List;

public interface ICensuService extends IBaseService<Censu>, ProxySelf<ICensuService>{
    List<Censu> selectByCensu(IRequest requestContext, Censu censu, int page, int
            pagesize);

    List<Censu> batchUpdate(IRequest requestContext, @StdWho List<Censu>
            census);
}