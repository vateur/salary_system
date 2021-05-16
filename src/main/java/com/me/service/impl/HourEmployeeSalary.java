package com.me.service.impl;

import com.me.entity.Employee;
import com.me.service.IBasicSalary;
import com.me.util.Utils;

import java.math.BigDecimal;

/**
 * 小时员工工资
 */
public class HourEmployeeSalary implements IBasicSalary {

    /**
     * 每小时工资
     */
    private double hourSalary;
    /**
     * 标准工时
     */
    private double standardHour;
    /**
     * 工时超出后的工资倍数
     */
    private double multiple;

    public HourEmployeeSalary(double hourSalary, double standardHour, double multiple) {
        this.hourSalary = hourSalary;
        this.standardHour = standardHour;
        this.multiple = multiple;
    }

    @Override
    public BigDecimal calcBasicSalary(Employee employee) {
        double workingHours = employee.getWorkingHours();
        if (workingHours <= standardHour) {
            return Utils.setScale(BigDecimal.valueOf(hourSalary * workingHours));
        }
        return Utils.setScale(BigDecimal.valueOf((hourSalary * standardHour) + (workingHours - standardHour) * hourSalary * multiple));
    }
}
