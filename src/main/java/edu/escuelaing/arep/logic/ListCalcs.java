package edu.escuelaing.arep.logic;

import java.io.IOException;
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
        
        //Convierte el maximo a solo dos cifras decimales
        String decimalTemp = String.format("%.2f", sum);
        sum = Double.parseDouble(decimalTemp);
        
        return sum;
    }
    
    public static double promedio(LinkedList set) {       
        double sum = sumatoria(set);
        double size = set.size();
        double prom = sum/size;

        return prom;
    }
    
    public static double bubbleSort(LinkedList set) {
        double list = 0.0;

        return list;
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
