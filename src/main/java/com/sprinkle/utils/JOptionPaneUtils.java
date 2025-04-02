package com.sprinkle.utils;

import javax.swing.JOptionPane;

public class JOptionPaneUtils {
    public static void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static boolean showConfirmDialog(String message) {
        int result = JOptionPane.showConfirmDialog(null, message, "Konfirmasi", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
}
