package com.ep.db.service;

import com.ep.db.dao.AdminMapper;
import com.ep.db.dao.HrPersonMapper;
import com.ep.db.domain.Admin;
import com.ep.db.domain.HrPerson.Column;
import com.ep.db.domain.AdminExample;
import com.ep.db.domain.HrPerson;
import com.ep.db.domain.HrPersonExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegisterService {
    private final Column[] result = new Column[]{Column.id, Column.name};

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private HrPersonMapper  hrPersonMapper;

    public List<Admin> findAdmin(String username) {
        AdminExample example = new AdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    public Admin findAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    public List<HrPerson> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        HrPersonExample example = new HrPersonExample();
        HrPersonExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            //criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }

        PageHelper.startPage(page, limit);
        return hrPersonMapper.selectByExampleSelective(example);
    }

    public int updateById(Admin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(Admin admin) {
        admin.setAddTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }

    /**
    public Admin findById(Integer id) {
        return adminMapper.selectByPrimaryKeySelective(id, result);
    }**/

    public List<Admin> all() {
        AdminExample example = new AdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }
}
