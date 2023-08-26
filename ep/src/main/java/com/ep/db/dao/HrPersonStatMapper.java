package com.ep.db.dao;

import com.ep.db.domain.HrPersonStat;

import java.util.List;

public interface HrPersonStatMapper {

    List<HrPersonStat> getEmployeeStat();

    List<HrPersonStat> getEmployeeStatus();

    List<HrPersonStat> getEmployeeEducation();
}