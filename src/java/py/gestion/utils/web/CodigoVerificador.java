/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christian
 */
public class CodigoVerificador {

    public static int calcular(String numero) {
        if (numero == null) {
            return 0;
        }
        int basemax = 11;
        byte codigo;
        String numero_al = "";

        for (int i = 0; i <= (numero).length() - 1; i++) {
            try {
                String c;
                c = numero.substring(i, i + 1);

                codigo = (c.trim().toUpperCase().getBytes("US-ASCII"))[0];

                if (!(codigo >= 48 & codigo <= 57)) {
                    numero_al = numero_al + codigo;
                } else {
                    numero_al = numero_al + c;
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CodigoVerificador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        int k = 2;
        double total = 0d;

        for (int i = numero_al.length(); i >= 1; i += -1) {
            if ((k > basemax)) {
                k = 2;
            }
            Long numero_aux;
            numero_aux = Long.parseLong(numero_al.substring(i - 1, i));
            total = total + (numero_aux * k);
            k = k + 1;
        }


        int digito;
        Double resto = total % 11;
        if ((resto > 1)) {
            digito = 11 - resto.intValue();
        } else {
            digito = 0;
        }
        return digito;
    }
}
