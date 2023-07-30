package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrPersonAttendanceRec;
import com.ep.db.domain.HrmCompany;
import com.ep.db.service.CompanyService;
import com.ep.db.service.PersonAttendanceRecService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/attendance/rec")
@Validated
public class PersonAttendanceRecController {
    private final Log logger = LogFactory.getLog(PersonAttendanceRecController.class);

    @Autowired
    private PersonAttendanceRecService personAttendanceRecService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("attendance:rec:list")
    @RequiresPermissionsDesc(menu = {"考勤管理", "人员考勤"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrPersonAttendanceRec> hrPersonAttendanceRecList = personAttendanceRecService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(hrPersonAttendanceRecList);
    }

    @RequiresPermissions("attendance:rec:create")
    @RequiresPermissionsDesc(menu = {"考勤管理", "人员考勤"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrPersonAttendanceRec hrPersonAttendanceRec) {
        //String companyName = hrPersonAttendanceRec.getName();
        personAttendanceRecService.add(hrPersonAttendanceRec);

        //logHelper.logAuthSucceed("添加人员考勤", companyName);

        return ResponseUtil.ok(hrPersonAttendanceRec);
    }

    @RequiresPermissions("attendance:rec:update")
    @RequiresPermissionsDesc(menu = {"考勤管理", "人员考勤"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrPersonAttendanceRec hrPersonAttendanceRec) {
        if (personAttendanceRecService.updateById(hrPersonAttendanceRec) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        //logHelper.logAuthSucceed("编辑人员考勤", hrPersonAttendanceRec.getName());
        return ResponseUtil.ok(hrPersonAttendanceRec);
    }

    @RequiresPermissions("attendance:rec:delete")
    @RequiresPermissionsDesc(menu = {"考勤管理", "人员考勤"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrPersonAttendanceRec hrPersonAttendanceRec) {
        String id =  hrPersonAttendanceRec.getId();
        personAttendanceRecService.deleteById(id);

        //logHelper.logAuthSucceed("删除人员考勤", hrPersonAttendanceRec.getName());
        return ResponseUtil.ok();
    }
}
