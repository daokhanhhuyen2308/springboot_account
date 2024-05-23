package com.funnycode.react_springboot_account.validator;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator {
    private static final Log LOG = LogFactory.getLog(EmailValidator.class);

    private static final String EMAIL_REGEX = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]+$";

    public static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
