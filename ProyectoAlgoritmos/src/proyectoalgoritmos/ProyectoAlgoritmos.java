/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoalgoritmos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Denisse
 */
public class ProyectoAlgoritmos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Metodos m = new Metodos();
        m.crearArchivoEntrada();
        
        Datos datos_entrada = m.leerArchivoEntrada();
        ArrayList<Distancia> distancias = m.getDistanciasCoordenas(datos_entrada);
        m.ordenarDistancias(distancias);
        ArrayList<RutaCamion> rutas_camiones = new ArrayList();
        for(int i=0;i<datos_entrada.getCamiones();i++){
            rutas_camiones.add(new RutaCamion());
        }
        
        m.asignarRutas(rutas_camiones, distancias, datos_entrada);
        m.agregarInicioARuta(rutas_camiones, datos_entrada);
        m.crearArchivoSalida(rutas_camiones);
        m.crearArchivoCalculos(rutas_camiones);
        
        for(int i=0;i<rutas_camiones.size();i++){
            System.out.println("Ruta "+i+" tiendas:"+rutas_camiones.get(i).getNodos().size()+"---> Distancia"+rutas_camiones.get(i).getTotalRuta());
             for(int j=0;j<rutas_camiones.get(i).getNodos().size()-1;j++){
                 System.out.print((rutas_camiones.get(i).getNodos().get(j)+1)+",");
             }
             System.out.println((rutas_camiones.get(i).getNodos().get(rutas_camiones.get(i).getNodos().size()-1)) +1);
             System.out.println("");
        }
        
        /*
        
        for(int i=0;i<distancias.size();i++){
            System.out.println(distancias.get(i).getIndice_coordenada()+" --->"+distancias.get(i).getInicio_distancia());
        }
*/
       
    }
    
    
}
