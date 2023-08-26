package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrSalaryMonth;
import com.ep.db.service.SalaryMonthService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/salary/month")
@Validated
public class SalaryMonthController {
    private final Log logger = LogFactory.getLog(SalaryMonthController.class);

    @Autowired
    private SalaryMonthService salaryMonthService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("salary:month:list")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬年月"}, button = "查询")
    @GetMapping("/list")
    public Object list(String code,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrSalaryMonth> hrSalaryMonthList = salaryMonthService.querySelective(code, page, limit, sort, order);
        return ResponseUtil.okList(hrSalaryMonthList);
    }

    @RequiresPermissions("salary:month:create")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬年月"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrSalaryMonth hrSalaryMonth) {
        String companyName = hrSalaryMonth.getName();
        salaryMonthService.add(hrSalaryMonth);

        logHelper.logAuthSucceed("添加薪酬年月", companyName);

        return ResponseUtil.ok(hrSalaryMonth);
    }

    @RequiresPermissions("salary:month:update")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬年月"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrSalaryMonth hrSalaryMonth) {
        if (salaryMonthService.updateById(hrSalaryMonth) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑薪酬年月", hrSalaryMonth.getName());
        return ResponseUtil.ok(hrSalaryMonth);
    }

    @RequiresPermissions("salary:month:delete")
    @RequiresPermissionsDesc(menu = {"薪酬管理", "薪酬年月"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrSalaryMonth hrSalaryMonth) {
        String id =  hrSalaryMonth.getId();
        salaryMonthService.deleteById(id);

        logHelper.logAuthSucceed("删除薪酬年月", hrSalaryMonth.getName());
        return ResponseUtil.ok();
    }
}
