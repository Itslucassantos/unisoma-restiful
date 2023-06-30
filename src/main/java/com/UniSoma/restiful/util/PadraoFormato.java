package com.UniSoma.restiful.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PadraoFormato {

    public static double formatar(double valor) {
        Locale localeUs = new Locale("en", "US");
        DecimalFormat decimalFormat = new DecimalFormat(".##", new DecimalFormatSymbols(localeUs));
        return Double.parseDouble(decimalFormat.format(valor));
    }




}
