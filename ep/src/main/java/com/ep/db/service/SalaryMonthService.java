package com.ep.db.service;

import com.ep.core.util.IdWorker;
import com.ep.db.dao.HrSalaryMonthMapper;
import com.ep.db.domain.HrSalaryMonth;
import com.ep.db.domain.HrSalaryMonthExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalaryMonthService {

    @Resource
    private HrSalaryMonthMapper hrSalaryMonthMapper;

    public List<HrSalaryMonth> querySelective(String code, Integer page, Integer limit, String sort, String order) {
        HrSalaryMonthExample example = new HrSalaryMonthExample();
        HrSalaryMonthExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(code)) {
            criteria.andCodeEqualTo(code);
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, limit);
        return hrSalaryMonthMapper.selectByExampleSelective(example);
    }

    public int updateById(HrSalaryMonth hrSalaryMonth) {
        hrSalaryMonth.setUpdateTime(LocalDateTime.now());
        return hrSalaryMonthMapper.updateByPrimaryKeySelective(hrSalaryMonth);
    }

    public void deleteById(String id) {
        hrSalaryMonthMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrSalaryMonth hrSalaryMonth) {
        if (hrSalaryMonth.getId() == null) {
            hrSalaryMonth.setId(String.valueOf(IdWorker.getId()));
        }
        hrSalaryMonth.setCreateTime(LocalDateTime.now());
        hrSalaryMonth.setUpdateTime(LocalDateTime.now());
        hrSalaryMonthMapper.insertSelective(hrSalaryMonth);
    }
}
