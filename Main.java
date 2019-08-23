package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Enter the dimensions of cube : ");
        Scanner scanner = new Scanner(System.in);
        int dimensionsOfCube = scanner.nextInt();
        System.out.print("Enter number of Times the die has been rolled : ");
        int dieRolled = scanner.nextInt();
        ArrayList<String> input= new ArrayList<>();
        assignValuesToDice(dimensionsOfCube, input);
        generateAllPossibleOutcomes(dimensionsOfCube, dieRolled, input);
        ArrayList<String> fin = new ArrayList<>();
        refineTheGeneratedData(dieRolled, input, fin);
        double probability = (fin.size())/Math.pow(dimensionsOfCube,dieRolled);
        System.out.print("The probability is : " + probability);
    }

    private static boolean isIncreasing(String input){
        int count = 0;
        for(int i=0;i<input.length();i++){
            if(i+1 <= input.length()-1) {
                if (input.charAt(i) < input.charAt(i + 1)){
                    count++;
                }
            }
        }
        return count == input.length()-1;
    }

    private static boolean isDecreasing(String input){
        int count = 0;
        for(int i=0;i<input.length();i++){
            if(i+1 <= input.length()-1) {
                if (input.charAt(i) > input.charAt(i + 1)){
                    count++;
                }
            }
        }
        return count == input.length()-1;
    }

    private static void refineTheGeneratedData(int dieRolled, ArrayList<String> input, ArrayList<String> fin) {
        for (String s : input) {
            if ((s.length() == dieRolled) && (s.charAt(0) != '0')) {
                if(!checkForZero(s,dieRolled)) {
                    if((!isDecreasing(s)) && (!isIncreasing(s))) {
                        fin.add(s);
                    }
                }
            }
        }
    }

    private static boolean checkForZero(String s, int dieRolled) {
        for(int i=0;i<dieRolled;i++){
            if (s.charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }

    private static void generateAllPossibleOutcomes(int dimensionsOfCube, int dieRolled, ArrayList<String> input) {
        int start = 0;
        int stop = input.size();
        for(int i=0;i<dieRolled-1;i++){
            generateTwoAtATime(start,stop,input,dimensionsOfCube);
            start = stop;
            stop = input.size();
        }
    }

    private static void assignValuesToDice(int dimensionsOfCube, ArrayList<String> input) {
        for(int i=0;i<dimensionsOfCube;i++){
            input.add(String.valueOf(i));
        }
    }

    private static void generateTwoAtATime(int start,int end,ArrayList<String> med,int initial) {
        for(int i=start;i<end;i++){
            for(int j=0;j<initial;j++){
                med.add(med.get(i) + med.get(j));
            }
        }
    }
}
