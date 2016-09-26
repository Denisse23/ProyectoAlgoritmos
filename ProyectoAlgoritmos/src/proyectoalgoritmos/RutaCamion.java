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
public class RutaCamion {
    ArrayList<Integer> nodos;
    boolean nodoAgregado;
    double total;
    public RutaCamion(){
        nodos = new ArrayList();
        nodoAgregado = false;
        total = 0;
    }
    
    public void addTotal(double t){
        total += t;
    }

    public boolean isNodoAgregado() {
        return nodoAgregado;
    }

    public void setNodoAgregado(boolean nodoAgregado) {
        this.nodoAgregado = nodoAgregado;
    }
    
    public double getTotalRuta(){
        return total;
    }

    public ArrayList<Integer> getNodos() {
        return nodos;
    }

    public void setNodos(ArrayList<Integer> nodos) {
        this.nodos = nodos;
    }
    
    public void addNodo(int no) {
        this.nodos.add(no);
    }
    
}
