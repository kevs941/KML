/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kml;

import Utilidades.GeneraXML;
import Utilidades.Punto;
import java.util.Vector;

/**
 *
 * @author lelguea
 */
public class Ejemplo_Consola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(GeneraXML.Inicio());
        Punto p = new Punto(22.185,-104.250);
        System.out.println(GeneraXML.addPunto("Punto1","Primer Punto", p));
        p = new Punto(22.175,-104.240);
        System.out.println(GeneraXML.addPunto("Punto2","Segundo Punto", p));
        p = new Punto(22.175,-104.260);
        System.out.println(GeneraXML.addPunto("Punto3","Tercer Punto", p));
        
        Vector v = new Vector();
        v.add(new Punto(22.175,-104.22));
        v.add(new Punto(22.165,-104.23));
        v.add(new Punto(22.170,-104.24));
        v.add(new Punto(22.170,-104.25));
        System.out.println(GeneraXML.addLinea("Segmento1", "Primer Segmento", v));
        
        v=new Vector();
        v.add(new Punto(22.16,-104.23,0 ));
        v.add(new Punto(22.15,-104.24,0));
        v.add(new Punto(22.14,-104.23,0 ));
        v.add(new Punto(22.15,-104.22,0 ));
        System.out.println(GeneraXML.addPoligono("Poligono1", "Primer Poligono", v));
        
        System.out.println(GeneraXML.Fin());
    }

}
