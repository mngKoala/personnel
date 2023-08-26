package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrRecruitPostRec;
import com.ep.db.service.RecruitPostRecService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/post/rec")
@Validated
public class RecruitPostRecController {
    private final Log logger = LogFactory.getLog(RecruitPostRecController.class);

    @Autowired
    private RecruitPostRecService recruitPostRecService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("post:rec:list")
    @RequiresPermissionsDesc(menu = {"招聘管理", "招聘岗位"}, button = "查询")
    @GetMapping("/list")
    public Object list(String postName,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrRecruitPostRec> companyList = recruitPostRecService.querySelective(postName, page, limit, sort, order);
        return ResponseUtil.okList(companyList);
    }

    @RequiresPermissions("post:rec:create")
    @RequiresPermissionsDesc(menu = {"招聘管理", "招聘岗位"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrRecruitPostRec hrRecruitPostRec) {
        String postName = hrRecruitPostRec.getPostName();
        recruitPostRecService.add(hrRecruitPostRec);

        logHelper.logAuthSucceed("添加招聘岗位", postName);

        return ResponseUtil.ok(hrRecruitPostRec);
    }

    @RequiresPermissions("post:rec:update")
    @RequiresPermissionsDesc(menu = {"招聘管理", "招聘岗位"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrRecruitPostRec hrRecruitPostRec) {
        if (recruitPostRecService.updateById(hrRecruitPostRec) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑招聘岗位", hrRecruitPostRec.getPostName());
        return ResponseUtil.ok(hrRecruitPostRec);
    }

    @RequiresPermissions("post:rec:delete")
    @RequiresPermissionsDesc(menu = {"招聘管理", "招聘岗位"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrRecruitPostRec hrRecruitPostRec) {
        String id =  hrRecruitPostRec.getId();
        recruitPostRecService.deleteById(id);

        logHelper.logAuthSucceed("删除招聘岗位", hrRecruitPostRec.getPostName());
        return ResponseUtil.ok();
    }
}
