package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmEmployee;
import com.ep.db.domain.HrmPosition;
import com.ep.db.service.CompanyService;
import com.ep.db.service.EmployeeService;
import com.ep.db.service.PositionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/basic/position")
@Validated
public class BasicPositionController {
    private final Log logger = LogFactory.getLog(BasicPositionController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("basic:position:list")
    @RequiresPermissionsDesc(menu = {"人力资源", "岗位管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrmPosition> employeeList = positionService.querySelective(id,name, page, limit, sort, order);
        return ResponseUtil.okList(employeeList);
    }

    @RequiresPermissions("basic:position:create")
    @RequiresPermissionsDesc(menu = {"人力资源", "岗位管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrmPosition hrmPosition) {
        String positionName = hrmPosition.getFullName();
        positionService.add(hrmPosition);

        logHelper.logAuthSucceed("添加岗位", positionName);

        return ResponseUtil.ok(hrmPosition);
    }

    @RequiresPermissions("basic:position:update")
    @RequiresPermissionsDesc(menu = {"人力资源", "岗位管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrmPosition hrmPosition) {
        if (positionService.updateById(hrmPosition) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑岗位", hrmPosition.getFullName());
        return ResponseUtil.ok(hrmPosition);
    }

    @RequiresPermissions("basic:position:delete")
    @RequiresPermissionsDesc(menu = {"人力资源", "岗位管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrmPosition hrmPosition) {
        String id =  hrmPosition.getId();
        positionService.deleteById(id);

        logHelper.logAuthSucceed("删除岗位", hrmPosition.getFullName());
        return ResponseUtil.ok();
    }
}
