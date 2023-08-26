package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrRecruitPersonRec;
import com.ep.db.service.RecruitPersonRecService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/person/rec")
@Validated
public class RecruitPersonRecController {
    private final Log logger = LogFactory.getLog(RecruitPersonRecController.class);

    @Autowired
    private RecruitPersonRecService recruitPersonRecService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("person:rec:list")
    @RequiresPermissionsDesc(menu = {"招聘管理", "招聘记录"}, button = "查询")
    @GetMapping("/list")
    public Object list(String postName,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrRecruitPersonRec> hrRecruitPersonRecList = recruitPersonRecService.querySelective(postName, page, limit, sort, order);
        return ResponseUtil.okList(hrRecruitPersonRecList);
    }

    @RequiresPermissions("person:rec:create")
    @RequiresPermissionsDesc(menu = {"招聘管理", "招聘记录"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrRecruitPersonRec hrRecruitPersonRec) {
        String postName = hrRecruitPersonRec.getPostName();
        recruitPersonRecService.add(hrRecruitPersonRec);

        logHelper.logAuthSucceed("添加招聘记录", postName);

        return ResponseUtil.ok(hrRecruitPersonRec);
    }

    @RequiresPermissions("person:rec:update")
    @RequiresPermissionsDesc(menu = {"招聘管理", "招聘记录"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrRecruitPersonRec hrRecruitPersonRec) {
        if (recruitPersonRecService.updateById(hrRecruitPersonRec) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑招聘记录", hrRecruitPersonRec.getPostName());
        return ResponseUtil.ok(hrRecruitPersonRec);
    }

    @RequiresPermissions("person:rec:delete")
    @RequiresPermissionsDesc(menu = {"招聘管理", "招聘记录"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrRecruitPersonRec hrRecruitPersonRec) {
        String id =  hrRecruitPersonRec.getId();
        recruitPersonRecService.deleteById(id);

        logHelper.logAuthSucceed("删除招聘记录", hrRecruitPersonRec.getPostName());
        return ResponseUtil.ok();
    }
}
