package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.AdminMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.AdminExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompanyService {
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};

    @Resource
    private HrmCompanyMapper hrmCompanyMapper;

    public List<HrmCompany> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrmCompanyExample  example = new HrmCompanyExample();
        HrmCompanyExample.Criteria criteria = example.createCriteria();


        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrmCompanyMapper.selectByExampleSelective(example);
    }

    public int updateById(HrmCompany hrmCompany) {
        hrmCompany.setUpdateTime(LocalDateTime.now());
        return hrmCompanyMapper.updateByPrimaryKeySelective(hrmCompany);
    }

    public void deleteById(String id) {
        hrmCompanyMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrmCompany hrmCompany) {
        if (hrmCompany.getId() == null) {
            hrmCompany.setId(String.valueOf(IdWorker.getId()));
        }
        hrmCompany.setCreateTime(LocalDateTime.now());
        hrmCompany.setUpdateTime(LocalDateTime.now());
        hrmCompanyMapper.insertSelective(hrmCompany);
    }
}
