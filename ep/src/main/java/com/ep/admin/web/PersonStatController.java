package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.core.util.ResponseUtil;
import com.ep.db.domain.HrPersonStat;
import com.ep.db.service.PersonStatService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/person/stat")
@Validated
public class PersonStatController {
    private final Log logger = LogFactory.getLog(PersonStatController.class);

    @Autowired
    private PersonStatService personStatService;

    @RequiresPermissions("person:stat:list")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员统计"}, button = "查询")
    @GetMapping("/list")
    public Object list() {
        List<HrPersonStat>  hrPersonStatList = personStatService.queryEmployeeStat();
        return ResponseUtil.okList(hrPersonStatList);
    }

    @RequiresPermissions("person:stat:status")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员统计"}, button = "统计")
    @GetMapping("/status")
    public Object status() {
        List<HrPersonStat>  hrPersonStatList = personStatService.queryEmployeeStatus();
        return ResponseUtil.okList(hrPersonStatList);
    }

    @RequiresPermissions("person:stat:education")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员统计"}, button = "统计")
    @GetMapping("/education")
    public Object education() {
        List<HrPersonStat>  hrPersonStatList = personStatService.queryEmployeeEducation();
        return ResponseUtil.okList(hrPersonStatList);
    }
}
