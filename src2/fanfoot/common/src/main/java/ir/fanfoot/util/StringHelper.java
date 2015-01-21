package ir.fanfoot.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class StringHelper {

    public static String correctPersianCharacters(String str) {
        if (str == null) {
            return null;
        }
        String newString = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 1610) {
                newString += (char) (1740);
            } else if (c == 1603) {
                newString += (char) (1705);
            } else {
                newString += c;
            }
        }
        return newString;
    }

    public static String refineForRegex(String content) {
        return content.replace("\n", "").replace("\r", "").replace("\t", "");
    }

    public static URL encodeURL(String url) throws MalformedURLException, URISyntaxException {
        URL u = new URL(url);
        return new URI(
                u.getProtocol(),
                u.getAuthority(),
                u.getPath(),
                u.getQuery(),
                u.getRef()).
                toURL();
    }
}
