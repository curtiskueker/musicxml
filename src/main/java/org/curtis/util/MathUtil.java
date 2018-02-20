package org.curtis.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MathUtil {
    public static int SCALE = 4;
    public static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    public static BigDecimal ZERO = newBigDecimal(0);

    private MathUtil() {

    }

    public static BigDecimal newBigDecimal(BigDecimal val) {
        return newBigDecimal(val.doubleValue());
    }

    public static BigDecimal newBigDecimal(double val) {
        BigDecimal newValue = new BigDecimal(val);
        return round(newValue);
    }

    public static BigDecimal newBigDecimal(int val) {
        BigDecimal newValue = new BigDecimal(val);
        return round(newValue);
    }

    public static BigDecimal newBigDecimal(String val) {
        if(StringUtil.isEmpty(val)) {
            return null;
        }

        BigDecimal newValue = new BigDecimal(val);
        return round(newValue);
    }

    public static BigDecimal round(BigDecimal newValue) {
        return newValue.setScale(SCALE, ROUNDING_MODE);
    }

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;

        BigDecimal newValue = a.add(b);
        return round(newValue);
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;

        BigDecimal newValue = a.subtract(b);
        return round(newValue);
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;

        BigDecimal newValue = a.multiply(b);
        return round(newValue);
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;

        MathContext mathContext = new MathContext(10, ROUNDING_MODE);
        BigDecimal newValue = a.divide(b, mathContext);
        return round(newValue);
    }

    public static BigDecimal exp(BigDecimal a, Integer b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = 0;

        BigDecimal newValue = a.pow(b);
        return round(newValue);
    }

    public static BigDecimal truncate(BigDecimal a) {
        return newBigDecimal(a.intValue());
    }

    public static boolean largerThan(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;

        return a.compareTo(b) > 0;
    }

    public static boolean smallerThan(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;

        return a.compareTo(b) < 0;
    }

    public static boolean equalTo(BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;

        return a.compareTo(b) == 0;
    }

    public static boolean isPositive(BigDecimal a) {
        return a != null && a.signum() > 0;
    }

    public static boolean isNegative(BigDecimal a) {
        return a != null && a.signum() < 0;
    }

    public static boolean isZero(BigDecimal a) {
        return a != null && equalTo(a, BigDecimal.ZERO);
    }
}
