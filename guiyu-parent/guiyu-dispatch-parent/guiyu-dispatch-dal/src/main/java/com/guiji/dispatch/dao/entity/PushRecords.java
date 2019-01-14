package com.guiji.dispatch.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class PushRecords implements Serializable {
    private Long id;

    private String planuuid;

    private String phone;

    private Date createTime;

    private Integer callbackStatus;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanuuid() {
        return planuuid;
    }

    public void setPlanuuid(String planuuid) {
        this.planuuid = planuuid == null ? null : planuuid.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCallbackStatus() {
        return callbackStatus;
    }

    public void setCallbackStatus(Integer callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", planuuid=").append(planuuid);
        sb.append(", phone=").append(phone);
        sb.append(", createTime=").append(createTime);
        sb.append(", callbackStatus=").append(callbackStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}