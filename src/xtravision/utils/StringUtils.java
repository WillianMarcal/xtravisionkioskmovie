/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtravision.utils;

/**
 *
 * @author Willian
 */
public class StringUtils {
 
    /**
     * Converts the value from String to Integer format. Returns null 
     * whether it can not be converted to a Integer value.
     * @param value The String value.
     * @return Integer value.
     */
    public static Integer convertToInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Converts the value from Integer to String format. Returns null 
     * whether it can not be converted to a String value.
     * @param value The Integer value.
     * @return String value.
     */
    public static String convertToString(Integer value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Checks if the value is not null and there is not only white-spaces 
     * @param value The value that will be checked.
     * @return <strong>true</strong> if the value is not null and is not only 
     * white-spaces, <strong>false</strong> otherwise.
     */
    public static boolean isNotBlank(String value) {
        return (value != null && value.trim().equals(""));
    }
}
