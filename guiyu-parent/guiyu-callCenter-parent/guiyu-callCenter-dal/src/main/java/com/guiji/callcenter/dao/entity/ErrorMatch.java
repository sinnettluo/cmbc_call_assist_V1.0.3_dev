package com.guiji.callcenter.dao.entity;

import java.io.Serializable;

public class ErrorMatch implements Serializable {
    private Integer errorType;

    private String keyWord;

    private String errorName;

    private static final long serialVersionUID = 1L;

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName == null ? null : errorName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", errorType=").append(errorType);
        sb.append(", keyWord=").append(keyWord);
        sb.append(", errorName=").append(errorName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}