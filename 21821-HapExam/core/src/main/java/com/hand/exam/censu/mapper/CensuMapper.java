package com.hand.exam.censu.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.exam.censu.dto.Censu;

import java.util.List;

public interface CensuMapper extends Mapper<Censu>{
    List<Censu> selectByCensu(Censu Censu);
    int insertCensu(Censu Censu);
    int updateCensu(Censu Censu);
    int deleteByPrimaryKey(Censu Censu);
    List<Censu> selectByCensuDetail(Censu Censu);
    int addOneCensu(Censu censu);
}