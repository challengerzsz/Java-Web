package com.bsb.utils.MatchUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchRegexUtil {

    private static final String emailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";


    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
