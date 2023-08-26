package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrPerson;
import com.ep.db.service.PersonPracticeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/person/practice")
@Validated
public class PersonPracticeController {
    private final Log logger = LogFactory.getLog(PersonPracticeController.class);

    @Autowired
    private PersonPracticeService personPracticeService;

    @RequiresPermissions("person:practice:list")
    @RequiresPermissionsDesc(menu = {"人员信息", "实习员工"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {

        List<HrPerson> hrPersonList = personPracticeService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(hrPersonList);
    }

    @RequiresPermissions("person:practice:create")
    @RequiresPermissionsDesc(menu = {"人员信息", "实习员工"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrPerson hrPerson) {

        return ResponseUtil.ok(hrPerson);
    }

    @RequiresPermissions("person:practice:update")
    @RequiresPermissionsDesc(menu = {"人员信息", "实习员工"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody  HrPerson hrPerson) {
        return ResponseUtil.ok(hrPerson);
    }

    @RequiresPermissions("person:practice:delete")
    @RequiresPermissionsDesc(menu = {"人员信息", "实习员工"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrPerson hrPerson) {
        return ResponseUtil.ok();
    }
}
