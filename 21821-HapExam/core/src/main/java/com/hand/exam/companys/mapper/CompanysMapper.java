package com.hand.exam.companys.mapper;

import com.hand.exam.censu.dto.Censu;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.exam.companys.dto.Companys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CompanysMapper extends Mapper<Companys>{
    List<Companys> selectById(Companys companys);
}