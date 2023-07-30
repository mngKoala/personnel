package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrSalaryTplMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrSalaryTpl;
import com.ep.db.domain.HrSalaryTplExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalaryTemplateService {

    @Resource
    private HrSalaryTplMapper hrSalaryTplMapper;

    public List<HrSalaryTpl> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrSalaryTplExample example = new HrSalaryTplExample();
        HrSalaryTplExample.Criteria criteria = example.createCriteria();


//        if (!StringUtils.isEmpty(name)) {
//            criteria.andNameLike("%" + name + "%");
//        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrSalaryTplMapper.selectByExampleSelective(example);
    }

    public int updateById(HrSalaryTpl hrSalaryTpl) {
        hrSalaryTpl.setUpdateTime(LocalDateTime.now());
        return hrSalaryTplMapper.updateByPrimaryKeySelective(hrSalaryTpl);
    }

    public void deleteById(String id) {
        hrSalaryTplMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrSalaryTpl hrSalaryTpl) {
        if (hrSalaryTpl.getId() == null) {
            hrSalaryTpl.setId(String.valueOf(IdWorker.getId()));
        }
        hrSalaryTpl.setCreateTime(LocalDateTime.now());
        hrSalaryTpl.setUpdateTime(LocalDateTime.now());
        hrSalaryTplMapper.insertSelective(hrSalaryTpl);
    }
}
