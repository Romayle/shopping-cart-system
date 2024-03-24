package com.cw.westminster_sc.helper;

import java.util.Scanner;

public class HelperUtil {

    private static Scanner scanner;

    public static String getStringInput(String prompt) {
        while(true){
            System.out.print(prompt + " : ");
            String input = scanner.nextLine();
            if(input.isEmpty()){
                printMsg("Please enter a valid input");
                continue;
            }
            return input;
        }
    }

    public static Integer getIntegerInput(String prompt) {
        while(true){
            System.out.print(prompt + " : ");
            String input = scanner.nextLine();
            if(input.isEmpty()){
                printMsg("Please enter a valid input");
                continue;
            }
            try{
                return Integer.parseInt(input);
            }catch (Exception e){
                printMsg("Please enter a valid input");
                continue;
            }
        }
    }


    public static Double getDoubleInput(String prompt) {
        while(true){
            System.out.print(prompt + " : ");
            String input = scanner.nextLine();
            if(input.isEmpty()){
                printMsg("Please enter a valid input");
                continue;
            }
            try{
                return Double.parseDouble(input);
            }catch (Exception e){
                printMsg("Please enter a valid input");
                continue;
            }
        }
    }
    public static void printMsg(String msg){
        System.out.print("---------------------------------------------------\n" + msg + "\n---------------------------------------------------\n");
    }




    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        HelperUtil.scanner = scanner;
    }
    
    
}
