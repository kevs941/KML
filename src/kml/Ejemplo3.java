/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kml;

import Utilidades.AreaRededor;
import Utilidades.GeneraXML;
import Utilidades.Punto;
import java.io.*;
import java.sql.*;


public class Ejemplo3
{

   public static void main(String[] args)  {
	try { 
	    PrintWriter escribir = new PrintWriter(new BufferedWriter(new FileWriter("Ejemplo3.kml")));
		
            escribir.println(GeneraXML.Inicio());
            escribir.println(procesa());
            escribir.println(GeneraXML.Fin());

            escribir.close();
	} catch (Exception e) { 
	    System.out.println(e);
	}
   }

   public static String procesa() {
       String Salida="";
       
       try {
           String db = "Demo.accdb";
           //String url = "jdbc:odbc:MS Access Database;DBQ=" + db;
           String url = "jdbc:ucanaccess://" + db; 

           //Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
           Connection con = DriverManager.getConnection (url, "", "");

           Statement select = con.createStatement();
           ResultSet consulta = select.executeQuery("SELECT * FROM f_circulo");
           boolean seguir = consulta.next();
           while (seguir) {
               String nom=consulta.getString("Nombre");
               String desc=consulta.getString("descr");
               Punto p = new Punto(consulta.getDouble("lat"),consulta.getDouble("lng"));

               Salida=Salida+GeneraXML.addPoligono(nom, desc, AreaRededor.elementos(p, consulta.getDouble("radio")));
               seguir = consulta.next(); 
           }
           consulta.close();

           select.close();
           con.close();

         } catch (Exception ex) {
             System.out.println(ex.toString());
         }       
       
       return Salida;
   }
   
}