package com.ep.db.service;

import com.ep.db.dao.HrPersonStatMapper;
import com.ep.db.domain.HrPersonStat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonStatService {

    @Resource
    private HrPersonStatMapper hrPersonStatMapper;

    public List<HrPersonStat> queryEmployeeStat() {
        return hrPersonStatMapper.getEmployeeStat();
    }

    public List<HrPersonStat> queryEmployeeStatus() {
        return hrPersonStatMapper.getEmployeeStatus();
    }

    public List<HrPersonStat> queryEmployeeEducation() {
        return hrPersonStatMapper.getEmployeeEducation();
    }
}
