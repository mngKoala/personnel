package com.ep.db.domain;

public class HrPersonRegister {

    private HrmEmployee  hrmEmployee;

    private HrmPerson  hrmPerson;

    private HrPerson  hrPerson;

    public HrmEmployee getHrmEmployee() {
        return hrmEmployee;
    }

    public void setHrmEmployee(HrmEmployee hrmEmployee) {
        this.hrmEmployee = hrmEmployee;
    }

    public HrmPerson getHrmPerson() {
        return hrmPerson;
    }

    public void setHrmPerson(HrmPerson hrmPerson) {
        this.hrmPerson = hrmPerson;
    }

    public HrPerson getHrPerson() {
        return hrPerson;
    }

    public void setHrPerson(HrPerson hrPerson) {
        this.hrPerson = hrPerson;
    }
}