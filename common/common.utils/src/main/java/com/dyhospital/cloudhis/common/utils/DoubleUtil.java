package com.dyhospital.cloudhis.common.utils;

import java.math.BigDecimal;

public class DoubleUtil {
    public static double get2Scale(double value){
        BigDecimal b = new BigDecimal(value);
        double val = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return val;
    }

    public static double get1Scale(double value){
        BigDecimal b = new BigDecimal(value);
        double val = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return val;
    }


}
