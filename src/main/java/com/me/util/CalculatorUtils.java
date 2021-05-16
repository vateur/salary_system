package com.me.util;

import com.me.entity.Department;
import com.me.entity.Employee;
import com.me.entity.Month;
import com.me.service.impl.HourEmployeeSalary;
import com.me.service.IBasicSalary;
import com.me.service.impl.SalaryEmployeeSalary;
import com.me.service.impl.SaleEmployeeSalary;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 计算工具类
 */
public class CalculatorUtils {

    private static IBasicSalary bs = null;

    /**
     * 计算总工资
     * @param department 解析实体类
     * @param configMap 配置数据
     * @return 总工资
     */
    public static BigDecimal getTotalSalary(Department department, Map<String,String> configMap) {

        double salary = Double.parseDouble(configMap.getOrDefault("salary","0"));
        double hourSalary = Double.parseDouble(configMap.getOrDefault("hourSalary","0"));
        double standardHour = Double.parseDouble(configMap.getOrDefault("standardHour","0"));
        double multiple = Double.parseDouble(configMap.getOrDefault("multiple","1"));
        double basicSalary = Double.parseDouble(configMap.getOrDefault("basicSalary","0"));
        double birthdaySalary = Double.parseDouble(configMap.getOrDefault("birthdaySalary","0"));
        String percentage = configMap.getOrDefault("percentage","");

        // 转换数据
        // key 为当前月份，value 为员工集合
        Map<Integer, List<Employee>> employeeMap = department.getMonths().stream().collect(Collectors.toMap(month -> Integer.parseInt(month.getValue()), Month::getEmployees));

        BigDecimal total = new BigDecimal("0");
        for (Map.Entry<Integer, List<Employee>> entry : employeeMap.entrySet()) {
            Integer month = entry.getKey();
            List<Employee> list = entry.getValue();
            for (Employee employee : list) {
                String type = employee.getType().trim();
                switch (type) {
                    case "salary":
                        bs = new SalaryEmployeeSalary(salary);
                        break;
                    case "hour":
                        bs = new HourEmployeeSalary(hourSalary, standardHour, multiple);
                        break;
                    case "sale":
                        bs = new SaleEmployeeSalary(basicSalary, getPercentages(percentage));
                        break;
                    default:
                        System.out.println("暂不支持其他用户类型！");
                        break;
                }
                BigDecimal personalMonthSalary = getSalary(employee,month,birthdaySalary);
                total = total.add(personalMonthSalary);
            }
        }
        return total;
    }

    /**
     * 计算每个员工的薪资
     * @param employee 员工信息
     * @param month 工资月份
     * @param birthdaySalary 生日奖励
     * @return 总工资
     */
    private static BigDecimal getSalary(Employee employee,int month, double birthdaySalary) {
        BigDecimal birthdayBonus = calcBirthdaySalary(employee,month,birthdaySalary);
        if (bs == null) {
            return BigDecimal.valueOf(0);
        }
        return bs.calcBasicSalary(employee).add(birthdayBonus);
    }

    /**
     * 获取员工的生日奖金
     * @param employee 员工信息
     * @param currentMonth 当前月份
     * @param money 奖励金额
     * @return 奖励
     */
    private static BigDecimal calcBirthdaySalary(Employee employee, int currentMonth, double money) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(employee.getBirthday());
        int month = calendar.get(Calendar.MONTH);
        return Utils.setScale((currentMonth - 1) == month ? BigDecimal.valueOf(money) : BigDecimal.valueOf(0));
    }

    /**
     * 获取销售额对应的提成率
     * @param percentage 提成率字符串
     * @return 销售额对应的提成率
     */
    private static HashMap<Double, Double> getPercentages(String percentage) {
        String[] pts = percentage.split(",");
        // 销售额对应的提成率
        HashMap<Double, Double> percentages = new HashMap<>();
        for (String pt: pts) {
            String[] ps = pt.split("_");
            percentages.put(Double.parseDouble(ps[0]),Double.parseDouble(ps[1]));
        }
        return percentages;
    }
}
