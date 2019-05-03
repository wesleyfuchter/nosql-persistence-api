package net.unibave.npa.core.util.text;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class TextLookupFacade {

    private volatile static TextLookupFacade instance;

    public static TextLookupFacade getInstance() {
        if (instance == null) {
            synchronized (TextLookupFacade.class) {
                if (instance == null) {
                    instance = new TextLookupFacade();
                }
            }
        }
        return instance;
    }

    public String setFirstToUpper(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
    }

    public String lpad(String string, Integer quantidadeCaracter, String valorConcatenado) {
        return String.format("%1$" + quantidadeCaracter + "s", string).replace(" ", valorConcatenado);
    }

    public String rpad(String string, Integer quantidadeCaracter, String valorConcatenado) {
        return String.format("%1$-" + quantidadeCaracter + "s", string).replace(" ", valorConcatenado);
    }

    public String lpad(String string, Integer quantidadeCaracter) {
        return String.format("%1$" + quantidadeCaracter + "s", string);
    }

    public String rpad(String string, Integer quantidadeCaracter) {
        return String.format("%1$-" + quantidadeCaracter + "s", string);
    }

    public String toUTF8(String value) throws UnsupportedEncodingException {
        return decode(value, "UTF-8");
    }

    public String decode(String value, String encodeCode) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, encodeCode);
    }

}
