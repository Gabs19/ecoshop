package com.example.ecoshop;

import java.math.RoundingMode;
import java.math.BigDecimal;

public class Utils {

    public double decimalFormat(double price) {
        BigDecimal df = new BigDecimal(price).setScale(2,RoundingMode.HALF_UP);
        return df.doubleValue();
    }


}
