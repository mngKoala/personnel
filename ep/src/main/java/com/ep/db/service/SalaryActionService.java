package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrSalaryActionMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrSalaryAction;
import com.ep.db.domain.HrSalaryActionExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalaryActionService {

    @Resource
    private HrSalaryActionMapper hrSalaryActionMapper;

    public List<HrSalaryAction> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrSalaryActionExample example = new HrSalaryActionExample();
        HrSalaryActionExample.Criteria criteria = example.createCriteria();


        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrSalaryActionMapper.selectByExampleSelective(example);
    }

    public int updateById(HrSalaryAction hrSalaryAction) {
        hrSalaryAction.setUpdateTime(LocalDateTime.now());
        return hrSalaryActionMapper.updateByPrimaryKeySelective(hrSalaryAction);
    }

    public void deleteById(String id) {
        hrSalaryActionMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrSalaryAction hrSalaryAction) {
        if (hrSalaryAction.getId() == null) {
            hrSalaryAction.setId(String.valueOf(IdWorker.getId()));
        }
        hrSalaryAction.setCreateTime(LocalDateTime.now());
        hrSalaryAction.setUpdateTime(LocalDateTime.now());
        hrSalaryActionMapper.insertSelective(hrSalaryAction);
    }
}
