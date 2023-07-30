package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrPersonFile;
import com.ep.db.domain.HrmCompany;
import com.ep.db.service.CompanyService;
import com.ep.db.service.PersonFileService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/person/file")
@Validated
public class PersonFileController {
    private final Log logger = LogFactory.getLog(PersonFileController.class);

    @Autowired
    private PersonFileService personFileService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("person:file:list")
    @RequiresPermissionsDesc(menu = {"档案管理", "人员档案"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrPersonFile> hrPersonFileList = personFileService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(hrPersonFileList);
    }

    @RequiresPermissions("person:file:create")
    @RequiresPermissionsDesc(menu = {"档案管理", "人员档案"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrPersonFile hrPersonFile) {
       // String companyName = hrPersonFile.getName();
        personFileService.add(hrPersonFile);

        //logHelper.logAuthSucceed("添加人员档案", companyName);

        return ResponseUtil.ok(hrPersonFile);
    }

    @RequiresPermissions("person:file:update")
    @RequiresPermissionsDesc(menu = {"档案管理", "人员档案"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrPersonFile hrPersonFile) {
        if (personFileService.updateById(hrPersonFile) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

       // logHelper.logAuthSucceed("编辑人员档案", hrPersonFile.getName());
        return ResponseUtil.ok(hrPersonFile);
    }

    @RequiresPermissions("person:file:delete")
    @RequiresPermissionsDesc(menu = {"档案管理", "人员档案"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrPersonFile hrPersonFile) {
        String id =  hrPersonFile.getId();
        personFileService.deleteById(id);

       // logHelper.logAuthSucceed("删除人员档案", hrPersonFile.getName());
        return ResponseUtil.ok();
    }
}
