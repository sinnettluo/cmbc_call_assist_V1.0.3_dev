package com.guiji.callcenter.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class Queue implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column queue.queue_id
     *
     * @mbggenerated
     */
    private Long queueId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column queue.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column queue.creator
     *
     * @mbggenerated
     */
    private Long creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column queue.queue_name
     *
     * @mbggenerated
     */
    private String queueName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column queue.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column queue.update_user
     *
     * @mbggenerated
     */
    private Long updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column queue.org_code
     *
     * @mbggenerated
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column queue.line_id
     *
     * @mbggenerated
     */
    private Integer lineId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table queue
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column queue.queue_id
     *
     * @return the value of queue.queue_id
     *
     * @mbggenerated
     */
    public Long getQueueId() {
        return queueId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column queue.queue_id
     *
     * @param queueId the value for queue.queue_id
     *
     * @mbggenerated
     */
    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column queue.create_time
     *
     * @return the value of queue.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column queue.create_time
     *
     * @param createTime the value for queue.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column queue.creator
     *
     * @return the value of queue.creator
     *
     * @mbggenerated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column queue.creator
     *
     * @param creator the value for queue.creator
     *
     * @mbggenerated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column queue.queue_name
     *
     * @return the value of queue.queue_name
     *
     * @mbggenerated
     */
    public String getQueueName() {
        return queueName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column queue.queue_name
     *
     * @param queueName the value for queue.queue_name
     *
     * @mbggenerated
     */
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column queue.update_time
     *
     * @return the value of queue.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column queue.update_time
     *
     * @param updateTime the value for queue.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column queue.update_user
     *
     * @return the value of queue.update_user
     *
     * @mbggenerated
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column queue.update_user
     *
     * @param updateUser the value for queue.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column queue.org_code
     *
     * @return the value of queue.org_code
     *
     * @mbggenerated
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column queue.org_code
     *
     * @param orgCode the value for queue.org_code
     *
     * @mbggenerated
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column queue.line_id
     *
     * @return the value of queue.line_id
     *
     * @mbggenerated
     */
    public Integer getLineId() {
        return lineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column queue.line_id
     *
     * @param lineId the value for queue.line_id
     *
     * @mbggenerated
     */
    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }
}