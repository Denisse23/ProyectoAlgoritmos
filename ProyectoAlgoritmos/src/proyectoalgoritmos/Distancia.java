/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoalgoritmos;

/**
 *
 * @author Denisse
 */
public class Distancia {
    double inicio_distancia;
    int indice_coordenada;
    
    public Distancia(double d, int indice){
        inicio_distancia = d;
        indice_coordenada = indice;
    }

    public double getInicio_distancia() {
        return inicio_distancia;
    }

    public void setInicio_distancia(double inicio_distancia) {
        this.inicio_distancia = inicio_distancia;
    }

    public int getIndice_coordenada() {
        return indice_coordenada;
    }

    public void setIndice_coordenada(int indice_coordenada) {
        this.indice_coordenada = indice_coordenada;
    }
    
    
}
