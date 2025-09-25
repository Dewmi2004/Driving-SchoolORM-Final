package lk.ijse.drivingschoolormfinal.util;

import java.util.regex.Pattern;

public class RegexValidator {

    private static final String NAME_REGEX = "^[A-Za-z ]{3,50}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_REGEX = "^(?:7|0|(?:\\+94))[0-9]{8,9}$"; // Sri Lanka format (+94 or 0)
    private static final String ADDRESS_REGEX = "^[A-Za-z0-9 ,./-]{5,100}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$"; // at least 1 digit, 1 upper, 1 lower
    private static final String FEE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$"; // 1000 or 1000.50
    private static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$"; // yyyy-MM-dd

    public static boolean isValidName(String name) {
        return name != null && Pattern.matches(NAME_REGEX, name);
    }

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && Pattern.matches(PHONE_REGEX, phone);
    }

    public static boolean isValidAddress(String address) {
        return address != null && Pattern.matches(ADDRESS_REGEX, address);
    }

    public static boolean isValidPassword(String password) {
        return password != null && Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean isValidFee(String fee) {
        return fee != null && Pattern.matches(FEE_REGEX, fee);
    }

    public static boolean isValidDate(String date) {
        return date != null && Pattern.matches(DATE_REGEX, date);
    }
}
