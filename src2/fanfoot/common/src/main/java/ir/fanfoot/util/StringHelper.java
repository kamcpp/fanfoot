package ir.fanfoot.util;

public class StringHelper {

    public static String correctPersianCharacters(String str) {
        if(str == null) {
            return null;
        }
        String newString = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 1610) {
                newString += (char)(1740);
            } else if (c == 1603) {
                newString += (char)(1705);
            } else {
                newString += c;
            }
        }
        return newString;
    }
}
