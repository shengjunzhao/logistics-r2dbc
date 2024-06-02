package com.haole.logistics.r2dbc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {

    /**
     * 大单位转换为小单位
     * 例如：kg->g,m->mm
     *
     * @param value
     * @param percent
     * @return
     */
    public static BigDecimal multiply(BigDecimal value, Integer percent) {
        if (null == percent) {
            percent = 0;
        }
        if (null == value) {
            return null;
        }
        if (percent <= 0) {
            return value.stripTrailingZeros();
        }
        return value.multiply(BigDecimal.TEN.pow(percent)).stripTrailingZeros();
    }

    /**
     * 小单位转换为大单位
     *
     * @param value
     * @param percent
     * @return
     */
    public static BigDecimal divide(BigDecimal value, Integer percent) {
        if (null == percent) {
            percent = 0;
        }
        if (null == value) {
            return null;
        }
        if (percent <= 0) {
            return value.stripTrailingZeros();
        }
        return value.divide(BigDecimal.TEN.pow(percent), 10, RoundingMode.HALF_UP).stripTrailingZeros();
    }
}
