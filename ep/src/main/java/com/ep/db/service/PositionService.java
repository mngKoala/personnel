package com.ep.db.service;

import com.alibaba.druid.util.StringUtils;
import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.dao.HrmEmployeeMapper;
import com.ep.db.dao.HrmPositionMapper;
import com.ep.db.domain.*;
import com.ep.db.domain.Admin.Column;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PositionService {
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};

    @Resource
    private HrmCompanyMapper hrmCompanyMapper;

    @Resource
    private HrmEmployeeMapper hrmEmployeeMapper;

    @Resource
    private HrmPositionMapper hrmPositionMapper;

    public List<HrmPosition> querySelective(String id , String name, Integer page, Integer limit, String sort, String order) {
        HrmPositionExample  example = new HrmPositionExample();
        HrmPositionExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andOrgIdEqualTo(id);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andFullNameLike("%" + name + "%");
        }

        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        example.setOrderByClause("create_time" + " " + order);

        PageHelper.startPage(page, limit);
        return hrmPositionMapper.selectByExampleSelective(example);
    }

    public int updateById(HrmPosition hrmPosition) {
        hrmPosition.setUpdateTime(LocalDateTime.now());
        return hrmPositionMapper.updateByPrimaryKeySelective(hrmPosition);
    }

    public void deleteById(String id) {
        hrmPositionMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrmPosition hrmPosition) {
        if (hrmPosition.getId() == null) {
            hrmPosition.setId(String.valueOf(IdWorker.getId()));
        }
        hrmPosition.setCreateTime(LocalDateTime.now());
        hrmPosition.setUpdateTime(LocalDateTime.now());
        hrmPositionMapper.insertSelective(hrmPosition);
    }
}
