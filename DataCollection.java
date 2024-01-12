package cpt;

import java.io.*;
import java.util.*;

public class DataCollection {
    private int fileLength = 150;
    private int intThisYear = 2023;
    private int intCurrentYear;

    ArrayList<Data> data;
    ArrayList<Data> currentData;

    int[] annualGrowth;
    
    /**
     * Method that reads CSV file and tracks its records and elements of each record
     * @return arraylist that represents all records of the CSV file
     */
    public ArrayList<Data> getData(){
        try {
            BufferedReader file = new BufferedReader(new FileReader("src/cpt/annual-population-growth.csv"));

            ArrayList<Data> data = new ArrayList<Data>();

            // Adding elements to an array
            for(int j = 0; j < fileLength; j++){
                String[] elements = file.readLine().split(",");
                Data records = new Data(Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                data.add(records);
            }
            this.data = data;
            file.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Method takes in current year and creates new arraylist with data from start to current year 
     * @param intThisYear the current year (2023)
     * @return the filtered dataset from start to current year
     */
    public ArrayList<Data> getCurrentData(){
        try {
            BufferedReader file = new BufferedReader(new FileReader("src/cpt/annual-population-growth.csv"));

            ArrayList<Data> currentData = new ArrayList<Data>();

            // Adding elements to an array
            for(int i = 0; i < fileLength; i++){
                String[] elements = file.readLine().split(",");
                Data records = new Data(Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
                if(Integer.parseInt(elements[2])<=intThisYear){
                    currentData.add(records);
                    intCurrentYear = i;
                }
            }
            this.currentData = currentData;
            file.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        return currentData;
    }

    /**
     * Getter method for the length of the file
     * @return the length of the file
     */
    public int getFileLength(){
        return fileLength-1;
    }

    /**
     * Getter method for the file line of current year
     * @return the file line of current year
     */
    public int getCurrentYear(){
        return intCurrentYear;
    }

    /**
     * Getter method for this year
     * @return the current year
     */
    public int getThisYear(){
        return intThisYear;
    }

    /**
     * Method that creates a filtered list of only population growth data from arraylist arr and searches for the smallest number 
     * @param arr the arraylist that is filtered and searched
     * @return the smallest number in the arraylist
     */
    public int getSmallestGrowth(ArrayList<Data> arr){
        int temp;
        int[] annualGrowth = new int[arr.size()];

        for(int i = 0; i < arr.size(); i++){
            annualGrowth[i] = arr.get(i).getPopulationGrowth();
        }

        for (int i = 0; i < arr.size(); i++){  
            for (int j = i + 1; j < arr.size(); j++){  
                if (annualGrowth[i] > annualGrowth[j]){  
                    temp = annualGrowth[i];  
                    annualGrowth[i] = annualGrowth[j];  
                    annualGrowth[j] = temp;  
                }  
            }  
        }  

        return annualGrowth[0];
    }

    /**
     * Method that creates a filtered list of only population growth data from arraylist arr and searches for the largest number 
     * @param arr the arraylist that is filtered and searched
     * @return the largest number in the arraylist
     */
    public int getLargestGrowth(ArrayList<Data> arr){
        int temp;
        int[] annualGrowth = new int[arr.size()];

        for(int i = 0; i < arr.size(); i++){
            annualGrowth[i] = arr.get(i).getPopulationGrowth();
        }

        for (int i = 0; i < arr.size(); i++){  
            for (int j = i + 1; j < arr.size(); j++){  
                if (annualGrowth[i] > annualGrowth[j]){  
                    temp = annualGrowth[i];  
                    annualGrowth[i] = annualGrowth[j];  
                    annualGrowth[j] = temp;  
                }  
            }  
        }  

        return annualGrowth[arr.size()-1];
    }

}