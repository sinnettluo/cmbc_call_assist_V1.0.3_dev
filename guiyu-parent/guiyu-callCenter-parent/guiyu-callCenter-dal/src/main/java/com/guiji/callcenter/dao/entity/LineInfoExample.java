package com.guiji.callcenter.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class LineInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public LineInfoExample() {
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

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

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

        public Criteria andLineIdIsNull() {
            addCriterion("line_id is null");
            return (Criteria) this;
        }

        public Criteria andLineIdIsNotNull() {
            addCriterion("line_id is not null");
            return (Criteria) this;
        }

        public Criteria andLineIdEqualTo(Integer value) {
            addCriterion("line_id =", value, "lineId");
            return (Criteria) this;
        }

        public Criteria andLineIdNotEqualTo(Integer value) {
            addCriterion("line_id <>", value, "lineId");
            return (Criteria) this;
        }

        public Criteria andLineIdGreaterThan(Integer value) {
            addCriterion("line_id >", value, "lineId");
            return (Criteria) this;
        }

        public Criteria andLineIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("line_id >=", value, "lineId");
            return (Criteria) this;
        }

        public Criteria andLineIdLessThan(Integer value) {
            addCriterion("line_id <", value, "lineId");
            return (Criteria) this;
        }

        public Criteria andLineIdLessThanOrEqualTo(Integer value) {
            addCriterion("line_id <=", value, "lineId");
            return (Criteria) this;
        }

        public Criteria andLineIdIn(List<Integer> values) {
            addCriterion("line_id in", values, "lineId");
            return (Criteria) this;
        }

        public Criteria andLineIdNotIn(List<Integer> values) {
            addCriterion("line_id not in", values, "lineId");
            return (Criteria) this;
        }

        public Criteria andLineIdBetween(Integer value1, Integer value2) {
            addCriterion("line_id between", value1, value2, "lineId");
            return (Criteria) this;
        }

        public Criteria andLineIdNotBetween(Integer value1, Integer value2) {
            addCriterion("line_id not between", value1, value2, "lineId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(String value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(String value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(String value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(String value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLike(String value) {
            addCriterion("customer_id like", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotLike(String value) {
            addCriterion("customer_id not like", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<String> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<String> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(String value1, String value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(String value1, String value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andLineNameIsNull() {
            addCriterion("line_name is null");
            return (Criteria) this;
        }

        public Criteria andLineNameIsNotNull() {
            addCriterion("line_name is not null");
            return (Criteria) this;
        }

        public Criteria andLineNameEqualTo(String value) {
            addCriterion("line_name =", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotEqualTo(String value) {
            addCriterion("line_name <>", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameGreaterThan(String value) {
            addCriterion("line_name >", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameGreaterThanOrEqualTo(String value) {
            addCriterion("line_name >=", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLessThan(String value) {
            addCriterion("line_name <", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLessThanOrEqualTo(String value) {
            addCriterion("line_name <=", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLike(String value) {
            addCriterion("line_name like", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotLike(String value) {
            addCriterion("line_name not like", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameIn(List<String> values) {
            addCriterion("line_name in", values, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotIn(List<String> values) {
            addCriterion("line_name not in", values, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameBetween(String value1, String value2) {
            addCriterion("line_name between", value1, value2, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotBetween(String value1, String value2) {
            addCriterion("line_name not between", value1, value2, "lineName");
            return (Criteria) this;
        }

        public Criteria andSipIpIsNull() {
            addCriterion("sip_ip is null");
            return (Criteria) this;
        }

        public Criteria andSipIpIsNotNull() {
            addCriterion("sip_ip is not null");
            return (Criteria) this;
        }

        public Criteria andSipIpEqualTo(String value) {
            addCriterion("sip_ip =", value, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpNotEqualTo(String value) {
            addCriterion("sip_ip <>", value, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpGreaterThan(String value) {
            addCriterion("sip_ip >", value, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpGreaterThanOrEqualTo(String value) {
            addCriterion("sip_ip >=", value, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpLessThan(String value) {
            addCriterion("sip_ip <", value, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpLessThanOrEqualTo(String value) {
            addCriterion("sip_ip <=", value, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpLike(String value) {
            addCriterion("sip_ip like", value, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpNotLike(String value) {
            addCriterion("sip_ip not like", value, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpIn(List<String> values) {
            addCriterion("sip_ip in", values, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpNotIn(List<String> values) {
            addCriterion("sip_ip not in", values, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpBetween(String value1, String value2) {
            addCriterion("sip_ip between", value1, value2, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipIpNotBetween(String value1, String value2) {
            addCriterion("sip_ip not between", value1, value2, "sipIp");
            return (Criteria) this;
        }

        public Criteria andSipPortIsNull() {
            addCriterion("sip_port is null");
            return (Criteria) this;
        }

        public Criteria andSipPortIsNotNull() {
            addCriterion("sip_port is not null");
            return (Criteria) this;
        }

        public Criteria andSipPortEqualTo(String value) {
            addCriterion("sip_port =", value, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortNotEqualTo(String value) {
            addCriterion("sip_port <>", value, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortGreaterThan(String value) {
            addCriterion("sip_port >", value, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortGreaterThanOrEqualTo(String value) {
            addCriterion("sip_port >=", value, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortLessThan(String value) {
            addCriterion("sip_port <", value, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortLessThanOrEqualTo(String value) {
            addCriterion("sip_port <=", value, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortLike(String value) {
            addCriterion("sip_port like", value, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortNotLike(String value) {
            addCriterion("sip_port not like", value, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortIn(List<String> values) {
            addCriterion("sip_port in", values, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortNotIn(List<String> values) {
            addCriterion("sip_port not in", values, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortBetween(String value1, String value2) {
            addCriterion("sip_port between", value1, value2, "sipPort");
            return (Criteria) this;
        }

        public Criteria andSipPortNotBetween(String value1, String value2) {
            addCriterion("sip_port not between", value1, value2, "sipPort");
            return (Criteria) this;
        }

        public Criteria andCodecIsNull() {
            addCriterion("codec is null");
            return (Criteria) this;
        }

        public Criteria andCodecIsNotNull() {
            addCriterion("codec is not null");
            return (Criteria) this;
        }

        public Criteria andCodecEqualTo(String value) {
            addCriterion("codec =", value, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecNotEqualTo(String value) {
            addCriterion("codec <>", value, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecGreaterThan(String value) {
            addCriterion("codec >", value, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecGreaterThanOrEqualTo(String value) {
            addCriterion("codec >=", value, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecLessThan(String value) {
            addCriterion("codec <", value, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecLessThanOrEqualTo(String value) {
            addCriterion("codec <=", value, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecLike(String value) {
            addCriterion("codec like", value, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecNotLike(String value) {
            addCriterion("codec not like", value, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecIn(List<String> values) {
            addCriterion("codec in", values, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecNotIn(List<String> values) {
            addCriterion("codec not in", values, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecBetween(String value1, String value2) {
            addCriterion("codec between", value1, value2, "codec");
            return (Criteria) this;
        }

        public Criteria andCodecNotBetween(String value1, String value2) {
            addCriterion("codec not between", value1, value2, "codec");
            return (Criteria) this;
        }

        public Criteria andCallerNumIsNull() {
            addCriterion("caller_num is null");
            return (Criteria) this;
        }

        public Criteria andCallerNumIsNotNull() {
            addCriterion("caller_num is not null");
            return (Criteria) this;
        }

        public Criteria andCallerNumEqualTo(String value) {
            addCriterion("caller_num =", value, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumNotEqualTo(String value) {
            addCriterion("caller_num <>", value, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumGreaterThan(String value) {
            addCriterion("caller_num >", value, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumGreaterThanOrEqualTo(String value) {
            addCriterion("caller_num >=", value, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumLessThan(String value) {
            addCriterion("caller_num <", value, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumLessThanOrEqualTo(String value) {
            addCriterion("caller_num <=", value, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumLike(String value) {
            addCriterion("caller_num like", value, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumNotLike(String value) {
            addCriterion("caller_num not like", value, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumIn(List<String> values) {
            addCriterion("caller_num in", values, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumNotIn(List<String> values) {
            addCriterion("caller_num not in", values, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumBetween(String value1, String value2) {
            addCriterion("caller_num between", value1, value2, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCallerNumNotBetween(String value1, String value2) {
            addCriterion("caller_num not between", value1, value2, "callerNum");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixIsNull() {
            addCriterion("callee_prefix is null");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixIsNotNull() {
            addCriterion("callee_prefix is not null");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixEqualTo(String value) {
            addCriterion("callee_prefix =", value, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixNotEqualTo(String value) {
            addCriterion("callee_prefix <>", value, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixGreaterThan(String value) {
            addCriterion("callee_prefix >", value, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixGreaterThanOrEqualTo(String value) {
            addCriterion("callee_prefix >=", value, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixLessThan(String value) {
            addCriterion("callee_prefix <", value, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixLessThanOrEqualTo(String value) {
            addCriterion("callee_prefix <=", value, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixLike(String value) {
            addCriterion("callee_prefix like", value, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixNotLike(String value) {
            addCriterion("callee_prefix not like", value, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixIn(List<String> values) {
            addCriterion("callee_prefix in", values, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixNotIn(List<String> values) {
            addCriterion("callee_prefix not in", values, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixBetween(String value1, String value2) {
            addCriterion("callee_prefix between", value1, value2, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andCalleePrefixNotBetween(String value1, String value2) {
            addCriterion("callee_prefix not between", value1, value2, "calleePrefix");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsIsNull() {
            addCriterion("max_concurrent_calls is null");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsIsNotNull() {
            addCriterion("max_concurrent_calls is not null");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsEqualTo(Integer value) {
            addCriterion("max_concurrent_calls =", value, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsNotEqualTo(Integer value) {
            addCriterion("max_concurrent_calls <>", value, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsGreaterThan(Integer value) {
            addCriterion("max_concurrent_calls >", value, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_concurrent_calls >=", value, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsLessThan(Integer value) {
            addCriterion("max_concurrent_calls <", value, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsLessThanOrEqualTo(Integer value) {
            addCriterion("max_concurrent_calls <=", value, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsIn(List<Integer> values) {
            addCriterion("max_concurrent_calls in", values, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsNotIn(List<Integer> values) {
            addCriterion("max_concurrent_calls not in", values, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsBetween(Integer value1, Integer value2) {
            addCriterion("max_concurrent_calls between", value1, value2, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentCallsNotBetween(Integer value1, Integer value2) {
            addCriterion("max_concurrent_calls not between", value1, value2, "maxConcurrentCalls");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(String value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(String value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(String value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(String value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(String value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(String value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLike(String value) {
            addCriterion("create_date like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotLike(String value) {
            addCriterion("create_date not like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<String> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<String> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(String value1, String value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(String value1, String value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(String value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(String value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(String value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(String value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(String value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(String value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLike(String value) {
            addCriterion("update_date like", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotLike(String value) {
            addCriterion("update_date not like", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<String> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<String> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(String value1, String value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(String value1, String value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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