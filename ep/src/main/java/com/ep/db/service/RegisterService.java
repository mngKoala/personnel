package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrPersonMapper;
import com.ep.db.dao.HrmEmployeeMapper;
import com.ep.db.domain.*;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegisterService {
    @Resource
    private HrPersonMapper  hrPersonMapper;

    @Resource
    private HrmEmployeeMapper hrmEmployeeMapper;

    public List<HrmEmployee> querySelectiveEmployee(String username, Integer page, Integer limit, String sort, String order) {
        HrmEmployeeExample example = new HrmEmployeeExample();
        HrmEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, limit);
        return hrmEmployeeMapper.selectByExampleSelective(example);
    }

    public List<HrPerson> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrPersonExample example = new HrPersonExample();
        HrPersonExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, limit);
        return hrPersonMapper.selectByExampleSelective(example);
    }

    public int updateById(HrPerson hrPerson) {
        hrPerson.setUpdateTime(LocalDateTime.now());
        return hrPersonMapper.updateByPrimaryKeySelective(hrPerson);
    }

    public void deleteById(String id) {
        hrPersonMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrPerson hrPerson) {
        if (hrPerson.getId() == null) {
            hrPerson.setId(String.valueOf(IdWorker.getId()));
        }

        hrPerson.setCreateTime(LocalDateTime.now());
        hrPerson.setUpdateTime(LocalDateTime.now());

        hrPersonMapper.insertSelective(hrPerson);
    }

    public HrPerson findById(String id) {
        return hrPersonMapper.selectByPrimaryKey(id);
    }
}
