package com.me.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@XmlRootElement
public class Employee {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "birthday")
    private Date birthday;

    @XmlAttribute(name = "amount")
    private double amount;

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "workingHours")
    private double workingHours;

    @XmlTransient
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @XmlTransient
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
