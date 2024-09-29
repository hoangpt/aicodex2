package com.fs.pos.relationalmanager.utils.validate;

public class StringValidator {
    public static boolean isEmailInvalid(String email) {
        if (email == null) {
            return true;
        }

        return !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean isUsernameInvalid(String username) {
        return username.length() < 6 || username.length() > 50;
    }


    /**
     * wrapper function for phone validation
     * @param countryName
     * @param phone
        * example: Vietnam 0912106550 --> valid
        * example: Vietnam 091210655 --> invalid
        * example: Vietnam +84912106550 --> valid
        * example: Singapore +56912106 --> valid
        * example: Singapore +569121065 --> invalid
     *
     * @return
     */
    public static boolean isPhoneInvalid(String countryName, String phone) {
        if (countryName == null) {
            countryName = "Vietnam";
        }

        if (countryName.equals("Vietnam")) {
            return isPhoneInvalidInVietnam(phone);
        } else if (countryName.equals("Singapore")) {
            return isPhoneInvalidInSingapore(phone);
        } else {
            return true;
        }

    }

    private static boolean isPhoneInvalidInSingapore(String phone) {
        if (phone == null) {
            return true;
        }

        return !phone.matches("^(\\+\\d{1,3}[- ]?)?\\d{8}$");
    }

    private static boolean isPhoneInvalidInVietnam(String phone) {
        if (phone == null) {
            return true;
        }

        return !phone.matches("^(\\+\\d{1,3}[- ]?)?\\d{10}$");
    }    
}
