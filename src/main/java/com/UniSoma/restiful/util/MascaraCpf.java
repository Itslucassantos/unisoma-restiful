package com.UniSoma.restiful.util;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class MascaraCpf {

    public static String formatarCpf(String cpf) throws ParseException {
        String mascara = "###.###.###-##";
        MaskFormatter mf = new MaskFormatter(mascara);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(cpf);
    }

}


