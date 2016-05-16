/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

import java.util.Vector;

/**
 *
 * @author lelguea
 */
public class AreaRededor {

    public static Vector elementos(Punto p, double distan) {
        Vector v = new Vector();
        for (double i=0;i<=2*Math.PI;i=i+Math.PI/12) {
            Punto pos = new Punto(p.Lat+distan/100*.90579*Math.cos(i), p.Lon+distan/100*.95078*Math.sin(i), p.Alt);
            v.add(pos);
            //System.out.println(pos);
        }

        return v;
    }

}
