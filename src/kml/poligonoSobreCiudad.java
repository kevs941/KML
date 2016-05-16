/*
http://www.rae.es/diccionario-panhispanico-de-dudas/signos-y-abreviaturas
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kml;

import Utilidades.AreaRededor;
import Utilidades.GeneraXML;
import Utilidades.GeoNames;
import Utilidades.Punto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nataniel
 */
public class poligonoSobreCiudad {
    static public ArrayList<classCiudad> arregloCiudades = new ArrayList<classCiudad>();
    public static void main(String[] args)  {
        try { 
                PrintWriter escribir = new PrintWriter(new BufferedWriter(new FileWriter("Ejemplo2.kml")));

                escribir.println(GeneraXML.Inicio());
                //llenaCiudades(); 
                llenaCiudadesDesdeBD(); 
                escribir.println(poligonosSobreCiudades());
                escribir.println(GeneraXML.Fin());

                escribir.close();
                System.out.println("Listo!!");
            } catch (Exception e) { 
                System.out.println(e);
            }
       }

    static private String poligonosSobreCiudades() {
        String salida = ""; 
        //Punto p = new Punto(GeoNames.BuscaUbicacion("Brasil",""));
        //Punto p = new Punto(-10,-55,100000);
         for ( classCiudad ciudadTemp : arregloCiudades ) {
             salida=salida+GeneraXML.addPoligono("Ventas en " + ciudadTemp.nombreCiudad, "Aqui se muestran las ventas de " + ciudadTemp.nombreCiudad, AreaRededor.elementos(ciudadTemp.p, 100));                                               
       }
        
        
        return salida; 
    }

    static private void llenaCiudades() {
        classCiudad ciudadTemporal = new classCiudad(-10,-55,1000000); 
        ciudadTemporal.nombreCiudad = "guayaquil"; 
        ciudadTemporal.nombrePais = "EC"; 
        arregloCiudades.add(ciudadTemporal); 
        
        //myclass.add(new xClass());
        //myclass.add(new xClass());
    }
    
    static private String llenaCiudadesDesdeBD(){
        String salida="";
       
       try {
           
           String db = "dbGoogleEarth.accdb";           
           String url = "jdbc:ucanaccess://" + db; 
           
           Connection con = DriverManager.getConnection (url, "", "");

           Statement select = con.createStatement();
           ResultSet consulta = select.executeQuery("SELECT * FROM t_ventas");
           boolean seguir = consulta.next();
           while (seguir) {
               String ciudad=consulta.getString("ciudad");
               String pais=consulta.getString("clavePais");               
               double altitud = consulta.getDouble("ventas"); 
               
               Punto p = new Punto(GeoNames.BuscaUbicacion(ciudad,pais));
               if (!p.toString().equalsIgnoreCase("0.0,0.0,0.0 ")) 
               {
                   p.setAlt(altitud);
                   //Aquí agragarlo al XML 
                   classCiudad ciudadTemp = new classCiudad(p.getLat(), p.getLon(), p.getAlt()); 
                   ciudadTemp.nombreCiudad = ciudad; 
                   ciudadTemp.nombrePais = pais; 
                   
                   arregloCiudades.add(ciudadTemp); 
                   
               } 
               else 
               {
                   System.err.println("No se encotro a "+ciudad+" en "+pais);
               }
       
               seguir = consulta.next(); 
           }
           consulta.close();

           select.close();
           con.close();

         } catch (Exception ex) {
             System.out.println(ex.toString());
         }       
       
       return salida;
    }
    
}
