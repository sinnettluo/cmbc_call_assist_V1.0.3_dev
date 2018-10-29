package com.guiji.callcenter.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallOutPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public CallOutPlanExample() {
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

        public Criteria andCallIdIsNull() {
            addCriterion("call_id is null");
            return (Criteria) this;
        }

        public Criteria andCallIdIsNotNull() {
            addCriterion("call_id is not null");
            return (Criteria) this;
        }

        public Criteria andCallIdEqualTo(String value) {
            addCriterion("call_id =", value, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdNotEqualTo(String value) {
            addCriterion("call_id <>", value, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdGreaterThan(String value) {
            addCriterion("call_id >", value, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdGreaterThanOrEqualTo(String value) {
            addCriterion("call_id >=", value, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdLessThan(String value) {
            addCriterion("call_id <", value, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdLessThanOrEqualTo(String value) {
            addCriterion("call_id <=", value, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdLike(String value) {
            addCriterion("call_id like", value, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdNotLike(String value) {
            addCriterion("call_id not like", value, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdIn(List<String> values) {
            addCriterion("call_id in", values, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdNotIn(List<String> values) {
            addCriterion("call_id not in", values, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdBetween(String value1, String value2) {
            addCriterion("call_id between", value1, value2, "callId");
            return (Criteria) this;
        }

        public Criteria andCallIdNotBetween(String value1, String value2) {
            addCriterion("call_id not between", value1, value2, "callId");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNull() {
            addCriterion("phone_num is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion("phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion("phone_num =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion("phone_num <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion("phone_num >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("phone_num >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion("phone_num <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("phone_num <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion("phone_num like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion("phone_num not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion("phone_num in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion("phone_num not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion("phone_num between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion("phone_num not between", value1, value2, "phoneNum");
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

        public Criteria andTempIdIsNull() {
            addCriterion("temp_id is null");
            return (Criteria) this;
        }

        public Criteria andTempIdIsNotNull() {
            addCriterion("temp_id is not null");
            return (Criteria) this;
        }

        public Criteria andTempIdEqualTo(String value) {
            addCriterion("temp_id =", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotEqualTo(String value) {
            addCriterion("temp_id <>", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThan(String value) {
            addCriterion("temp_id >", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThanOrEqualTo(String value) {
            addCriterion("temp_id >=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThan(String value) {
            addCriterion("temp_id <", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThanOrEqualTo(String value) {
            addCriterion("temp_id <=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLike(String value) {
            addCriterion("temp_id like", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotLike(String value) {
            addCriterion("temp_id not like", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdIn(List<String> values) {
            addCriterion("temp_id in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotIn(List<String> values) {
            addCriterion("temp_id not in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdBetween(String value1, String value2) {
            addCriterion("temp_id between", value1, value2, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotBetween(String value1, String value2) {
            addCriterion("temp_id not between", value1, value2, "tempId");
            return (Criteria) this;
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

        public Criteria andServeridIsNull() {
            addCriterion("serverId is null");
            return (Criteria) this;
        }

        public Criteria andServeridIsNotNull() {
            addCriterion("serverId is not null");
            return (Criteria) this;
        }

        public Criteria andServeridEqualTo(String value) {
            addCriterion("serverId =", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridNotEqualTo(String value) {
            addCriterion("serverId <>", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridGreaterThan(String value) {
            addCriterion("serverId >", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridGreaterThanOrEqualTo(String value) {
            addCriterion("serverId >=", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridLessThan(String value) {
            addCriterion("serverId <", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridLessThanOrEqualTo(String value) {
            addCriterion("serverId <=", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridLike(String value) {
            addCriterion("serverId like", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridNotLike(String value) {
            addCriterion("serverId not like", value, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridIn(List<String> values) {
            addCriterion("serverId in", values, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridNotIn(List<String> values) {
            addCriterion("serverId not in", values, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridBetween(String value1, String value2) {
            addCriterion("serverId between", value1, value2, "serverid");
            return (Criteria) this;
        }

        public Criteria andServeridNotBetween(String value1, String value2) {
            addCriterion("serverId not between", value1, value2, "serverid");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNull() {
            addCriterion("agent_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("agent_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(String value) {
            addCriterion("agent_id =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(String value) {
            addCriterion("agent_id <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(String value) {
            addCriterion("agent_id >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(String value) {
            addCriterion("agent_id >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(String value) {
            addCriterion("agent_id <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(String value) {
            addCriterion("agent_id <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLike(String value) {
            addCriterion("agent_id like", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotLike(String value) {
            addCriterion("agent_id not like", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<String> values) {
            addCriterion("agent_id in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<String> values) {
            addCriterion("agent_id not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(String value1, String value2) {
            addCriterion("agent_id between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(String value1, String value2) {
            addCriterion("agent_id not between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeIsNull() {
            addCriterion("agent_answer_time is null");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeIsNotNull() {
            addCriterion("agent_answer_time is not null");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeEqualTo(Date value) {
            addCriterion("agent_answer_time =", value, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeNotEqualTo(Date value) {
            addCriterion("agent_answer_time <>", value, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeGreaterThan(Date value) {
            addCriterion("agent_answer_time >", value, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("agent_answer_time >=", value, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeLessThan(Date value) {
            addCriterion("agent_answer_time <", value, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeLessThanOrEqualTo(Date value) {
            addCriterion("agent_answer_time <=", value, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeIn(List<Date> values) {
            addCriterion("agent_answer_time in", values, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeNotIn(List<Date> values) {
            addCriterion("agent_answer_time not in", values, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeBetween(Date value1, Date value2) {
            addCriterion("agent_answer_time between", value1, value2, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentAnswerTimeNotBetween(Date value1, Date value2) {
            addCriterion("agent_answer_time not between", value1, value2, "agentAnswerTime");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidIsNull() {
            addCriterion("agent_channel_uuid is null");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidIsNotNull() {
            addCriterion("agent_channel_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidEqualTo(String value) {
            addCriterion("agent_channel_uuid =", value, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidNotEqualTo(String value) {
            addCriterion("agent_channel_uuid <>", value, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidGreaterThan(String value) {
            addCriterion("agent_channel_uuid >", value, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidGreaterThanOrEqualTo(String value) {
            addCriterion("agent_channel_uuid >=", value, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidLessThan(String value) {
            addCriterion("agent_channel_uuid <", value, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidLessThanOrEqualTo(String value) {
            addCriterion("agent_channel_uuid <=", value, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidLike(String value) {
            addCriterion("agent_channel_uuid like", value, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidNotLike(String value) {
            addCriterion("agent_channel_uuid not like", value, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidIn(List<String> values) {
            addCriterion("agent_channel_uuid in", values, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidNotIn(List<String> values) {
            addCriterion("agent_channel_uuid not in", values, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidBetween(String value1, String value2) {
            addCriterion("agent_channel_uuid between", value1, value2, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentChannelUuidNotBetween(String value1, String value2) {
            addCriterion("agent_channel_uuid not between", value1, value2, "agentChannelUuid");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdIsNull() {
            addCriterion("agent_group_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdIsNotNull() {
            addCriterion("agent_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdEqualTo(String value) {
            addCriterion("agent_group_id =", value, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdNotEqualTo(String value) {
            addCriterion("agent_group_id <>", value, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdGreaterThan(String value) {
            addCriterion("agent_group_id >", value, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("agent_group_id >=", value, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdLessThan(String value) {
            addCriterion("agent_group_id <", value, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdLessThanOrEqualTo(String value) {
            addCriterion("agent_group_id <=", value, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdLike(String value) {
            addCriterion("agent_group_id like", value, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdNotLike(String value) {
            addCriterion("agent_group_id not like", value, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdIn(List<String> values) {
            addCriterion("agent_group_id in", values, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdNotIn(List<String> values) {
            addCriterion("agent_group_id not in", values, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdBetween(String value1, String value2) {
            addCriterion("agent_group_id between", value1, value2, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentGroupIdNotBetween(String value1, String value2) {
            addCriterion("agent_group_id not between", value1, value2, "agentGroupId");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeIsNull() {
            addCriterion("agent_start_time is null");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeIsNotNull() {
            addCriterion("agent_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeEqualTo(Date value) {
            addCriterion("agent_start_time =", value, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeNotEqualTo(Date value) {
            addCriterion("agent_start_time <>", value, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeGreaterThan(Date value) {
            addCriterion("agent_start_time >", value, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("agent_start_time >=", value, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeLessThan(Date value) {
            addCriterion("agent_start_time <", value, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("agent_start_time <=", value, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeIn(List<Date> values) {
            addCriterion("agent_start_time in", values, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeNotIn(List<Date> values) {
            addCriterion("agent_start_time not in", values, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeBetween(Date value1, Date value2) {
            addCriterion("agent_start_time between", value1, value2, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andAgentStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("agent_start_time not between", value1, value2, "agentStartTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeIsNull() {
            addCriterion("schedule_time is null");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeIsNotNull() {
            addCriterion("schedule_time is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeEqualTo(Date value) {
            addCriterion("schedule_time =", value, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeNotEqualTo(Date value) {
            addCriterion("schedule_time <>", value, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeGreaterThan(Date value) {
            addCriterion("schedule_time >", value, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("schedule_time >=", value, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeLessThan(Date value) {
            addCriterion("schedule_time <", value, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeLessThanOrEqualTo(Date value) {
            addCriterion("schedule_time <=", value, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeIn(List<Date> values) {
            addCriterion("schedule_time in", values, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeNotIn(List<Date> values) {
            addCriterion("schedule_time not in", values, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeBetween(Date value1, Date value2) {
            addCriterion("schedule_time between", value1, value2, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andScheduleTimeNotBetween(Date value1, Date value2) {
            addCriterion("schedule_time not between", value1, value2, "scheduleTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeIsNull() {
            addCriterion("hangup_time is null");
            return (Criteria) this;
        }

        public Criteria andHangupTimeIsNotNull() {
            addCriterion("hangup_time is not null");
            return (Criteria) this;
        }

        public Criteria andHangupTimeEqualTo(Date value) {
            addCriterion("hangup_time =", value, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeNotEqualTo(Date value) {
            addCriterion("hangup_time <>", value, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeGreaterThan(Date value) {
            addCriterion("hangup_time >", value, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("hangup_time >=", value, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeLessThan(Date value) {
            addCriterion("hangup_time <", value, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeLessThanOrEqualTo(Date value) {
            addCriterion("hangup_time <=", value, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeIn(List<Date> values) {
            addCriterion("hangup_time in", values, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeNotIn(List<Date> values) {
            addCriterion("hangup_time not in", values, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeBetween(Date value1, Date value2) {
            addCriterion("hangup_time between", value1, value2, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andHangupTimeNotBetween(Date value1, Date value2) {
            addCriterion("hangup_time not between", value1, value2, "hangupTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIsNull() {
            addCriterion("answer_time is null");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIsNotNull() {
            addCriterion("answer_time is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeEqualTo(Date value) {
            addCriterion("answer_time =", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotEqualTo(Date value) {
            addCriterion("answer_time <>", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeGreaterThan(Date value) {
            addCriterion("answer_time >", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("answer_time >=", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeLessThan(Date value) {
            addCriterion("answer_time <", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeLessThanOrEqualTo(Date value) {
            addCriterion("answer_time <=", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIn(List<Date> values) {
            addCriterion("answer_time in", values, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotIn(List<Date> values) {
            addCriterion("answer_time not in", values, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeBetween(Date value1, Date value2) {
            addCriterion("answer_time between", value1, value2, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotBetween(Date value1, Date value2) {
            addCriterion("answer_time not between", value1, value2, "answerTime");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Integer value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Integer value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Integer value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Integer value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Integer value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Integer> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Integer> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Integer value1, Integer value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andBillSecIsNull() {
            addCriterion("bill_sec is null");
            return (Criteria) this;
        }

        public Criteria andBillSecIsNotNull() {
            addCriterion("bill_sec is not null");
            return (Criteria) this;
        }

        public Criteria andBillSecEqualTo(Integer value) {
            addCriterion("bill_sec =", value, "billSec");
            return (Criteria) this;
        }

        public Criteria andBillSecNotEqualTo(Integer value) {
            addCriterion("bill_sec <>", value, "billSec");
            return (Criteria) this;
        }

        public Criteria andBillSecGreaterThan(Integer value) {
            addCriterion("bill_sec >", value, "billSec");
            return (Criteria) this;
        }

        public Criteria andBillSecGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_sec >=", value, "billSec");
            return (Criteria) this;
        }

        public Criteria andBillSecLessThan(Integer value) {
            addCriterion("bill_sec <", value, "billSec");
            return (Criteria) this;
        }

        public Criteria andBillSecLessThanOrEqualTo(Integer value) {
            addCriterion("bill_sec <=", value, "billSec");
            return (Criteria) this;
        }

        public Criteria andBillSecIn(List<Integer> values) {
            addCriterion("bill_sec in", values, "billSec");
            return (Criteria) this;
        }

        public Criteria andBillSecNotIn(List<Integer> values) {
            addCriterion("bill_sec not in", values, "billSec");
            return (Criteria) this;
        }

        public Criteria andBillSecBetween(Integer value1, Integer value2) {
            addCriterion("bill_sec between", value1, value2, "billSec");
            return (Criteria) this;
        }

        public Criteria andBillSecNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_sec not between", value1, value2, "billSec");
            return (Criteria) this;
        }

        public Criteria andCallDirectionIsNull() {
            addCriterion("call_direction is null");
            return (Criteria) this;
        }

        public Criteria andCallDirectionIsNotNull() {
            addCriterion("call_direction is not null");
            return (Criteria) this;
        }

        public Criteria andCallDirectionEqualTo(Integer value) {
            addCriterion("call_direction =", value, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallDirectionNotEqualTo(Integer value) {
            addCriterion("call_direction <>", value, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallDirectionGreaterThan(Integer value) {
            addCriterion("call_direction >", value, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallDirectionGreaterThanOrEqualTo(Integer value) {
            addCriterion("call_direction >=", value, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallDirectionLessThan(Integer value) {
            addCriterion("call_direction <", value, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallDirectionLessThanOrEqualTo(Integer value) {
            addCriterion("call_direction <=", value, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallDirectionIn(List<Integer> values) {
            addCriterion("call_direction in", values, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallDirectionNotIn(List<Integer> values) {
            addCriterion("call_direction not in", values, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallDirectionBetween(Integer value1, Integer value2) {
            addCriterion("call_direction between", value1, value2, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallDirectionNotBetween(Integer value1, Integer value2) {
            addCriterion("call_direction not between", value1, value2, "callDirection");
            return (Criteria) this;
        }

        public Criteria andCallStateIsNull() {
            addCriterion("call_state is null");
            return (Criteria) this;
        }

        public Criteria andCallStateIsNotNull() {
            addCriterion("call_state is not null");
            return (Criteria) this;
        }

        public Criteria andCallStateEqualTo(Integer value) {
            addCriterion("call_state =", value, "callState");
            return (Criteria) this;
        }

        public Criteria andCallStateNotEqualTo(Integer value) {
            addCriterion("call_state <>", value, "callState");
            return (Criteria) this;
        }

        public Criteria andCallStateGreaterThan(Integer value) {
            addCriterion("call_state >", value, "callState");
            return (Criteria) this;
        }

        public Criteria andCallStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("call_state >=", value, "callState");
            return (Criteria) this;
        }

        public Criteria andCallStateLessThan(Integer value) {
            addCriterion("call_state <", value, "callState");
            return (Criteria) this;
        }

        public Criteria andCallStateLessThanOrEqualTo(Integer value) {
            addCriterion("call_state <=", value, "callState");
            return (Criteria) this;
        }

        public Criteria andCallStateIn(List<Integer> values) {
            addCriterion("call_state in", values, "callState");
            return (Criteria) this;
        }

        public Criteria andCallStateNotIn(List<Integer> values) {
            addCriterion("call_state not in", values, "callState");
            return (Criteria) this;
        }

        public Criteria andCallStateBetween(Integer value1, Integer value2) {
            addCriterion("call_state between", value1, value2, "callState");
            return (Criteria) this;
        }

        public Criteria andCallStateNotBetween(Integer value1, Integer value2) {
            addCriterion("call_state not between", value1, value2, "callState");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionIsNull() {
            addCriterion("hangup_direction is null");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionIsNotNull() {
            addCriterion("hangup_direction is not null");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionEqualTo(Integer value) {
            addCriterion("hangup_direction =", value, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionNotEqualTo(Integer value) {
            addCriterion("hangup_direction <>", value, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionGreaterThan(Integer value) {
            addCriterion("hangup_direction >", value, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionGreaterThanOrEqualTo(Integer value) {
            addCriterion("hangup_direction >=", value, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionLessThan(Integer value) {
            addCriterion("hangup_direction <", value, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionLessThanOrEqualTo(Integer value) {
            addCriterion("hangup_direction <=", value, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionIn(List<Integer> values) {
            addCriterion("hangup_direction in", values, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionNotIn(List<Integer> values) {
            addCriterion("hangup_direction not in", values, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionBetween(Integer value1, Integer value2) {
            addCriterion("hangup_direction between", value1, value2, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andHangupDirectionNotBetween(Integer value1, Integer value2) {
            addCriterion("hangup_direction not between", value1, value2, "hangupDirection");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​IsNull() {
            addCriterion("accurate_intent​ is null");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​IsNotNull() {
            addCriterion("accurate_intent​ is not null");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​EqualTo(String value) {
            addCriterion("accurate_intent​ =", value, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​NotEqualTo(String value) {
            addCriterion("accurate_intent​ <>", value, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​GreaterThan(String value) {
            addCriterion("accurate_intent​ >", value, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​GreaterThanOrEqualTo(String value) {
            addCriterion("accurate_intent​ >=", value, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​LessThan(String value) {
            addCriterion("accurate_intent​ <", value, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​LessThanOrEqualTo(String value) {
            addCriterion("accurate_intent​ <=", value, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​Like(String value) {
            addCriterion("accurate_intent​ like", value, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​NotLike(String value) {
            addCriterion("accurate_intent​ not like", value, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​In(List<String> values) {
            addCriterion("accurate_intent​ in", values, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​NotIn(List<String> values) {
            addCriterion("accurate_intent​ not in", values, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​Between(String value1, String value2) {
            addCriterion("accurate_intent​ between", value1, value2, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andAccurateIntent​NotBetween(String value1, String value2) {
            addCriterion("accurate_intent​ not between", value1, value2, "accurateIntent​");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andHangupCodeIsNull() {
            addCriterion("hangup_code is null");
            return (Criteria) this;
        }

        public Criteria andHangupCodeIsNotNull() {
            addCriterion("hangup_code is not null");
            return (Criteria) this;
        }

        public Criteria andHangupCodeEqualTo(String value) {
            addCriterion("hangup_code =", value, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeNotEqualTo(String value) {
            addCriterion("hangup_code <>", value, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeGreaterThan(String value) {
            addCriterion("hangup_code >", value, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("hangup_code >=", value, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeLessThan(String value) {
            addCriterion("hangup_code <", value, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeLessThanOrEqualTo(String value) {
            addCriterion("hangup_code <=", value, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeLike(String value) {
            addCriterion("hangup_code like", value, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeNotLike(String value) {
            addCriterion("hangup_code not like", value, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeIn(List<String> values) {
            addCriterion("hangup_code in", values, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeNotIn(List<String> values) {
            addCriterion("hangup_code not in", values, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeBetween(String value1, String value2) {
            addCriterion("hangup_code between", value1, value2, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andHangupCodeNotBetween(String value1, String value2) {
            addCriterion("hangup_code not between", value1, value2, "hangupCode");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdIsNull() {
            addCriterion("originate_cmd is null");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdIsNotNull() {
            addCriterion("originate_cmd is not null");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdEqualTo(String value) {
            addCriterion("originate_cmd =", value, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdNotEqualTo(String value) {
            addCriterion("originate_cmd <>", value, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdGreaterThan(String value) {
            addCriterion("originate_cmd >", value, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdGreaterThanOrEqualTo(String value) {
            addCriterion("originate_cmd >=", value, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdLessThan(String value) {
            addCriterion("originate_cmd <", value, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdLessThanOrEqualTo(String value) {
            addCriterion("originate_cmd <=", value, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdLike(String value) {
            addCriterion("originate_cmd like", value, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdNotLike(String value) {
            addCriterion("originate_cmd not like", value, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdIn(List<String> values) {
            addCriterion("originate_cmd in", values, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdNotIn(List<String> values) {
            addCriterion("originate_cmd not in", values, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdBetween(String value1, String value2) {
            addCriterion("originate_cmd between", value1, value2, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andOriginateCmdNotBetween(String value1, String value2) {
            addCriterion("originate_cmd not between", value1, value2, "originateCmd");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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