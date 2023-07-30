package com.ep.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HrmEmployee {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hrm_employee
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hrm_employee
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.badge
     *
     * @mbg.generated
     */
    private String badge;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.person_id
     *
     * @mbg.generated
     */
    private String personId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.company_id
     *
     * @mbg.generated
     */
    private String companyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.tenant_id
     *
     * @mbg.generated
     */
    private String tenantId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.create_by
     *
     * @mbg.generated
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.update_by
     *
     * @mbg.generated
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.delete_by
     *
     * @mbg.generated
     */
    private String deleteBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.delete_time
     *
     * @mbg.generated
     */
    private LocalDateTime deleteTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.version
     *
     * @mbg.generated
     */
    private Integer version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.type
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hrm_employee.direct_leader_id
     *
     * @mbg.generated
     */
    private String directLeaderId;

    private List<HrmPerson>  personList;

    private List<HrmPosition>  hrmPositionList;

    private HrmPerson  hrmPerson;

    private  HrmEmployee directLeader;

    private  String  positionId;

    private  HrmPosition hrmPosition;

    private  int isPrimary;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.id
     *
     * @return the value of hrm_employee.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.id
     *
     * @param id the value for hrm_employee.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.badge
     *
     * @return the value of hrm_employee.badge
     *
     * @mbg.generated
     */
    public String getBadge() {
        return badge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.badge
     *
     * @param badge the value for hrm_employee.badge
     *
     * @mbg.generated
     */
    public void setBadge(String badge) {
        this.badge = badge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.phone
     *
     * @return the value of hrm_employee.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.phone
     *
     * @param phone the value for hrm_employee.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.person_id
     *
     * @return the value of hrm_employee.person_id
     *
     * @mbg.generated
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.person_id
     *
     * @param personId the value for hrm_employee.person_id
     *
     * @mbg.generated
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.company_id
     *
     * @return the value of hrm_employee.company_id
     *
     * @mbg.generated
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.company_id
     *
     * @param companyId the value for hrm_employee.company_id
     *
     * @mbg.generated
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.status
     *
     * @return the value of hrm_employee.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.status
     *
     * @param status the value for hrm_employee.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.tenant_id
     *
     * @return the value of hrm_employee.tenant_id
     *
     * @mbg.generated
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.tenant_id
     *
     * @param tenantId the value for hrm_employee.tenant_id
     *
     * @mbg.generated
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.create_by
     *
     * @return the value of hrm_employee.create_by
     *
     * @mbg.generated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.create_by
     *
     * @param createBy the value for hrm_employee.create_by
     *
     * @mbg.generated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.create_time
     *
     * @return the value of hrm_employee.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.create_time
     *
     * @param createTime the value for hrm_employee.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.update_by
     *
     * @return the value of hrm_employee.update_by
     *
     * @mbg.generated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.update_by
     *
     * @param updateBy the value for hrm_employee.update_by
     *
     * @mbg.generated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.update_time
     *
     * @return the value of hrm_employee.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.update_time
     *
     * @param updateTime the value for hrm_employee.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_employee
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.deleted
     *
     * @return the value of hrm_employee.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.deleted
     *
     * @param deleted the value for hrm_employee.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.delete_by
     *
     * @return the value of hrm_employee.delete_by
     *
     * @mbg.generated
     */
    public String getDeleteBy() {
        return deleteBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.delete_by
     *
     * @param deleteBy the value for hrm_employee.delete_by
     *
     * @mbg.generated
     */
    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.delete_time
     *
     * @return the value of hrm_employee.delete_time
     *
     * @mbg.generated
     */
    public LocalDateTime getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.delete_time
     *
     * @param deleteTime the value for hrm_employee.delete_time
     *
     * @mbg.generated
     */
    public void setDeleteTime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.version
     *
     * @return the value of hrm_employee.version
     *
     * @mbg.generated
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.version
     *
     * @param version the value for hrm_employee.version
     *
     * @mbg.generated
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.type
     *
     * @return the value of hrm_employee.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.type
     *
     * @param type the value for hrm_employee.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hrm_employee.direct_leader_id
     *
     * @return the value of hrm_employee.direct_leader_id
     *
     * @mbg.generated
     */
    public String getDirectLeaderId() {
        return directLeaderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hrm_employee.direct_leader_id
     *
     * @param directLeaderId the value for hrm_employee.direct_leader_id
     *
     * @mbg.generated
     */
    public void setDirectLeaderId(String directLeaderId) {
        this.directLeaderId = directLeaderId;
    }

    public List<HrmPerson> getPersonList() {
        return personList;
    }

    public void setPersonList(List<HrmPerson> personList) {
        this.personList = personList;
    }

    public HrmPerson getHrmPerson() {
        return hrmPerson;
    }

    public void setHrmPerson(HrmPerson hrmPerson) {
        this.hrmPerson = hrmPerson;
    }

    public HrmEmployee getDirectLeader() {
        return directLeader;
    }

    public void setDirectLeader(HrmEmployee directLeader) {
        this.directLeader = directLeader;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public HrmPosition getHrmPosition() {
        return hrmPosition;
    }

    public void setHrmPosition(HrmPosition hrmPosition) {
        this.hrmPosition = hrmPosition;
    }

    public List<HrmPosition> getHrmPositionList() {
        return hrmPositionList;
    }

    public void setHrmPositionList(List<HrmPosition> hrmPositionList) {
        this.hrmPositionList = hrmPositionList;
    }

    public int getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(int isPrimary) {
        this.isPrimary = isPrimary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_employee
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
        sb.append(", id=").append(id);
        sb.append(", badge=").append(badge);
        sb.append(", phone=").append(phone);
        sb.append(", personId=").append(personId);
        sb.append(", companyId=").append(companyId);
        sb.append(", status=").append(status);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", deleteBy=").append(deleteBy);
        sb.append(", deleteTime=").append(deleteTime);
        sb.append(", version=").append(version);
        sb.append(", type=").append(type);
        sb.append(", directLeaderId=").append(directLeaderId);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_employee
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        HrmEmployee other = (HrmEmployee) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBadge() == null ? other.getBadge() == null : this.getBadge().equals(other.getBadge()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getPersonId() == null ? other.getPersonId() == null : this.getPersonId().equals(other.getPersonId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getDeleteBy() == null ? other.getDeleteBy() == null : this.getDeleteBy().equals(other.getDeleteBy()))
            && (this.getDeleteTime() == null ? other.getDeleteTime() == null : this.getDeleteTime().equals(other.getDeleteTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getDirectLeaderId() == null ? other.getDirectLeaderId() == null : this.getDirectLeaderId().equals(other.getDirectLeaderId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_employee
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBadge() == null) ? 0 : getBadge().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPersonId() == null) ? 0 : getPersonId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getDeleteBy() == null) ? 0 : getDeleteBy().hashCode());
        result = prime * result + ((getDeleteTime() == null) ? 0 : getDeleteTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getDirectLeaderId() == null) ? 0 : getDirectLeaderId().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table hrm_employee
     *
     * @mbg.generated
     */
    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table hrm_employee
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "VARCHAR", false),
        badge("badge", "badge", "VARCHAR", false),
        phone("phone", "phone", "VARCHAR", false),
        personId("person_id", "personId", "VARCHAR", false),
        companyId("company_id", "companyId", "VARCHAR", false),
        status("status", "status", "VARCHAR", true),
        tenantId("tenant_id", "tenantId", "VARCHAR", false),
        createBy("create_by", "createBy", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateBy("update_by", "updateBy", "VARCHAR", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        deleted("deleted", "deleted", "BIT", false),
        deleteBy("delete_by", "deleteBy", "VARCHAR", false),
        deleteTime("delete_time", "deleteTime", "TIMESTAMP", false),
        version("version", "version", "INTEGER", false),
        type("type", "type", "VARCHAR", true),
        directLeaderId("direct_leader_id", "directLeaderId", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hrm_employee
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}