package com.ep.db.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class HrSalaryCtl {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hr_salary_ctl
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hr_salary_ctl
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.code
     *
     * @mbg.generated
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.type
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.value_en
     *
     * @mbg.generated
     */
    private String valueEn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.value_str
     *
     * @mbg.generated
     */
    private String valueStr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.value_d
     *
     * @mbg.generated
     */
    private BigDecimal valueD;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.notes
     *
     * @mbg.generated
     */
    private String notes;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.create_by
     *
     * @mbg.generated
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.update_by
     *
     * @mbg.generated
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.delete_by
     *
     * @mbg.generated
     */
    private String deleteBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.delete_time
     *
     * @mbg.generated
     */
    private LocalDateTime deleteTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.version
     *
     * @mbg.generated
     */
    private Integer version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hr_salary_ctl.tenant_id
     *
     * @mbg.generated
     */
    private String tenantId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.id
     *
     * @return the value of hr_salary_ctl.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.id
     *
     * @param id the value for hr_salary_ctl.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.code
     *
     * @return the value of hr_salary_ctl.code
     *
     * @mbg.generated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.code
     *
     * @param code the value for hr_salary_ctl.code
     *
     * @mbg.generated
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.name
     *
     * @return the value of hr_salary_ctl.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.name
     *
     * @param name the value for hr_salary_ctl.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.type
     *
     * @return the value of hr_salary_ctl.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.type
     *
     * @param type the value for hr_salary_ctl.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.value_en
     *
     * @return the value of hr_salary_ctl.value_en
     *
     * @mbg.generated
     */
    public String getValueEn() {
        return valueEn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.value_en
     *
     * @param valueEn the value for hr_salary_ctl.value_en
     *
     * @mbg.generated
     */
    public void setValueEn(String valueEn) {
        this.valueEn = valueEn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.value_str
     *
     * @return the value of hr_salary_ctl.value_str
     *
     * @mbg.generated
     */
    public String getValueStr() {
        return valueStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.value_str
     *
     * @param valueStr the value for hr_salary_ctl.value_str
     *
     * @mbg.generated
     */
    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.value_d
     *
     * @return the value of hr_salary_ctl.value_d
     *
     * @mbg.generated
     */
    public BigDecimal getValueD() {
        return valueD;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.value_d
     *
     * @param valueD the value for hr_salary_ctl.value_d
     *
     * @mbg.generated
     */
    public void setValueD(BigDecimal valueD) {
        this.valueD = valueD;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.notes
     *
     * @return the value of hr_salary_ctl.notes
     *
     * @mbg.generated
     */
    public String getNotes() {
        return notes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.notes
     *
     * @param notes the value for hr_salary_ctl.notes
     *
     * @mbg.generated
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.create_by
     *
     * @return the value of hr_salary_ctl.create_by
     *
     * @mbg.generated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.create_by
     *
     * @param createBy the value for hr_salary_ctl.create_by
     *
     * @mbg.generated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.create_time
     *
     * @return the value of hr_salary_ctl.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.create_time
     *
     * @param createTime the value for hr_salary_ctl.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.update_by
     *
     * @return the value of hr_salary_ctl.update_by
     *
     * @mbg.generated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.update_by
     *
     * @param updateBy the value for hr_salary_ctl.update_by
     *
     * @mbg.generated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.update_time
     *
     * @return the value of hr_salary_ctl.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.update_time
     *
     * @param updateTime the value for hr_salary_ctl.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_ctl
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.deleted
     *
     * @return the value of hr_salary_ctl.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.deleted
     *
     * @param deleted the value for hr_salary_ctl.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.delete_by
     *
     * @return the value of hr_salary_ctl.delete_by
     *
     * @mbg.generated
     */
    public String getDeleteBy() {
        return deleteBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.delete_by
     *
     * @param deleteBy the value for hr_salary_ctl.delete_by
     *
     * @mbg.generated
     */
    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.delete_time
     *
     * @return the value of hr_salary_ctl.delete_time
     *
     * @mbg.generated
     */
    public LocalDateTime getDeleteTime() {
        return deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.delete_time
     *
     * @param deleteTime the value for hr_salary_ctl.delete_time
     *
     * @mbg.generated
     */
    public void setDeleteTime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.version
     *
     * @return the value of hr_salary_ctl.version
     *
     * @mbg.generated
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.version
     *
     * @param version the value for hr_salary_ctl.version
     *
     * @mbg.generated
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hr_salary_ctl.tenant_id
     *
     * @return the value of hr_salary_ctl.tenant_id
     *
     * @mbg.generated
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hr_salary_ctl.tenant_id
     *
     * @param tenantId the value for hr_salary_ctl.tenant_id
     *
     * @mbg.generated
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_ctl
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", valueEn=").append(valueEn);
        sb.append(", valueStr=").append(valueStr);
        sb.append(", valueD=").append(valueD);
        sb.append(", notes=").append(notes);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", deleteBy=").append(deleteBy);
        sb.append(", deleteTime=").append(deleteTime);
        sb.append(", version=").append(version);
        sb.append(", tenantId=").append(tenantId);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_ctl
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
        HrSalaryCtl other = (HrSalaryCtl) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getValueEn() == null ? other.getValueEn() == null : this.getValueEn().equals(other.getValueEn()))
            && (this.getValueStr() == null ? other.getValueStr() == null : this.getValueStr().equals(other.getValueStr()))
            && (this.getValueD() == null ? other.getValueD() == null : this.getValueD().equals(other.getValueD()))
            && (this.getNotes() == null ? other.getNotes() == null : this.getNotes().equals(other.getNotes()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getDeleteBy() == null ? other.getDeleteBy() == null : this.getDeleteBy().equals(other.getDeleteBy()))
            && (this.getDeleteTime() == null ? other.getDeleteTime() == null : this.getDeleteTime().equals(other.getDeleteTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_ctl
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getValueEn() == null) ? 0 : getValueEn().hashCode());
        result = prime * result + ((getValueStr() == null) ? 0 : getValueStr().hashCode());
        result = prime * result + ((getValueD() == null) ? 0 : getValueD().hashCode());
        result = prime * result + ((getNotes() == null) ? 0 : getNotes().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getDeleteBy() == null) ? 0 : getDeleteBy().hashCode());
        result = prime * result + ((getDeleteTime() == null) ? 0 : getDeleteTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table hr_salary_ctl
     *
     * @mbg.generated
     */
    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table hr_salary_ctl
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "VARCHAR", false),
        code("code", "code", "VARCHAR", false),
        name("name", "name", "VARCHAR", true),
        type("type", "type", "VARCHAR", true),
        valueEn("value_en", "valueEn", "VARCHAR", false),
        valueStr("value_str", "valueStr", "VARCHAR", false),
        valueD("value_d", "valueD", "DECIMAL", false),
        notes("notes", "notes", "VARCHAR", false),
        createBy("create_by", "createBy", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateBy("update_by", "updateBy", "VARCHAR", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        deleted("deleted", "deleted", "BIT", false),
        deleteBy("delete_by", "deleteBy", "VARCHAR", false),
        deleteTime("delete_time", "deleteTime", "TIMESTAMP", false),
        version("version", "version", "INTEGER", false),
        tenantId("tenant_id", "tenantId", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
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
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hr_salary_ctl
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
         * This method corresponds to the database table hr_salary_ctl
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
         * This method corresponds to the database table hr_salary_ctl
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}