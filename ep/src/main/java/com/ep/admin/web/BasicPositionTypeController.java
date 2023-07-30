package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrPositionType;
import com.ep.db.service.PositionTypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/basic/position/type")
@Validated
public class BasicPositionTypeController {
    private final Log logger = LogFactory.getLog(BasicPositionTypeController.class);

    @Autowired
    private PositionTypeService positionTypeService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("basic:positionType:list")
    @RequiresPermissionsDesc(menu = {"人力资源", "岗位类型"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrPositionType> companyList = positionTypeService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(companyList);
    }

    @RequiresPermissions("basic:positionType:create")
    @RequiresPermissionsDesc(menu = {"人力资源", "岗位类型"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrPositionType hrPositionType) {
        String positionTypeName = hrPositionType.getName();
        positionTypeService.add(hrPositionType);

        logHelper.logAuthSucceed("添加岗位类型", positionTypeName);

        return ResponseUtil.ok(hrPositionType);
    }

    @RequiresPermissions("basic:positionType:update")
    @RequiresPermissionsDesc(menu = {"人力资源", "岗位类型"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrPositionType hrPositionType) {
        if (positionTypeService.updateById(hrPositionType) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑岗位类型", hrPositionType.getName());
        return ResponseUtil.ok(hrPositionType);
    }

    @RequiresPermissions("basic:positionType:delete")
    @RequiresPermissionsDesc(menu = {"人力资源", "岗位类型"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrPositionType hrPositionType) {
        Integer id =  hrPositionType.getId();
        positionTypeService.deleteById(String.valueOf(id));

        logHelper.logAuthSucceed("删除岗位类型", hrPositionType.getName());
        return ResponseUtil.ok();
    }
}
