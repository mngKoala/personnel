package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmEmployee;
import com.ep.db.domain.HrmOrganization;
import com.ep.db.service.CompanyService;
import com.ep.db.service.EmployeeService;
import com.ep.db.service.OrgService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/basic/employee")
@Validated
public class BasicEmployeeController {
    private final Log logger = LogFactory.getLog(BasicEmployeeController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private OrgService orgService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("basic:employee:list")
    @RequiresPermissionsDesc(menu = {"人力资源", "员工管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id,
                       String type,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {

        List<HrmEmployee> employeeList = employeeService.querySelective(id, type, page, limit, sort, order);
        return ResponseUtil.okList(employeeList);
    }

    @RequiresPermissions("basic:employee:create")
    @RequiresPermissionsDesc(menu = {"人力资源", "员工管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrmEmployee HrmEmployee) {

        /**
        String companyName = hrmCompany.getName();
        companyService.add(hrmCompany);

        logHelper.logAuthSucceed("添加公司", companyName);***/

        return ResponseUtil.ok(HrmEmployee);
    }

    @RequiresPermissions("basic:employee:update")
    @RequiresPermissionsDesc(menu = {"人力资源", "员工管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrmCompany hrmCompany) {
        if (companyService.updateById(hrmCompany) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑公司", hrmCompany.getName());
        return ResponseUtil.ok(hrmCompany);
    }

    @RequiresPermissions("basic:employee:delete")
    @RequiresPermissionsDesc(menu = {"人力资源", "员工管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrmCompany hrmCompany) {
        String id =  hrmCompany.getId();
        companyService.deleteById(id);

        logHelper.logAuthSucceed("删除公司", hrmCompany.getName());
        return ResponseUtil.ok();
    }
}
