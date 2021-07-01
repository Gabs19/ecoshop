package com.example.ecoshop;

import java.text.DecimalFormat;

public class Utils {

    public double decimalFormat(double price) {
        DecimalFormat df = new DecimalFormat("##.##");
        double priceFormat = Float.parseFloat(df.format(price));
        return priceFormat;
    }


}
