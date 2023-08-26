package com.ep.db.service;

import com.ep.db.dao.HrPersonMapper;
import com.ep.db.domain.HrPerson;
import com.ep.db.domain.HrPersonExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonPracticeService {

    @Resource
    private HrPersonMapper hrPersonMapper;

    public List<HrPerson> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        HrPersonExample example = new HrPersonExample();
        HrPersonExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }

        criteria.andEmployeeIdentityStatusEqualTo("practice");
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, limit);
        return hrPersonMapper.selectByExampleSelective(example);
    }
}
