package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrPerson;
import com.ep.db.domain.HrmEmployee;
import com.ep.db.service.RegisterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin/person/register")
@Validated
public class PersonRegisterController {
    private final Log logger = LogFactory.getLog(PersonRegisterController.class);

    @Autowired
    private RegisterService registerService;
    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("person:register:list")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "查询")
    @GetMapping("/listEmployee")
    public Object listEmployee(String username,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrmEmployee> hrmEmployeeList = registerService.querySelectiveEmployee(username, page, limit, sort, order);
        return ResponseUtil.okList(hrmEmployeeList);
    }

    @RequiresPermissions("person:register:list")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrPerson> hrPersonList = registerService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(hrPersonList);
    }

    @RequiresPermissions("person:register:create")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrPerson hrPerson) {
        registerService.add(hrPerson);
        return ResponseUtil.ok(hrPerson);
    }

    @RequiresPermissions("person:register:read")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull String id) {
        HrPerson hrPerson = registerService.findById(id);
        return ResponseUtil.ok(hrPerson);
    }

    @RequiresPermissions("person:register:update")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrPerson hrPerson) {
        String hrPersonId = hrPerson.getId();
        if (hrPersonId == null) {
            return ResponseUtil.badArgument();
        }

        if (registerService.updateById(hrPerson) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑人员信息", hrPerson.getName());
        return ResponseUtil.ok(hrPerson);
    }

    @RequiresPermissions("person:register:delete")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrPerson hrPerson) {
        String hrPersonId = hrPerson.getId();
        if (hrPersonId == null) {
            return ResponseUtil.badArgument();
        }

        registerService.deleteById(hrPersonId);
        logHelper.logAuthSucceed("删除人员信息", hrPerson.getName());
        return ResponseUtil.ok();
    }
}
