package edu.escuelaing.arep.parcial;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.escuelaing.arep.logic.ListCalcs;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import static spark.Spark.*;


/**
 * @author Juan.Ortiz
 *
 */
public class ServerParcial {
    
    public static LinkedList list_numbers = new LinkedList();
    public static Double list_Prom;
    public static Double list_Sum;
    public static LinkedList list_Order;
    
	
    public static void main(String[] args) { 
        setPort(4567);
        port(getPort());
	get("/index", (req, res) -> indexPage(req, res));
        get("/calcdata", (req, res) -> calcdataPage(req, res));
        get("/results", (req, res) -> resultsPage(req,res));
    }
    
    private static String indexPage(Request req, Response res){
        String indexHTML = "<!DOCTYPE html>\n" +
"<html>\n" +
"   \n" +
"   <head>\n" +
"      <title>Parcial</title>\n" +
"   </head>\n" +
"	\n" +
"   <body>\n" +
"      <p><b>Calculations with list of numbers</b></p>\n" +
"      <h2>Numbers Operators Web App!</h2>\n" +
"      <p>Web application to sort with Bubble Sort, calculate average and sum of a list of numbers given by the user.\n\n</p>\n" +
"      <p>\n\nClick the next link to start entering your data:</p>\n" +             
"      <a href = \"/calcdata\" target = \"_self\">Enter your Data</a>\n" +                
"   </body>\n" +
"	\n" +
"</html>";
        return indexHTML;
    }
    
    /*
    
    */
    private static String calcdataPage(Request req, Response res){
        String calcdataHTML = "<!DOCTYPE html>\n" +
"<html>\n" +
"   \n" +
"   <head>\n" +
"      <title>Parcial</title>\n" +
"   </head>\n" +
"	\n" +
"   <body>\n" +
"      <p><b>Calculations with list of numbers</b></p>\n" +
"      <h2>Enter your list of numbers to the application:</h2>\n" +
"      <p>\n\nEnter a list of numbers you want to calculate:</p>\n" +
"      <p>***Remember to enter the numbers separated by blank spaces and with \\ \". \\\" decimal points if required.</p>\n" +                
"       <form action=\"/results\">\n" +
"           List of Numbers:<br>\n" +
"           <input type=\"text\" placeholder=\"Separated by spaces\" name=\"inputData\" ><br>\n" +
"           <input type=\"submit\" value=\"Calculate!\">\n" +
"       </form>" +      
"   </body>\n" +
"	\n" +
"</html>";
        return calcdataHTML;
    }
    
    /*
    
    */
    private static String resultsPage(Request req, Response res){
        String set = req.queryParams("inputData");   
        
        ListCalcs calculator = new ListCalcs();        
        
        list_numbers = calculator.readInput(set);   
        list_Prom = calculator.promedio(list_numbers);
        list_Sum = calculator.sumatoria(list_numbers);
        list_Order = calculator.bubbleSort(list_numbers);
        ObjectMapper map = new ObjectMapper();
            
        try {
            String json = map.writeValueAsString(list_Order);
            System.out.println("Json"+" "
                    +json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ServerParcial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("List of Numbers:");
        System.out.println("List: "+ list_numbers);
        System.out.println("Average: "+ list_Prom);
        System.out.println("Summation: "+ list_Sum);
        System.out.println("Ordered: "+ list_Order);
        
        String resultsHTML = "<!DOCTYPE html>\n" +
"<html>\n" +
"   \n" +
"   <head>\n" +
"      <title>Parcial</title>\n" +
"   <style>" +
"       table, th, td {" +
"           border: 1px solid black;" +
"           border-colapse: collapse;" +
"       }" +
"       th, td {" +
"           padding: 5px;" +
"       }" +
"       th {" +
"           text-align: left;" +
"       }" +
"   </style>" +
"   </head>\n" +
"	\n" +
"   <body>\n" +
"      <p><b>Calculations with List of Numbers</b></p>\n" +
"       <h2>Results</h2>" +
"       <p>BubbleSort,Average and Summation values</p>\n\n" +
         
                
"       <table style=\"width:30%\">\n" +
"           <tr>\n" +
"               <th>Results</th>\n" +
"               <th><b>Result</b></th> \n" +
"           </tr>\n" +
"           <tr>\n" +
"               <td><b>Average</b></td>\n" +
"               <td>" + list_Prom + "</td> \n" +
"           </tr>\n" +
"           <tr>\n" +
"               <td><b>Summation</b></td>\n" +
"               <td>" + list_Sum + "</td> \n" +
"           </tr>\n" +                
"           <tr>\n" +
"               <td><b>Ordered</b></td>\n" +
"               <td>" + list_Order + "</td> \n" +
"           </tr>\n" +
"       </table>" +                   
"   </body>\n" +
"	\n" +
"</html>";
        
        return resultsHTML;
    }
    
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
    
}
