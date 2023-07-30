package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrSalaryCtlMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrSalaryCtl;
import com.ep.db.domain.HrSalaryCtlExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalaryParameterService {

    @Resource
    private HrSalaryCtlMapper hrSalaryCtlMapper;

    public List<HrSalaryCtl> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrSalaryCtlExample example = new HrSalaryCtlExample();
        HrSalaryCtlExample.Criteria criteria = example.createCriteria();


        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrSalaryCtlMapper.selectByExampleSelective(example);
    }

    public int updateById(HrSalaryCtl hrSalaryCtl) {
        hrSalaryCtl.setUpdateTime(LocalDateTime.now());
        return hrSalaryCtlMapper.updateByPrimaryKeySelective(hrSalaryCtl);
    }

    public void deleteById(String id) {
        hrSalaryCtlMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrSalaryCtl hrSalaryCtl) {
        if (hrSalaryCtl.getId() == null) {
            hrSalaryCtl.setId(String.valueOf(IdWorker.getId()));
        }
        hrSalaryCtl.setCreateTime(LocalDateTime.now());
        hrSalaryCtl.setUpdateTime(LocalDateTime.now());
        hrSalaryCtlMapper.insertSelective(hrSalaryCtl);
    }
}
