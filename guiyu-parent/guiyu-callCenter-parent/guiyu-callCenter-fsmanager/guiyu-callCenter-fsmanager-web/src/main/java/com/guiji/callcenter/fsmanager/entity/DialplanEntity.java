package com.guiji.callcenter.fsmanager.entity;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.LinkedHashSet;

@Data
@XmlRootElement(name = "include")
@XmlAccessorType(XmlAccessType.FIELD)
public class DialplanEntity {
    @XmlElement(name = "extension")
    Extension extension;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public class Extension {
        @XmlAttribute(name = "name")
        String name;
        @XmlElement(name = "condition")
        Condition condition;
    }
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public class Condition {
        @XmlAttribute(name = "field")
        String field;
        @XmlAttribute(name = "expression")
        String expression;

        @XmlElement(name = "action")
        LinkedHashSet<Action> action;
    }
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public class Action {
        @XmlAttribute(name = "application")
        String application;
        @XmlAttribute(name = "data")
        String data;
    }
}