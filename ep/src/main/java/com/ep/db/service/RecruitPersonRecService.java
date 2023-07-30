package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrRecruitPersonRecMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrRecruitPersonRec;
import com.ep.db.domain.HrRecruitPersonRecExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecruitPersonRecService {

    @Resource
    private HrRecruitPersonRecMapper hrRecruitPersonRecMapper;

    public List<HrRecruitPersonRec> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrRecruitPersonRecExample example = new HrRecruitPersonRecExample();
        HrRecruitPersonRecExample.Criteria criteria = example.createCriteria();


        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrRecruitPersonRecMapper.selectByExampleSelective(example);
    }

    public int updateById(HrRecruitPersonRec hrRecruitPersonRec) {
        hrRecruitPersonRec.setUpdateTime(LocalDateTime.now());
        return hrRecruitPersonRecMapper.updateByPrimaryKeySelective(hrRecruitPersonRec);
    }

    public void deleteById(String id) {
        hrRecruitPersonRecMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrRecruitPersonRec hrRecruitPersonRec) {
        if (hrRecruitPersonRec.getId() == null) {
            hrRecruitPersonRec.setId(String.valueOf(IdWorker.getId()));
        }
        hrRecruitPersonRec.setCreateTime(LocalDateTime.now());
        hrRecruitPersonRec.setUpdateTime(LocalDateTime.now());
        hrRecruitPersonRecMapper.insertSelective(hrRecruitPersonRec);
    }
}
