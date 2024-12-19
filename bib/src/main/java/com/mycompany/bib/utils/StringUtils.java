/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.utils;

public class StringUtils {

    // Check if a string is null or empty
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Capitalize the first letter of a string
    public static String capitalizeFirstLetter(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    // Reverse a string
    public static String reverse(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return new StringBuilder(str).reverse().toString();
    }
}
