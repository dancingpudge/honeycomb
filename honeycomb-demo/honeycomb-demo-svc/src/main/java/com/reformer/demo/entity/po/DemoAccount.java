package com.honeycomb.demo.entity.po;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DemoAccount {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.account_id
     *
     * @mbg.generated
     */
    private Integer accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.account_code
     *
     * @mbg.generated
     */
    private String accountCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.union_id
     *
     * @mbg.generated
     */
    private String unionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.account_status
     *
     * @mbg.generated
     */
    private String accountStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.terminal
     *
     * @mbg.generated
     */
    private String terminal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.is_recharge
     *
     * @mbg.generated
     */
    private Integer isRecharge;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.unconscious_status
     *
     * @mbg.generated
     */
    private Boolean unconsciousStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.active_time
     *
     * @mbg.generated
     */
    private LocalDateTime activeTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column demo_account.is_delete
     *
     * @mbg.generated
     */
    private Boolean isDelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.account_id
     *
     * @return the value of demo_account.account_id
     *
     * @mbg.generated
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.account_id
     *
     * @param accountId the value for demo_account.account_id
     *
     * @mbg.generated
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.account_code
     *
     * @return the value of demo_account.account_code
     *
     * @mbg.generated
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.account_code
     *
     * @param accountCode the value for demo_account.account_code
     *
     * @mbg.generated
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.union_id
     *
     * @return the value of demo_account.union_id
     *
     * @mbg.generated
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.union_id
     *
     * @param unionId the value for demo_account.union_id
     *
     * @mbg.generated
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.user_name
     *
     * @return the value of demo_account.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.user_name
     *
     * @param userName the value for demo_account.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.account_status
     *
     * @return the value of demo_account.account_status
     *
     * @mbg.generated
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.account_status
     *
     * @param accountStatus the value for demo_account.account_status
     *
     * @mbg.generated
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.terminal
     *
     * @return the value of demo_account.terminal
     *
     * @mbg.generated
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.terminal
     *
     * @param terminal the value for demo_account.terminal
     *
     * @mbg.generated
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.is_recharge
     *
     * @return the value of demo_account.is_recharge
     *
     * @mbg.generated
     */
    public Integer getIsRecharge() {
        return isRecharge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.is_recharge
     *
     * @param isRecharge the value for demo_account.is_recharge
     *
     * @mbg.generated
     */
    public void setIsRecharge(Integer isRecharge) {
        this.isRecharge = isRecharge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.unconscious_status
     *
     * @return the value of demo_account.unconscious_status
     *
     * @mbg.generated
     */
    public Boolean getUnconsciousStatus() {
        return unconsciousStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.unconscious_status
     *
     * @param unconsciousStatus the value for demo_account.unconscious_status
     *
     * @mbg.generated
     */
    public void setUnconsciousStatus(Boolean unconsciousStatus) {
        this.unconsciousStatus = unconsciousStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.create_time
     *
     * @return the value of demo_account.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.create_time
     *
     * @param createTime the value for demo_account.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.update_time
     *
     * @return the value of demo_account.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.update_time
     *
     * @param updateTime the value for demo_account.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.active_time
     *
     * @return the value of demo_account.active_time
     *
     * @mbg.generated
     */
    public LocalDateTime getActiveTime() {
        return activeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.active_time
     *
     * @param activeTime the value for demo_account.active_time
     *
     * @mbg.generated
     */
    public void setActiveTime(LocalDateTime activeTime) {
        this.activeTime = activeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column demo_account.is_delete
     *
     * @return the value of demo_account.is_delete
     *
     * @mbg.generated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column demo_account.is_delete
     *
     * @param isDelete the value for demo_account.is_delete
     *
     * @mbg.generated
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_account
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accountId=").append(accountId);
        sb.append(", accountCode=").append(accountCode);
        sb.append(", unionId=").append(unionId);
        sb.append(", userName=").append(userName);
        sb.append(", accountStatus=").append(accountStatus);
        sb.append(", terminal=").append(terminal);
        sb.append(", isRecharge=").append(isRecharge);
        sb.append(", unconsciousStatus=").append(unconsciousStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", activeTime=").append(activeTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_account
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
        DemoAccount other = (DemoAccount) that;
        return (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getAccountCode() == null ? other.getAccountCode() == null : this.getAccountCode().equals(other.getAccountCode()))
            && (this.getUnionId() == null ? other.getUnionId() == null : this.getUnionId().equals(other.getUnionId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getAccountStatus() == null ? other.getAccountStatus() == null : this.getAccountStatus().equals(other.getAccountStatus()))
            && (this.getTerminal() == null ? other.getTerminal() == null : this.getTerminal().equals(other.getTerminal()))
            && (this.getIsRecharge() == null ? other.getIsRecharge() == null : this.getIsRecharge().equals(other.getIsRecharge()))
            && (this.getUnconsciousStatus() == null ? other.getUnconsciousStatus() == null : this.getUnconsciousStatus().equals(other.getUnconsciousStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getActiveTime() == null ? other.getActiveTime() == null : this.getActiveTime().equals(other.getActiveTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_account
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getAccountCode() == null) ? 0 : getAccountCode().hashCode());
        result = prime * result + ((getUnionId() == null) ? 0 : getUnionId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getAccountStatus() == null) ? 0 : getAccountStatus().hashCode());
        result = prime * result + ((getTerminal() == null) ? 0 : getTerminal().hashCode());
        result = prime * result + ((getIsRecharge() == null) ? 0 : getIsRecharge().hashCode());
        result = prime * result + ((getUnconsciousStatus() == null) ? 0 : getUnconsciousStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getActiveTime() == null) ? 0 : getActiveTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table demo_account
     *
     * @mbg.generated
     */
    public enum Column {
        accountId("account_id", "accountId", "INTEGER", false),
        accountCode("account_code", "accountCode", "VARCHAR", false),
        unionId("union_id", "unionId", "VARCHAR", false),
        userName("user_name", "userName", "VARCHAR", false),
        accountStatus("account_status", "accountStatus", "CHAR", false),
        terminal("terminal", "terminal", "VARCHAR", false),
        isRecharge("is_recharge", "isRecharge", "INTEGER", false),
        unconsciousStatus("unconscious_status", "unconsciousStatus", "BIT", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        activeTime("active_time", "activeTime", "TIMESTAMP", false),
        isDelete("is_delete", "isDelete", "BIT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table demo_account
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
         * This method corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table demo_account
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
         * This method corresponds to the database table demo_account
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
         * This method corresponds to the database table demo_account
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}