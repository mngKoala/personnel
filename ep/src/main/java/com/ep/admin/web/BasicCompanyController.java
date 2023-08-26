package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrmCompany;
import com.ep.db.service.CompanyService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/basic/company")
@Validated
public class BasicCompanyController {
    private final Log logger = LogFactory.getLog(BasicCompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("basic:company:list")
    @RequiresPermissionsDesc(menu = {"人力资源", "公司管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrmCompany> companyList = companyService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(companyList);
    }

    @RequiresPermissions("basic:company:create")
    @RequiresPermissionsDesc(menu = {"人力资源", "公司管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrmCompany hrmCompany) {
        String companyName = hrmCompany.getName();
        companyService.add(hrmCompany);

        logHelper.logAuthSucceed("添加公司", companyName);

        return ResponseUtil.ok(hrmCompany);
    }

    @RequiresPermissions("basic:company:update")
    @RequiresPermissionsDesc(menu = {"人力资源", "公司管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrmCompany hrmCompany) {
        if (companyService.updateById(hrmCompany) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑公司", hrmCompany.getName());
        return ResponseUtil.ok(hrmCompany);
    }

    @RequiresPermissions("basic:company:delete")
    @RequiresPermissionsDesc(menu = {"人力资源", "公司管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrmCompany hrmCompany) {
        String id =  hrmCompany.getId();
        companyService.deleteById(id);

        logHelper.logAuthSucceed("删除公司", hrmCompany.getName());
        return ResponseUtil.ok();
    }
}
