package edu.escuelaing.arep.logic;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;


/**
 * @author Juan.Ortiz
 *
 */
public class ListCalcs 
{
    public static LinkedList numbers = new LinkedList(); 
    public static double max;
    public static double min;
    public static double sum;
    public static double mul;
    
    public static void main( String[] args ) throws IOException
    { 
        System.out.println("Maximo: "+max);
        System.out.println("Minimo: "+min);
        System.out.println("Sumatoria: "+sum);
        System.out.println("Multiplicatoria: "+mul);
    }
    
    
    public static double sumatoria(LinkedList set){
        double sum = 0.0;
        for (int i = 0; i < set.size(); i++){
            sum += (double) set.get(i);
        }     
        
        return sum;
    }
    
    public static double promedio(LinkedList set) {       
        double sum = sumatoria(set);
        double size = set.size();
        double prom = sum/size;

        return prom;
    }
    
    public static LinkedList bubbleSort(LinkedList<Double> set) {
        Double[] nums = new Double[set.size()];
        for(int i=0;i<set.size();i++){
            nums[i]=set.get(i);
        }
        int n = nums.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (nums[j] > nums[j+1])
                {
                    double temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
        LinkedList ll = new LinkedList(Arrays.asList(nums));
        
        return ll;
    }
    
    
    
    public static LinkedList readInput(String set) {
        
        numbers.clear();
        for (String singleString: set.split(" ")){
            Double singleNumber = Double.parseDouble(singleString);
            numbers.add(singleNumber);
        }
        
        return numbers;
    }
    
    
    
}
