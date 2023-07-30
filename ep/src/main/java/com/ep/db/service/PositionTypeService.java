package com.ep.db.service;

import com.ep.db.dao.HrPositionTypeMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrPositionType;
import com.ep.db.domain.HrPositionTypeExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PositionTypeService {
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};

    @Resource
    private HrmCompanyMapper hrmCompanyMapper;

    @Resource
    private HrPositionTypeMapper hrPositionTypeMapper;

    public List<HrPositionType> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrPositionTypeExample example = new HrPositionTypeExample();
        HrPositionTypeExample.Criteria criteria = example.createCriteria();


        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrPositionTypeMapper.selectByExampleSelective(example);
    }

    public int updateById(HrPositionType hrPositionType) {
        hrPositionType.setUpdateTime(LocalDateTime.now());
        return hrPositionTypeMapper.updateByPrimaryKeySelective(hrPositionType);
    }

    public void deleteById(String id) {
        hrPositionTypeMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrPositionType hrPositionType) {
        hrPositionType.setCreateTime(LocalDateTime.now());
        hrPositionType.setUpdateTime(LocalDateTime.now());
        hrPositionTypeMapper.insertSelective(hrPositionType);
    }
}
