/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kml;

import Utilidades.Punto;

/**
 *
 * @author Nataniel
 */
public class classCiudad {
    public Punto p; 
    public String nombreCiudad; 
    public String nombrePais; 
    classCiudad(double latitud, double longitud, double altitud){
        p = new Punto(latitud, longitud, altitud);        
        nombreCiudad =""; 
        nombrePais = ""; 
    }
    
    
}
