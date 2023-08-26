package com.ep.db.dao;

import com.ep.db.domain.HrPersonContractStat;

import java.util.List;

public interface HrPersonContractStatMapper {

    List<HrPersonContractStat> getContractStat();

    List<HrPersonContractStat> getContractYear();

}