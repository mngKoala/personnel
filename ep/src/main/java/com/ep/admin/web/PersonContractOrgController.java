package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrContractOrg;
import com.ep.db.service.ContractOrgService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/contract/org")
@Validated
public class PersonContractOrgController {
    private final Log logger = LogFactory.getLog(PersonContractOrgController.class);

    @Autowired
    private ContractOrgService contractOrgService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("contract:org:list")
    @RequiresPermissionsDesc(menu = {"人员合同", "合同签订方管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrContractOrg> companyList = contractOrgService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(companyList);
    }

    @RequiresPermissions("contract:org:create")
    @RequiresPermissionsDesc(menu = {"人员合同", "合同签订方管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrContractOrg hrContractOrg) {
        String companyName = hrContractOrg.getName();
        contractOrgService.add(hrContractOrg);

        logHelper.logAuthSucceed("添加合同签订方", companyName);

        return ResponseUtil.ok(hrContractOrg);
    }

    @RequiresPermissions("contract:org:update")
    @RequiresPermissionsDesc(menu = {"人员合同", "合同签订方管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrContractOrg hrContractOrg) {
        if (contractOrgService.updateById(hrContractOrg) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑合同签订方", hrContractOrg.getName());
        return ResponseUtil.ok(hrContractOrg);
    }

    @RequiresPermissions("contract:org:delete")
    @RequiresPermissionsDesc(menu = {"人员合同", "合同签订方管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrContractOrg hrContractOrg) {
        String id =  hrContractOrg.getId();
        contractOrgService.deleteById(id);

        logHelper.logAuthSucceed("删除合同签订方", hrContractOrg.getName());
        return ResponseUtil.ok();
    }
}
