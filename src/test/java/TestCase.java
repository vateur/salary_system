import com.me.entity.Department;
import com.me.util.CalculatorUtils;
import com.me.util.FileUtils;
import com.me.util.Utils;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class TestCase {

    public final String resourcePath = "src/main/resources";

    @Test
    public void test() throws JAXBException, IOException {

        String listPath = resourcePath + "/list.xml";
        Department department = FileUtils.getDepartment(listPath);

        String configPath = resourcePath + "/config.properties";
        Map<String, String> configMap = FileUtils.parseConfig(configPath);

        BigDecimal total = CalculatorUtils.getTotalSalary(department, configMap);
        System.out.println("工具计算的总工资为：" + total);

        BigDecimal handCalcSalary = handCalculate();
        System.out.println("手动计算的总工资为：" + handCalcSalary);

        Assert.assertEquals(total, handCalcSalary);

    }

    private BigDecimal handCalculate() {
        /*<month value = "9" >
            <employee name = "xiao wang" type = "salary" birthday = "1990-10-11" / >
            <employee name = "xiao zhang" type = "hour" workingHours = "170.5" birthday = "1990-11-11" / >
            <employee name = "xiao hong" type = "sale" amount = "34100.8" birthday = "1990-12-11" / >
        </month >
        <month value = "10" >
            <employee name = "xiao wang" type = "salary" birthday = "1990-10-11" / >
            <employee name = "xiao zhang" type = "hour" workingHours = "155.75" birthday = "1990-11-11" / >
            <employee name = "xiao hong" type = "sale" amount = "23500.7" birthday = "1990-12-11" / >
            <employee name = "xiao liu" type = " hour " workingHours = "188.25" birthday = "1989-10-11" / >
        </month >*/
        // 9月工资
        BigDecimal xiaowang9 = BigDecimal.valueOf(6000);
        BigDecimal xiaozhang9 = Utils.setScale(BigDecimal.valueOf(160 * 35 + (170.5 - 160) * 35 * 1.3));
        BigDecimal xiaohong9 = Utils.setScale(BigDecimal.valueOf(3000 + (34100.8 - 20000) * 0.08));
        // 10月工资
        BigDecimal xiaowang10 = BigDecimal.valueOf(6000).add(BigDecimal.valueOf(100));
        BigDecimal xiaozhang10 = Utils.setScale(BigDecimal.valueOf(155.75 * 35));
        BigDecimal xiaohong10 = Utils.setScale(BigDecimal.valueOf(3000 + (23500.7 - 20000) * 0.05));
        BigDecimal xiaoliu10 = Utils.setScale(BigDecimal.valueOf(160 * 35 + (188.25 - 160) * 35 * 1.3).add(BigDecimal.valueOf(100)));

        BigDecimal result = xiaowang9.add(xiaozhang9).add(xiaohong9).add(xiaowang10).add(xiaozhang10).add(xiaohong10).add(xiaoliu10);
        return result;
    }
}
