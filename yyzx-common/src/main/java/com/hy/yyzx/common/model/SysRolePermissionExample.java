/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import me.jason.generator.model.BaseExample;

@SuppressWarnings("serial")
public class SysRolePermissionExample extends BaseExample implements Serializable {
    /**
     * @date Sun Apr 12 20:01:53 CST 2020
     */
    protected String orderByClause;

    /**
     * @date Sun Apr 12 20:01:53 CST 2020
     */
    protected boolean distinct;

    /**
     * @date Sun Apr 12 20:01:53 CST 2020
     */
    protected List<Criteria> oredCriteria;

    public SysRolePermissionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * SysRolePermission<p/>
     * sys_role_permission
     * @date Sun Apr 12 20:01:53 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNull() {
            addCriterion("roleid is null");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("roleid is not null");
            return (Criteria) this;
        }

        public Criteria andRoleidEqualTo(Integer value) {
            addCriterion("roleid =", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotEqualTo(Integer value) {
            addCriterion("roleid <>", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThan(Integer value) {
            addCriterion("roleid >", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("roleid >=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThan(Integer value) {
            addCriterion("roleid <", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThanOrEqualTo(Integer value) {
            addCriterion("roleid <=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidIn(List<Integer> values) {
            addCriterion("roleid in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotIn(List<Integer> values) {
            addCriterion("roleid not in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidBetween(Integer value1, Integer value2) {
            addCriterion("roleid between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotBetween(Integer value1, Integer value2) {
            addCriterion("roleid not between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andMenukeyIsNull() {
            addCriterion("menukey is null");
            return (Criteria) this;
        }

        public Criteria andMenukeyIsNotNull() {
            addCriterion("menukey is not null");
            return (Criteria) this;
        }

        public Criteria andMenukeyEqualTo(String value) {
            addCriterion("menukey =", value, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyNotEqualTo(String value) {
            addCriterion("menukey <>", value, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyGreaterThan(String value) {
            addCriterion("menukey >", value, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyGreaterThanOrEqualTo(String value) {
            addCriterion("menukey >=", value, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyLessThan(String value) {
            addCriterion("menukey <", value, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyLessThanOrEqualTo(String value) {
            addCriterion("menukey <=", value, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyLike(String value) {
            addCriterion("menukey like", value, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyNotLike(String value) {
            addCriterion("menukey not like", value, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyIn(List<String> values) {
            addCriterion("menukey in", values, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyNotIn(List<String> values) {
            addCriterion("menukey not in", values, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyBetween(String value1, String value2) {
            addCriterion("menukey between", value1, value2, "menukey");
            return (Criteria) this;
        }

        public Criteria andMenukeyNotBetween(String value1, String value2) {
            addCriterion("menukey not between", value1, value2, "menukey");
            return (Criteria) this;
        }

        public Criteria andActionsIsNull() {
            addCriterion("actions is null");
            return (Criteria) this;
        }

        public Criteria andActionsIsNotNull() {
            addCriterion("actions is not null");
            return (Criteria) this;
        }

        public Criteria andActionsEqualTo(String value) {
            addCriterion("actions =", value, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsNotEqualTo(String value) {
            addCriterion("actions <>", value, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsGreaterThan(String value) {
            addCriterion("actions >", value, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsGreaterThanOrEqualTo(String value) {
            addCriterion("actions >=", value, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsLessThan(String value) {
            addCriterion("actions <", value, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsLessThanOrEqualTo(String value) {
            addCriterion("actions <=", value, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsLike(String value) {
            addCriterion("actions like", value, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsNotLike(String value) {
            addCriterion("actions not like", value, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsIn(List<String> values) {
            addCriterion("actions in", values, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsNotIn(List<String> values) {
            addCriterion("actions not in", values, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsBetween(String value1, String value2) {
            addCriterion("actions between", value1, value2, "actions");
            return (Criteria) this;
        }

        public Criteria andActionsNotBetween(String value1, String value2) {
            addCriterion("actions not between", value1, value2, "actions");
            return (Criteria) this;
        }

        public Criteria andDatascopeIsNull() {
            addCriterion("datascope is null");
            return (Criteria) this;
        }

        public Criteria andDatascopeIsNotNull() {
            addCriterion("datascope is not null");
            return (Criteria) this;
        }

        public Criteria andDatascopeEqualTo(Integer value) {
            addCriterion("datascope =", value, "datascope");
            return (Criteria) this;
        }

        public Criteria andDatascopeNotEqualTo(Integer value) {
            addCriterion("datascope <>", value, "datascope");
            return (Criteria) this;
        }

        public Criteria andDatascopeGreaterThan(Integer value) {
            addCriterion("datascope >", value, "datascope");
            return (Criteria) this;
        }

        public Criteria andDatascopeGreaterThanOrEqualTo(Integer value) {
            addCriterion("datascope >=", value, "datascope");
            return (Criteria) this;
        }

        public Criteria andDatascopeLessThan(Integer value) {
            addCriterion("datascope <", value, "datascope");
            return (Criteria) this;
        }

        public Criteria andDatascopeLessThanOrEqualTo(Integer value) {
            addCriterion("datascope <=", value, "datascope");
            return (Criteria) this;
        }

        public Criteria andDatascopeIn(List<Integer> values) {
            addCriterion("datascope in", values, "datascope");
            return (Criteria) this;
        }

        public Criteria andDatascopeNotIn(List<Integer> values) {
            addCriterion("datascope not in", values, "datascope");
            return (Criteria) this;
        }

        public Criteria andDatascopeBetween(Integer value1, Integer value2) {
            addCriterion("datascope between", value1, value2, "datascope");
            return (Criteria) this;
        }

        public Criteria andDatascopeNotBetween(Integer value1, Integer value2) {
            addCriterion("datascope not between", value1, value2, "datascope");
            return (Criteria) this;
        }

        public Criteria andHospitalidsIsNull() {
            addCriterion("hospitalids is null");
            return (Criteria) this;
        }

        public Criteria andHospitalidsIsNotNull() {
            addCriterion("hospitalids is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalidsEqualTo(String value) {
            addCriterion("hospitalids =", value, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsNotEqualTo(String value) {
            addCriterion("hospitalids <>", value, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsGreaterThan(String value) {
            addCriterion("hospitalids >", value, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsGreaterThanOrEqualTo(String value) {
            addCriterion("hospitalids >=", value, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsLessThan(String value) {
            addCriterion("hospitalids <", value, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsLessThanOrEqualTo(String value) {
            addCriterion("hospitalids <=", value, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsLike(String value) {
            addCriterion("hospitalids like", value, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsNotLike(String value) {
            addCriterion("hospitalids not like", value, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsIn(List<String> values) {
            addCriterion("hospitalids in", values, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsNotIn(List<String> values) {
            addCriterion("hospitalids not in", values, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsBetween(String value1, String value2) {
            addCriterion("hospitalids between", value1, value2, "hospitalids");
            return (Criteria) this;
        }

        public Criteria andHospitalidsNotBetween(String value1, String value2) {
            addCriterion("hospitalids not between", value1, value2, "hospitalids");
            return (Criteria) this;
        }
    }

    /**
     * null<p/>
     * sys_role_permission
     * @date Sun Apr 12 20:01:53 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * SysRolePermission<p/>
     * sys_role_permission
     * @date Sun Apr 12 20:01:53 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}