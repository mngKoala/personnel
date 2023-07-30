package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrContractOrgMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrContractOrg;
import com.ep.db.domain.HrContractOrgExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContractOrgService {
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};

    @Resource
    private HrContractOrgMapper hrContractOrgMapper;

    public List<HrContractOrg> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrContractOrgExample example = new HrContractOrgExample();
        HrContractOrgExample.Criteria criteria = example.createCriteria();


        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrContractOrgMapper.selectByExampleSelective(example);
    }

    public int updateById(HrContractOrg hrContractOrg) {
        hrContractOrg.setUpdateTime(LocalDateTime.now());
        return hrContractOrgMapper.updateByPrimaryKeySelective(hrContractOrg);
    }

    public void deleteById(String id) {
        hrContractOrgMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrContractOrg hrContractOrg) {
        if (hrContractOrg.getId() == null) {
            hrContractOrg.setId(String.valueOf(IdWorker.getId()));
        }
        hrContractOrg.setCreateTime(LocalDateTime.now());
        hrContractOrg.setUpdateTime(LocalDateTime.now());
        hrContractOrgMapper.insertSelective(hrContractOrg);
    }
}
