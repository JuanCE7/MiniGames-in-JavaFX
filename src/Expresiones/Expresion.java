
package Expresiones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expresion {
    public static boolean correo(String email){
        Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(email);
        return mat.find();
    }
}
