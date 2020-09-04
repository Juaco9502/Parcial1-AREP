package edu.escuelaing.arep.parcial;

import edu.escuelaing.arep.logic.ListCalcs;
import java.util.LinkedList;
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
    public static Double list_Order;
	
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
"      <p><b>Calculos con Lista de Numeros</b></p>\n" +
"      <h2>Numbers Operators Web App!</h2>\n" +
"      <p>Aplicación Web para ordenar con BubbleSort, calcular promedio y sumatoria de una lista de numeros dados por el usuario.\n\n</p>\n" +
"      <p>\n\nClick the next link to start entering your data:</p>\n" +             
"      <a href = \"/calcdata\" target = \"_self\">Enter Your Data</a>\n" +                
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
"      <p><b>Calculos con Lista de Numeros</b></p>\n" +
"      <h2>Ingrese su Lista de Numeros a la Aplicacion:</h2>\n" +
"      <p>\n\nIngrese una lista de números que quiera calcular:</p>\n" +
"      <p>***Recuerde ingresar los numeros separados por espacios en blanco y con \".\" puntos decimales en caso de requerirlo.</p>\n" +                
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
        
        System.out.println("List of Numbers:");
        System.out.println("List: "+ list_numbers);
        System.out.println("Average: "+ list_Prom);
        System.out.println("Ordered: "+ list_Order);
        System.out.println("Summation: "+ list_Sum);
        
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
"      <p><b>Calculos con Lista de Numeros</b></p>\n" +
"       <h2>Results</h2>" +
"       <p>BubbleSort,Average and Summation values</p>\n\n" +
         
                
"       <table style=\"width:30%\">\n" +
"           <tr>\n" +
"               <th>Results</th>\n" +
"               <th><b>List of Numbers</b></th> \n" +
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
