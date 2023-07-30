package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrSalaryAction;
import com.ep.db.service.SalaryActionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/salary/action")
@Validated
public class SalaryActionController {
    private final Log logger = LogFactory.getLog(SalaryActionController.class);

    @Autowired
    private SalaryActionService salaryActionService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("basic:company:list")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬发放"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrSalaryAction> hrSalaryActionList = salaryActionService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(hrSalaryActionList);
    }

    @RequiresPermissions("basic:company:create")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬发放"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrSalaryAction hrSalaryAction) {
        String companyName = hrSalaryAction.getName();
        salaryActionService.add(hrSalaryAction);

        logHelper.logAuthSucceed("添加薪酬发放", companyName);

        return ResponseUtil.ok(hrSalaryAction);
    }

    @RequiresPermissions("basic:company:update")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬发放"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrSalaryAction hrSalaryAction) {
        if (salaryActionService.updateById(hrSalaryAction) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑薪酬发放", hrSalaryAction.getName());
        return ResponseUtil.ok(hrSalaryAction);
    }

    @RequiresPermissions("basic:company:delete")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬发放"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrSalaryAction hrSalaryAction) {
        String id =  hrSalaryAction.getId();
        salaryActionService.deleteById(id);

        logHelper.logAuthSucceed("删除薪酬发放", hrSalaryAction.getName());
        return ResponseUtil.ok();
    }
}
