package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.AdminMapper;
import com.ep.db.dao.HrmOrganizationMapper;
import com.ep.db.dao.HrmPositionMapper;
import com.ep.db.domain.*;
import com.ep.db.domain.HrPerson.Column;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrgService {
    ///private final Column[] result = new Column[]{Column.id, Column.name};

    @Resource
    private HrmOrganizationMapper  hrmOrganizationMapper;

    @Resource
    private HrmPositionMapper hrmPositionMapper;

    public List<HrmOrganization> findOrg() {

        return  hrmOrganizationMapper.getOrganizationTree("0");
    }

    public List<HrmOrganization> findOrgWithPosition() {

        return  hrmOrganizationMapper.getOrganizationWithPositionTree("0");
    }

    public List<HrmOrganization> findCompany() {

        return  hrmOrganizationMapper.getCompanyTree("0");
    }

    public List<HrmOrganization> findDept(String parentId ) {
        HrmOrganizationExample  example = new HrmOrganizationExample();
        HrmOrganizationExample.Criteria criteria = example.createCriteria();

        criteria.andTypeEqualTo("dept");
        criteria.andParentIdEqualTo(parentId);

        return  hrmOrganizationMapper.selectByExample(example);
    }

    public List<HrmOrganization> getName(HrmOrganization hrmOrganization) {
        String  id = hrmOrganization.getId();
        return  hrmOrganizationMapper.getParentTree(id);
    }

    public List<HrmOrganization> querySelective(String parentId, Integer page, Integer limit, String sort, String order) {
        HrmOrganizationExample  example = new HrmOrganizationExample();
        HrmOrganizationExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(parentId)) {
            criteria.andParentIdEqualTo(parentId);
        }

        criteria.andTypeEqualTo("dept");
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, limit);
        return hrmOrganizationMapper.selectByExampleSelective(example);
    }

    public int updateById(HrmOrganization hrmOrganization) {
        hrmOrganization.setUpdateTime(LocalDateTime.now());
        return hrmOrganizationMapper.updateByPrimaryKeySelective(hrmOrganization);
    }

    public void deleteById(String id) {
        hrmOrganizationMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrmOrganization hrmOrganization) {
        if (hrmOrganization.getId() == null) {
            hrmOrganization.setId(String.valueOf(IdWorker.getId()));
        }
        hrmOrganization.setCreateTime(LocalDateTime.now());
        hrmOrganization.setUpdateTime(LocalDateTime.now());

        String hierarchy  = hrmOrganization.getHierarchy();
        if(hierarchy == null){
            hrmOrganization.setHierarchy(hrmOrganization.getId());
        }else{
            hrmOrganization.setHierarchy(hierarchy + "/" +hrmOrganization.getId());
        }

        hrmOrganizationMapper.insertSelective(hrmOrganization);
    }
}
