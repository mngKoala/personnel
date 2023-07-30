package com.ep.db.service;

import com.ep.db.dao.HrmCompanyMapper;
import com.ep.db.dao.HrmEmployeeMapper;
import com.ep.db.dao.HrmEmployeePositionMapper;
import com.ep.db.dao.HrmOrganizationMapper;
import com.ep.db.domain.*;
import com.ep.db.domain.Admin.Column;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};

    @Resource
    private HrmCompanyMapper hrmCompanyMapper;

    @Resource
    private HrmEmployeeMapper hrmEmployeeMapper;

    @Resource
    private HrmOrganizationMapper hrmOrganizationMapper;

    @Resource
    private HrmEmployeePositionMapper hrmEmployeePositionMapper;

    public List<HrmEmployee> querySelective(String id, String type,Integer page, Integer limit, String sort, String order) {
        List<String>  valuesEmployeeId = new ArrayList<>();

        if("0".equals(id)){
            HrmEmployeeExample  example = new HrmEmployeeExample();
            HrmEmployeeExample.Criteria criteria = example.createCriteria();

            example.setOrderByClause("create_time" + " " + order);

            PageHelper.startPage(page, limit);
            return hrmEmployeeMapper.selectByExampleSelective(example);
        }

        if("position".equals(type)){
            valuesEmployeeId.add(id);
        }else {
            //根据传过来的id,获取下级，取下级的职位id
            List<HrmOrganization>   hrmOrganizationList= hrmOrganizationMapper.getOrganizationWithPositionTree(id);

            List<String>  valuesPositionId = getPositionId(hrmOrganizationList);
            //根据positionId查employeeId

            if(valuesPositionId.size() >0){
                HrmEmployeePositionExample   hrmEmployeePositionExample = new HrmEmployeePositionExample();
                HrmEmployeePositionExample.Criteria hrmEmployeePositionExampleCriteria  = hrmEmployeePositionExample.createCriteria();
                hrmEmployeePositionExampleCriteria.andPositionIdIn(valuesPositionId);

                List<HrmEmployeePosition> hrmEmployeePositionList = hrmEmployeePositionMapper.selectByExample(hrmEmployeePositionExample);

                for (int i = 0; i < hrmEmployeePositionList.size(); i++) {
                    HrmEmployeePosition  hrmEmployeePosition =  hrmEmployeePositionList.get(i);
                    valuesEmployeeId.add(hrmEmployeePosition.getEmployeeId());
                }
            }
        }

        if(valuesEmployeeId.size() >0 ){
            HrmEmployeeExample  example = new HrmEmployeeExample();
            HrmEmployeeExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(valuesEmployeeId);

            example.setOrderByClause("create_time" + " " + order);
            PageHelper.startPage(page, limit);
            return hrmEmployeeMapper.selectByExampleSelective(example);
        }

        return  new ArrayList<HrmEmployee>();
    }

    public List<String>   getPositionId(List<HrmOrganization>   hrmOrganizationList){
        List<String>  values = new ArrayList<>();

        for (int i = 0; i < hrmOrganizationList.size(); i++) {
            HrmOrganization   hrmOrganization =  hrmOrganizationList.get(i);
            if( "position".equals(hrmOrganization.getType())){
                values.add(hrmOrganization.getId());
            }else {
                List<HrmOrganization> hrmOrganizationChildren =  hrmOrganization.getChildren();
                getPositionId(hrmOrganizationChildren);
            }
        }

        return values;
    }

    public int updateById(HrmCompany hrmCompany) {
        hrmCompany.setUpdateTime(LocalDateTime.now());
        return hrmCompanyMapper.updateByPrimaryKeySelective(hrmCompany);
    }

    public void deleteById(String id) {
        hrmCompanyMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(HrmCompany hrmCompany) {
        hrmCompany.setCreateTime(LocalDateTime.now());
        hrmCompany.setUpdateTime(LocalDateTime.now());
        hrmCompanyMapper.insertSelective(hrmCompany);
    }
}
