/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilidades;

/**
 *
 * @author lelguea
 */
public class Punto {
    double Lat;
    double Lon;
    double Alt;
    
    public Punto() {
        Lat=0;
        Lon=0;
        Alt=0;
    }
    
    public Punto(double par1, double par2, double par3) {
        Lat=par1;
        Lon=par2;
        Alt=par3;
    }

    public Punto(double par1, double par2) {
        Lat=par1;
        Lon=par2;
        Alt=0;
    }

    public Punto(String pos) {
        String[] valores=pos.split(",");
        Lat=Double.parseDouble(valores[1]);
        Lon=Double.parseDouble(valores[0]);
        Alt=0;
    }
    
    public void setLat(double par) {
        Lat=par;
    }
    public void setLon(double par) {
        Lon=par;
    }
    public void setAlt(double par) {
        Alt=par;
    }
    
    public double getLat() {
        return Lat;
    }
    public double getLon() {
        return Lon;
    }
    public double getAlt() {
        return Alt;
    }
    
    @Override
    public String toString() {
        return Lon+","+Lat+","+Alt+" ";
    }

}
