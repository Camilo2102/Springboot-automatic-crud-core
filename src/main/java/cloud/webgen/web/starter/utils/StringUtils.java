package cloud.webgen.web.base.starter.utils;

public class StringUtils {
    public static String getFromParking(String parking, String... args) {
        return String.format(parking, args);
    }

    public static String firstUpperLetter(String value){
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static String firstLowerLetter(String value){
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }
}