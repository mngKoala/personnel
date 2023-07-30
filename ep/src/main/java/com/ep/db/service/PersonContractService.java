package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrPersonContractMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrPersonContract;
import com.ep.db.domain.HrPersonContractExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonContractService {

    @Resource
    private HrPersonContractMapper hrPersonContractMapper;

    public List<HrPersonContract> querySelective(String businessCode, Integer page, Integer limit, String sort, String order) {
        HrPersonContractExample example = new HrPersonContractExample();
        HrPersonContractExample.Criteria criteria = example.createCriteria();


        if (!StringUtils.isEmpty(businessCode)) {
            criteria.andBusinessCodeEqualTo(businessCode);
        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrPersonContractMapper.selectByExampleSelective(example);
    }

    public int updateById(HrPersonContract hrPersonContract) {
        hrPersonContract.setUpdateTime(LocalDateTime.now());
        return hrPersonContractMapper.updateByPrimaryKeySelective(hrPersonContract);
    }

    public void deleteById(String id) {
        hrPersonContractMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrPersonContract hrPersonContract) {
        if (hrPersonContract.getId() == null) {
            hrPersonContract.setId(String.valueOf(IdWorker.getId()));
        }
        hrPersonContract.setCreateTime(LocalDateTime.now());
        hrPersonContract.setUpdateTime(LocalDateTime.now());
        hrPersonContractMapper.insertSelective(hrPersonContract);
    }
}
