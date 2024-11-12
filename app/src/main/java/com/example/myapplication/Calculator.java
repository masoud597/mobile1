package com.example.myapplication;

public class Calculator {
    private static String firstNum = "";
    private static String secondNum = "";
    private static String lastOperation = "";
    private static boolean lastInputWasOperation = false;

    public static String getFirstNum(){
        return firstNum;
    }
    public static String getSecondNum(){
        return secondNum;
    }
    public static String getLastOperation() {
        return lastOperation;
    }
    public static void setFirstNum(String num) {
        firstNum = num;
    }
    public static void setSecondNum(String num) {
        secondNum = num;
    }
    public static void setLastOperation(String operation) {
        lastOperation = operation;
    }
    public static void setLastInputWasOperation(boolean wasOperation) {
        lastInputWasOperation = wasOperation;
    }

    public static boolean canUseOperation() {
        return (!lastInputWasOperation || lastOperation.isEmpty()) && !firstNum.isEmpty();
    }
}
