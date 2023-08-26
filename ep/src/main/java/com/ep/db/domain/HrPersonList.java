package com.ep.db.domain;

public class HrPersonList extends  HrPerson {

    private HrmEmployee  hrmEmployee;

    public HrmEmployee getHrmEmployee() {
        return hrmEmployee;
    }

    public void setHrmEmployee(HrmEmployee hrmEmployee) {
        this.hrmEmployee = hrmEmployee;
    }
}
