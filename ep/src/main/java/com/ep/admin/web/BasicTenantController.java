package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrmCompany;
import com.ep.db.domain.SysTenant;
import com.ep.db.service.CompanyService;
import com.ep.db.service.TenantService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/basic/tenant")
@Validated
public class BasicTenantController {
    private final Log logger = LogFactory.getLog(BasicTenantController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("basic:tenant:list")
    @RequiresPermissionsDesc(menu = {"人力资源", "租户管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<SysTenant> companyList = tenantService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(companyList);
    }

    @RequiresPermissions("basic:tenant:create")
    @RequiresPermissionsDesc(menu = {"人力资源", "租户管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody SysTenant sysTenant) {
        //String companyName = sysTenant.getName();
        tenantService.add(sysTenant);

       // logHelper.logAuthSucceed("添加租户", companyName);

        return ResponseUtil.ok(sysTenant);
    }

    @RequiresPermissions("basic:tenant:update")
    @RequiresPermissionsDesc(menu = {"人力资源", "租户管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody SysTenant sysTenant) {
        /**
        if (tenantService.updateById(hrmCompany) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑租户", hrmCompany.getName());**/
        return ResponseUtil.ok(sysTenant);
    }

    @RequiresPermissions("basic:tenant:delete")
    @RequiresPermissionsDesc(menu = {"人力资源", "租户管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody SysTenant sysTenant) {
        String id =  sysTenant.getId();
        tenantService.deleteById(id);

        logHelper.logAuthSucceed("删除租户", sysTenant.getAlias());
        return ResponseUtil.ok();
    }
}
