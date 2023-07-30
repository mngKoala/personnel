package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrSalaryCtl;
import com.ep.db.domain.HrmCompany;
import com.ep.db.service.CompanyService;
import com.ep.db.service.SalaryParameterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/salary/parameter")
@Validated
public class SalaryParameterController {
    private final Log logger = LogFactory.getLog(SalaryParameterController.class);

    @Autowired
    private SalaryParameterService salaryParameterService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("salary:parameter:list")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬参数"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrSalaryCtl> companyList = salaryParameterService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(companyList);
    }

    @RequiresPermissions("salary:parameter:create")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬参数"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrSalaryCtl hrSalaryCtl) {
        String companyName = hrSalaryCtl.getName();
        salaryParameterService.add(hrSalaryCtl);

        logHelper.logAuthSucceed("添加薪酬参数", companyName);

        return ResponseUtil.ok(hrSalaryCtl);
    }

    @RequiresPermissions("salary:parameter:update")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬参数"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrSalaryCtl hrSalaryCtl) {
        if (salaryParameterService.updateById(hrSalaryCtl) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑薪酬参数", hrSalaryCtl.getName());
        return ResponseUtil.ok(hrSalaryCtl);
    }

    @RequiresPermissions("salary:parameter:delete")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬参数"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrSalaryCtl hrSalaryCtl) {
        String id =  hrSalaryCtl.getId();
        salaryParameterService.deleteById(id);

        logHelper.logAuthSucceed("删除薪酬参数", hrSalaryCtl.getName());
        return ResponseUtil.ok();
    }
}
