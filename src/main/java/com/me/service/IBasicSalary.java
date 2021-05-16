package com.me.service;


import com.me.entity.Employee;

import java.math.BigDecimal;

/**
 * 基础工资接口
 */
public interface IBasicSalary {

    /**
     * 计算基础工资
     *
     * @param employee 员工信息
     * @return 工资
     */
    BigDecimal calcBasicSalary(Employee employee);
}
