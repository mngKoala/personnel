package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.SysTenantMapper;
import com.ep.db.domain.SysTenant;
import com.ep.db.domain.SysTenantExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TenantService {
    @Resource
    private SysTenantMapper sysTenantMapper;

    public List<SysTenant> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        SysTenantExample example = new SysTenantExample();
        SysTenantExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andAliasLike("%" + name + "%");
        }

        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, limit);
        return sysTenantMapper.selectByExampleSelective(example);
    }

    public int updateById(SysTenant sysTenant) {
        sysTenant.setUpdateTime(LocalDateTime.now());
        return sysTenantMapper.updateByPrimaryKeySelective(sysTenant);
    }

    public void deleteById(String id) {
        sysTenantMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(SysTenant sysTenant) {
        if (sysTenant.getId() == null) {
            sysTenant.setId(String.valueOf(IdWorker.getId()));
        }
        sysTenant.setCreateTime(LocalDateTime.now());
        sysTenant.setUpdateTime(LocalDateTime.now());
        sysTenantMapper.insertSelective(sysTenant);
    }
}
