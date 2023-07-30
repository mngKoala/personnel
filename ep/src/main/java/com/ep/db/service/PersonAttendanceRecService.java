package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrPersonAttendanceRecMapper;
import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.domain.Admin.Column;
import com.ep.db.domain.HrPersonAttendanceRec;
import com.ep.db.domain.HrPersonAttendanceRecExample;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmCompanyExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonAttendanceRecService {

    @Resource
    private HrPersonAttendanceRecMapper hrPersonAttendanceRecMapper;

    public List<HrPersonAttendanceRec> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrPersonAttendanceRecExample example = new HrPersonAttendanceRecExample();
        HrPersonAttendanceRecExample.Criteria criteria = example.createCriteria();


//        if (!StringUtils.isEmpty(name)) {
//            criteria.andNameLike("%" + name + "%");
//        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrPersonAttendanceRecMapper.selectByExampleSelective(example);
    }

    public int updateById(HrPersonAttendanceRec hrPersonAttendanceRec) {
        hrPersonAttendanceRec.setUpdateTime(LocalDateTime.now());
        return hrPersonAttendanceRecMapper.updateByPrimaryKeySelective(hrPersonAttendanceRec);
    }

    public void deleteById(String id) {
        hrPersonAttendanceRecMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrPersonAttendanceRec hrPersonAttendanceRec) {
        if (hrPersonAttendanceRec.getId() == null) {
            hrPersonAttendanceRec.setId(String.valueOf(IdWorker.getId()));
        }
        hrPersonAttendanceRec.setCreateTime(LocalDateTime.now());
        hrPersonAttendanceRec.setUpdateTime(LocalDateTime.now());
        hrPersonAttendanceRecMapper.insertSelective(hrPersonAttendanceRec);
    }
}
