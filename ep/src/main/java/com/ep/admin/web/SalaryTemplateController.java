package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrSalaryTpl;
import com.ep.db.domain.HrmCompany;
import com.ep.db.service.CompanyService;
import com.ep.db.service.SalaryTemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/salary/template")
@Validated
public class SalaryTemplateController {
    private final Log logger = LogFactory.getLog(SalaryTemplateController.class);

    @Autowired
    private SalaryTemplateService salaryTemplateService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("salary:template:list")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬模板"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrSalaryTpl> hrSalaryTplList = salaryTemplateService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(hrSalaryTplList);
    }

    @RequiresPermissions("salary:template:create")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬模板"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrSalaryTpl hrSalaryTpl) {
        String hrSalaryTplName = hrSalaryTpl.getName();
        salaryTemplateService.add(hrSalaryTpl);

        logHelper.logAuthSucceed("添加薪酬模板", hrSalaryTplName);

        return ResponseUtil.ok(hrSalaryTplName);
    }

    @RequiresPermissions("salary:template:update")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬模板"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrSalaryTpl hrSalaryTpl) {
        if (salaryTemplateService.updateById(hrSalaryTpl) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑薪酬模板", hrSalaryTpl.getName());
        return ResponseUtil.ok(hrSalaryTpl);
    }

    @RequiresPermissions("salary:template:delete")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬模板"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrSalaryTpl hrSalaryTpl) {
        String id =  hrSalaryTpl.getId();
        salaryTemplateService.deleteById(id);

        logHelper.logAuthSucceed("删除薪酬模板", hrSalaryTpl.getName());
        return ResponseUtil.ok();
    }
}
