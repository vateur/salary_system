package com.me.service.impl;

import com.me.entity.Employee;
import com.me.service.IBasicSalary;

import java.math.BigDecimal;

/**
 * 固定工资员工
 */
public class SalaryEmployeeSalary implements IBasicSalary {

    /**
     * 固定工资
     */
    private double salary;

    public SalaryEmployeeSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public BigDecimal calcBasicSalary(Employee employee) {
        return new BigDecimal(salary);
    }
}
