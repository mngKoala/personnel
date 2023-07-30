package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrPerson;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.HrmOrganization;
import com.ep.db.domain.HrmPosition;
import com.ep.db.service.OrgService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/basic/org")
@Validated
public class BasicOrgController {
    private final Log logger = LogFactory.getLog(BasicOrgController.class);

    @Autowired
    private OrgService orgService;

    @Autowired
    private LogHelper logHelper;

    @GetMapping("/tree")
    public Object tree() {
        List<HrmOrganization> hrmOrganizationList = orgService.findOrgWithPosition();
        return ResponseUtil.ok(hrmOrganizationList);
    }

    @GetMapping("/com")
    public Object getCompany() {
        List<HrmOrganization> hrmOrganizationList = orgService.findCompany();
        return ResponseUtil.ok(hrmOrganizationList);
    }

    @GetMapping("/dept")
    public Object dept(String parentId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrmOrganization> employeeList = orgService.querySelective(parentId, page, limit, sort, order);
        return ResponseUtil.okList(employeeList);
    }

    @GetMapping("/name")
    public Object getName(HrmOrganization hrmOrganization){
        List<HrmOrganization> hrmOrganizationList = orgService.getName(hrmOrganization);
        return ResponseUtil.ok(hrmOrganizationList);
    }

    @RequiresPermissions("basic:org:create")
    @RequiresPermissionsDesc(menu = {"人力资源", "组织管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrmOrganization hrmOrganization) {
        String orgName = hrmOrganization.getFullName();
        orgService.add(hrmOrganization);

        logHelper.logAuthSucceed("添加组织", orgName);

        return ResponseUtil.ok(hrmOrganization);
    }

    @RequiresPermissions("basic:org:update")
    @RequiresPermissionsDesc(menu = {"人力资源", "组织管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrmOrganization hrmOrganization) {
        if (orgService.updateById(hrmOrganization) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑组织", hrmOrganization.getFullName());
        return ResponseUtil.ok(hrmOrganization);
    }

    @RequiresPermissions("basic:org:delete")
    @RequiresPermissionsDesc(menu = {"人力资源", "组织管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrmOrganization hrmOrganization) {
        String id =  hrmOrganization.getId();
        orgService.deleteById(id);

        logHelper.logAuthSucceed("删除组织", hrmOrganization.getFullName());
        return ResponseUtil.ok();
    }
}
