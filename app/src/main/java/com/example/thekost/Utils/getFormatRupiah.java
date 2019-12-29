package com.example.thekost.Utils;

import java.text.NumberFormat;
import java.util.Locale;

public class getFormatRupiah {
    public String getFormat(int price){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        return format.format(price);
    }
}
