package com.ep.admin.web;

import com.ep.admin.annotation.RequiresPermissionsDesc;
import com.ep.admin.service.LogHelper;
import com.ep.core.util.ResponseUtil;
import com.ep.core.validator.Order;
import com.ep.core.validator.Sort;
import com.ep.db.domain.HrPersonContract;
import com.ep.db.domain.HrmCompany;
import com.ep.db.service.CompanyService;
import com.ep.db.service.PersonContractService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/contract/contract")
@Validated
public class PersonContractController {
    private final Log logger = LogFactory.getLog(PersonContractController.class);

    @Autowired
    private PersonContractService personContractService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("contract:contract:list")
    @RequiresPermissionsDesc(menu = {"人员合同", "人员合同"}, button = "查询")
    @GetMapping("/list")
    public Object list(String businessCode,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<HrPersonContract> companyList = personContractService.querySelective(businessCode, page, limit, sort, order);
        return ResponseUtil.okList(companyList);
    }

    @RequiresPermissions("contract:contract:create")
    @RequiresPermissionsDesc(menu = {"人员合同", "人员合同"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody HrPersonContract hrPersonContract) {
        String businessCode = hrPersonContract.getBusinessCode();
        personContractService.add(hrPersonContract);

        logHelper.logAuthSucceed("添加人员合同", businessCode);

        return ResponseUtil.ok(hrPersonContract);
    }

    @RequiresPermissions("contract:contract:update")
    @RequiresPermissionsDesc(menu = {"人员合同", "人员合同"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody HrPersonContract hrPersonContract) {
        if (personContractService.updateById(hrPersonContract) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

       logHelper.logAuthSucceed("编辑人员合同", hrPersonContract.getBusinessCode());
        return ResponseUtil.ok(hrPersonContract);
    }

    @RequiresPermissions("contract:contract:delete")
    @RequiresPermissionsDesc(menu = {"人员合同", "人员合同"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody HrPersonContract hrPersonContract) {
        String id =  hrPersonContract.getId();
        personContractService.deleteById(id);

        logHelper.logAuthSucceed("删除人员合同", hrPersonContract.getBusinessCode());
        return ResponseUtil.ok();
    }
}
