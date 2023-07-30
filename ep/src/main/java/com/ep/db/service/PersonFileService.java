package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrPersonFileMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrPersonFile;
import com.ep.db.domain.HrPersonFileExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonFileService {

    @Resource
    private HrPersonFileMapper hrPersonFileMapper;

    public List<HrPersonFile> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrPersonFileExample  example = new HrPersonFileExample();
        HrPersonFileExample.Criteria criteria = example.createCriteria();


//        if (!StringUtils.isEmpty(name)) {
//            criteria.andNameLike("%" + name + "%");
//        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrPersonFileMapper.selectByExampleSelective(example);
    }

    public int updateById(HrPersonFile hrPersonFile) {
        hrPersonFile.setUpdateTime(LocalDateTime.now());
        return hrPersonFileMapper.updateByPrimaryKeySelective(hrPersonFile);
    }

    public void deleteById(String id) {
        hrPersonFileMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrPersonFile hrPersonFile) {
        if (hrPersonFile.getId() == null) {
            hrPersonFile.setId(String.valueOf(IdWorker.getId()));
        }
        hrPersonFile.setCreateTime(LocalDateTime.now());
        hrPersonFile.setUpdateTime(LocalDateTime.now());
        hrPersonFileMapper.insertSelective(hrPersonFile);
    }
}
