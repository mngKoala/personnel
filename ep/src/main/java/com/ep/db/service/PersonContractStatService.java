package com.ep.db.service;

import com.ep.db.dao.HrPersonContractStatMapper;
import com.ep.db.domain.HrPersonContractStat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonContractStatService {

    @Resource
    private HrPersonContractStatMapper hrPersonContractStatMapper;

    public List<HrPersonContractStat> queryContractStat() {
        return hrPersonContractStatMapper.getContractStat();
    }

    public List<HrPersonContractStat> queryContractYear() {
        return hrPersonContractStatMapper.getContractYear();
    }

}
