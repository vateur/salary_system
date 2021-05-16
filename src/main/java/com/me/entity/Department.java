package com.me.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Department {

    @XmlElement(name = "month")
    private List<Month> months = new ArrayList<>();

    @XmlTransient
    public List<Month> getMonths() {
        return months;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }
}
