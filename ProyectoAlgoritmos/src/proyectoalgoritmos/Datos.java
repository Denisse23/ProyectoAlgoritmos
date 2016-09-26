/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoalgoritmos;

import java.util.ArrayList;

/**
 *
 * @author Denisse
 */
public class Datos {
    int camiones;
    ArrayList<coordenada> coordenadaPepsi;
     ArrayList<coordenada> coordenadasTiendas;
    
    public Datos(int c, ArrayList<coordenada> cp, ArrayList<coordenada> ct){
        camiones = c;
        coordenadaPepsi = cp;
        coordenadasTiendas = ct;
    }
    
    public Datos(){
        
    }

    public int getCamiones() {
        return camiones;
    }

    public void setCamiones(int camiones) {
        this.camiones = camiones;
    }

    public ArrayList<coordenada> getCoordenadaPepsi() {
        return coordenadaPepsi;
    }

    public void setCoordenadaPepsi(ArrayList<coordenada> coordenadaPepsi) {
        this.coordenadaPepsi = coordenadaPepsi;
    }

    public ArrayList<coordenada> getCoordenadasTiendas() {
        return coordenadasTiendas;
    }

    public void setCoordenadasTiendas(ArrayList<coordenada> coordenadasTiendas) {
        this.coordenadasTiendas = coordenadasTiendas;
    }
    
    

   
}
