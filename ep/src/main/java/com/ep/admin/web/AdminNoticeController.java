package com.ep.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.core.util.JacksonUtil;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.*;
import com.ep.db.service.AdminService;
import com.ep.db.service.NoticeAdminService;
import com.ep.db.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ep.admin.util.AdminResponseCode.NOTICE_UPDATE_NOT_ALLOWED;

@RestController
@RequestMapping("/admin/notice")
@Validated
public class AdminNoticeController {
    private final Log logger = LogFactory.getLog(AdminNoticeController.class);

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private NoticeAdminService noticeAdminService;

    @RequiresPermissions("admin:notice:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "通知管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String title, String content,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<Notice> noticeList = noticeService.querySelective(title, content, page, limit, sort, order);
        return ResponseUtil.okList(noticeList);
    }

    private Object validate(Notice notice) {
        String title = notice.getTitle();
        if (StringUtils.isEmpty(title)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    private Integer getAdminId(){
        Subject currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();
        return admin.getId();
    }

    @RequiresPermissions("admin:notice:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "通知管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody Notice notice) {
        Object error = validate(notice);
        if (error != null) {
            return error;
        }
        // 1. 添加通知记录
        notice.setAdminId(getAdminId());
        noticeService.add(notice);
        // 2. 添加管理员通知记录
        List<Admin> adminList = adminService.all();
        NoticeAdmin noticeAdmin = new NoticeAdmin();
        noticeAdmin.setNoticeId(notice.getId());
        noticeAdmin.setNoticeTitle(notice.getTitle());
        for(Admin admin : adminList){
            noticeAdmin.setAdminId(admin.getId());
            noticeAdminService.add(noticeAdmin);
        }
        return ResponseUtil.ok(notice);
    }

    @RequiresPermissions("admin:notice:read")
    @RequiresPermissionsDesc(menu = {"系统管理", "通知管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        Notice notice = noticeService.findById(id);
        List<NoticeAdmin> noticeAdminList = noticeAdminService.queryByNoticeId(id);
        Map<String, Object> data = new HashMap<>(2);
        data.put("notice", notice);
        data.put("noticeAdminList", noticeAdminList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:notice:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "通知管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody Notice notice) {
        Object error = validate(notice);
        if (error != null) {
            return error;
        }
        Notice originalNotice = noticeService.findById(notice.getId());
        if (originalNotice == null) {
            return ResponseUtil.badArgument();
        }
        // 如果通知已经有人阅读过，则不支持编辑
        if(noticeAdminService.countReadByNoticeId(notice.getId()) > 0){
            return ResponseUtil.fail(NOTICE_UPDATE_NOT_ALLOWED, "通知已被阅读，不能重新编辑");
        }
        // 1. 更新通知记录
        notice.setAdminId(getAdminId());
        noticeService.updateById(notice);
        // 2. 更新管理员通知记录
        if(!originalNotice.getTitle().equals(notice.getTitle())){
            NoticeAdmin noticeAdmin = new NoticeAdmin();
            noticeAdmin.setNoticeTitle(notice.getTitle());
            noticeAdminService.updateByNoticeId(noticeAdmin, notice.getId());
        }
        return ResponseUtil.ok(notice);
    }

    @RequiresPermissions("admin:notice:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "通知管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody Notice notice) {
        // 1. 删除通知管理员记录
        noticeAdminService.deleteByNoticeId(notice.getId());
        // 2. 删除通知记录
        noticeService.deleteById(notice.getId());
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:notice:batch-delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "通知管理"}, button = "批量删除")
    @PostMapping("/batch-delete")
    public Object batchDelete(@RequestBody String body) {
        List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
        // 1. 删除通知管理员记录
        noticeAdminService.deleteByNoticeIds(ids);
        // 2. 删除通知记录
        noticeService.deleteByIds(ids);
        return ResponseUtil.ok();
    }
}
