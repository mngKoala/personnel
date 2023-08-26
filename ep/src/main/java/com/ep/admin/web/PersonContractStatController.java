package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.core.util.ResponseUtil;
import com.ep.db.domain.HrPersonContractStat;
import com.ep.db.service.PersonContractStatService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/contract/stat")
@Validated
public class PersonContractStatController {
    private final Log logger = LogFactory.getLog(PersonContractStatController.class);

    @Autowired
    private PersonContractStatService personContractStatService;

    @RequiresPermissions("person:stat:list")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员统计"}, button = "查询")
    @GetMapping("/list")
    public Object list() {
        List<HrPersonContractStat>  hrPersonContractStatList = personContractStatService.queryContractStat();
        return ResponseUtil.okList(hrPersonContractStatList);
    }

    @RequiresPermissions("person:stat:year")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员统计"}, button = "统计")
    @GetMapping("/year")
    public Object year() {
        List<HrPersonContractStat>  hrPersonContractStatList = personContractStatService.queryContractYear();
        return ResponseUtil.okList(hrPersonContractStatList);
    }
}
