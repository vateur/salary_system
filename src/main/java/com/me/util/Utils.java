package com.me.util;

import java.math.BigDecimal;


/**
 * 工具类
 */
public class Utils {

    /**
     * 四舍五入为两位小数
     *
     * @param bd 原始小数
     * @return result
     */
    public static BigDecimal setScale(BigDecimal bd) {
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
