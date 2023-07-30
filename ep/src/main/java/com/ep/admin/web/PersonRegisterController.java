package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.RegexUtil;
import com.ep.core.util.ResponseUtil;
import com.ep.core.util.bcrypt.BCryptPasswordEncoder;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.Admin;
import com.ep.db.domain.HrPerson;
import com.ep.db.service.RegisterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.ep.admin.util.AdminResponseCode.*;

@RestController
@RequestMapping("/admin/person/register")
@Validated
public class PersonRegisterController {
    private final Log logger = LogFactory.getLog(PersonRegisterController.class);

    @Autowired
    private RegisterService registerService;
    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("person:register:list")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrPerson> hrPersonList = registerService.querySelective(username, page, limit, sort, order);
        return ResponseUtil.okList(hrPersonList);
    }

    private Object validate(Admin admin) {
        String name = admin.getUsername();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isUsername(name)) {
            return ResponseUtil.fail(ADMIN_INVALID_NAME, "管理员名称不符合规定");
        }
        String password = admin.getPassword();
        if (StringUtils.isEmpty(password) || password.length() < 6) {
            return ResponseUtil.fail(ADMIN_INVALID_PASSWORD, "管理员密码长度不能小于6");
        }
        return null;
    }

    @RequiresPermissions("person:register:create")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody Admin admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }

        String username = admin.getUsername();
        List<Admin> adminList = registerService.findAdmin(username);
        if (adminList.size() > 0) {
            return ResponseUtil.fail(ADMIN_NAME_EXIST, "管理员已经存在");
        }

        String rawPassword = admin.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        admin.setPassword(encodedPassword);
        registerService.add(admin);
        logHelper.logAuthSucceed("添加管理员", username);
        return ResponseUtil.ok(admin);
    }

    @RequiresPermissions("person:register:read")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
       // Admin admin = registerService.findById(id);
        Admin admin = null;
        return ResponseUtil.ok(admin);
    }

    @RequiresPermissions("person:register:update")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody Admin admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }

        Integer anotherAdminId = admin.getId();
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }

        // 不允许管理员通过编辑接口修改密码
        admin.setPassword(null);

        if (registerService.updateById(admin) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑管理员", admin.getUsername());
        return ResponseUtil.ok(admin);
    }

    @RequiresPermissions("person:register:delete")
    @RequiresPermissionsDesc(menu = {"人员信息", "人员登记"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody Admin admin) {
        Integer anotherAdminId = admin.getId();
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }

        // 管理员不能删除自身账号
        Subject currentUser = SecurityUtils.getSubject();
        Admin currentAdmin = (Admin) currentUser.getPrincipal();
        if (currentAdmin.getId().equals(anotherAdminId)) {
            return ResponseUtil.fail(ADMIN_DELETE_NOT_ALLOWED, "管理员不能删除自己账号");
        }

        registerService.deleteById(anotherAdminId);
        logHelper.logAuthSucceed("删除管理员", admin.getUsername());
        return ResponseUtil.ok();
    }
}
