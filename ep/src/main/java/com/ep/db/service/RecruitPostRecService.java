package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrRecruitPostRecMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrRecruitPostRec;
import com.ep.db.domain.HrRecruitPostRecExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecruitPostRecService {

    @Resource
    private HrRecruitPostRecMapper hrRecruitPostRecMapper;

    public List<HrRecruitPostRec> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrRecruitPostRecExample example = new HrRecruitPostRecExample();
        HrRecruitPostRecExample.Criteria criteria = example.createCriteria();


        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrRecruitPostRecMapper.selectByExampleSelective(example);
    }

    public int updateById(HrRecruitPostRec hrRecruitPostRec) {
        hrRecruitPostRec.setUpdateTime(LocalDateTime.now());
        return hrRecruitPostRecMapper.updateByPrimaryKeySelective(hrRecruitPostRec);
    }

    public void deleteById(String id) {
        hrRecruitPostRecMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrRecruitPostRec hrRecruitPostRec) {
        if (hrRecruitPostRec.getId() == null) {
            hrRecruitPostRec.setId(String.valueOf(IdWorker.getId()));
        }
        hrRecruitPostRec.setCreateTime(LocalDateTime.now());
        hrRecruitPostRec.setUpdateTime(LocalDateTime.now());
        hrRecruitPostRecMapper.insertSelective(hrRecruitPostRec);
    }
}
