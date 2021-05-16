package com.me.service.impl;

import com.me.entity.Employee;
import com.me.service.IBasicSalary;
import com.me.util.Utils;

import java.math.BigDecimal;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 销售人员工资
 */
public class SaleEmployeeSalary implements IBasicSalary {

    /**
     * 底薪
     */
    private double basicSalary;

    /**
     * 提成率
     */
    private Map<Double, Double> percentages;

    public SaleEmployeeSalary(double basicSalary, Map<Double, Double> percentages) {
        this.basicSalary = basicSalary;
        this.percentages = percentages;
    }

    @Override
    public BigDecimal calcBasicSalary(Employee employee) {
        double amount = employee.getAmount();
        double basicAmount = 20000L;
        double percentageSalary = 0;
        if (amount > basicAmount) {
            Map<Double, Double> sortedMap = new LinkedHashMap<>();
            percentages.entrySet().stream().sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(e -> sortedMap.put(e.getKey(), e.getValue()));
            double actualPercentage = 0;
            for (Map.Entry<Double, Double> entry : sortedMap.entrySet()) {
                double key = entry.getKey();
                if (amount > key) {
                    actualPercentage = entry.getValue();
                }
            }
            percentageSalary = (amount - basicAmount) * actualPercentage;
        }
        return Utils.setScale(BigDecimal.valueOf((basicSalary + percentageSalary)));
    }
}
