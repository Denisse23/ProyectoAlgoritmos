/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoalgoritmos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Denisse
 */
public class Metodos {
    public void crearArchivoEntrada() throws IOException{
        Random rnd = new Random();
        int camiones=3+rnd.nextInt(5);
        int tiendas=50+rnd.nextInt(75);

        int x[] = new int[tiendas];
        int y[] = new int[tiendas];
                
        for(int i=0;i<tiendas; i++){
            x[i] = rnd.nextInt(100);
            y[i] = rnd.nextInt(100);
        }
        
        int pepsi_x = rnd.nextInt(100);
        int pepsi_y = rnd.nextInt(100);
        FileWriter fichero = new FileWriter("./entrada.txt");
        fichero.write(camiones+"\n");
        fichero.write(pepsi_x+","+pepsi_y+"\n");
        for(int i=0;i<tiendas;i++){
            fichero.write(x[i]+","+ y[i]+"\n");
        }
        fichero.flush();
        fichero.close();
    }
    
    public Datos leerArchivoEntrada()throws IOException{
        FileReader fr = new FileReader ("./entrada.txt");
        BufferedReader br = new BufferedReader(fr);
        
        Datos d = new Datos();
        String linea = br.readLine();
        d.setCamiones(Integer.parseInt(linea));
        linea = br.readLine();
        ArrayList<coordenada> pep = new ArrayList();
        pep.add(new coordenada(Integer.parseInt(linea.split(",")[0]), Integer.parseInt(linea.split(",")[1])));
        d.setCoordenadaPepsi(pep);
        ArrayList<coordenada> tiendas_cor = new ArrayList();
        while((linea=br.readLine())!=null){
            tiendas_cor.add(new coordenada(Integer.parseInt(linea.split(",")[0]), Integer.parseInt(linea.split(",")[1])));
        }
        d.setCoordenadasTiendas(tiendas_cor);
        fr.close(); 
        
        return d;
    }
    
    public ArrayList<Distancia> getDistanciasCoordenas(Datos d){
        ArrayList<Distancia> distancias = new ArrayList();
        for(int i=0;i<d.getCoordenadasTiendas().size();i++){
            double distancia = Math.sqrt(Math.pow((d.getCoordenadasTiendas().get(i).getX()-d.getCoordenadaPepsi().get(0).getX()),2) + 
                                        Math.pow((d.getCoordenadasTiendas().get(i).getY()-d.getCoordenadaPepsi().get(0).getY()),2));
            distancias.add(new Distancia(distancia, i));
            
        }
        return distancias;
    }
    
    public void ordenarDistancias(ArrayList<Distancia> distancias){
        quickSort(distancias, 0, distancias.size()-1);
    }
    
   
    private void quickSort(ArrayList<Distancia> distancias, int izq, int der){
        Distancia pivote=distancias.get(izq); 
        int i=izq;
        int j=der; 
 
        while(i<j){            
            while(distancias.get(i).getInicio_distancia()<=pivote.getInicio_distancia() && i<j) 
                i++; 
            while(distancias.get(j).getInicio_distancia()>pivote.getInicio_distancia()) 
                j--;       
            if (i<j)
                swap(distancias, i, j);
            
        }    
        distancias.set(izq, distancias.get(j));
        distancias.set(j, pivote);
        if(izq<j-1)
            quickSort(distancias,izq,j-1); 
        if(j+1 <der)
            quickSort(distancias,j+1,der); 
        
    }
    
    private  void swap(ArrayList<Distancia> distancias, int pos1, int pos2){
        Distancia temp = distancias.get(pos1);
        distancias.set(pos1, distancias.get(pos2));
        distancias.set(pos2, temp);
    }
    
    public void asignarRutas(ArrayList<RutaCamion> rutas_camiones, ArrayList<Distancia> distancias, Datos datos_entrada){
        int i=0;
       
        while(i<rutas_camiones.size() && i<distancias.size()){
            rutas_camiones.get(i).addNodo(distancias.get(0).getIndice_coordenada());
            rutas_camiones.get(i).addTotal(distancias.get(0).getInicio_distancia());
            distancias.remove(0);
            i++;
        }
        int rutas_nodoagregado = 0;
        while(distancias.size()>0){
            Datos d = new Datos();
            ArrayList<coordenada> destino = new ArrayList();
            destino.add(datos_entrada.getCoordenadasTiendas().get(distancias.get(0).getIndice_coordenada()));
            d.setCoordenadaPepsi(destino);
            ArrayList<coordenada> rutas_anteriores = new ArrayList();
            ArrayList<Integer> ruta_pos = new ArrayList();
            for(int j=0; j<rutas_camiones.size();j++){
                if(!rutas_camiones.get(j).isNodoAgregado()){
                rutas_anteriores.add(datos_entrada.getCoordenadasTiendas().get(rutas_camiones.get(j).getNodos().
                                    get(rutas_camiones.get(j).getNodos().size()-1)));
                ruta_pos.add(j);
                }
            }
            d.setCoordenadasTiendas(rutas_anteriores);
            ArrayList<Distancia> dis = getDistanciasCoordenas(d);
            int pos = getIndiceMin(dis);
            rutas_camiones.get(ruta_pos.get(pos)).addNodo(distancias.get(0).getIndice_coordenada());
            rutas_camiones.get(ruta_pos.get(pos)).addTotal(distancias.get(0).getInicio_distancia());
            rutas_camiones.get(ruta_pos.get(pos)).setNodoAgregado(true);
            distancias.remove(0);
            rutas_nodoagregado++;
            if(rutas_nodoagregado==datos_entrada.getCamiones()){
                rutas_nodoagregado=0;
                for(int j=0; j<rutas_camiones.size();j++){
                    rutas_camiones.get(j).setNodoAgregado(false);
                }
            }
        }
        
        
        
        
    }
    private int getIndiceMin(ArrayList<Distancia> dis){
        int min = 0;
        for(int i=1;i<dis.size();i++){
            if(dis.get(i).getInicio_distancia()<dis.get(min).getInicio_distancia()){
                min = i;
            }
        }
        
        return min;
    }
    
    public void agregarInicioARuta(ArrayList<RutaCamion> rutas_camiones, Datos datos_entrada){
       
            Datos d = new Datos();
            d.setCoordenadaPepsi(datos_entrada.getCoordenadaPepsi());
            ArrayList<coordenada> rutas_anteriores = new ArrayList();
            for(int j=0; j<rutas_camiones.size();j++){
                rutas_anteriores.add(datos_entrada.getCoordenadasTiendas().get(rutas_camiones.get(j).getNodos().
                                    get(rutas_camiones.get(j).getNodos().size()-1)));
            }
            d.setCoordenadasTiendas(rutas_anteriores);
            ArrayList<Distancia> dis = getDistanciasCoordenas(d);
            for(int i=0;i<rutas_camiones.size();i++){
                rutas_camiones.get(i).addNodo(-1);
                rutas_camiones.get(i).addTotal(dis.get(i).getInicio_distancia());
            }
    }
    
    public void crearArchivoSalida(ArrayList<RutaCamion> rutas_camiones) throws IOException{
        
        FileWriter fichero = new FileWriter("./salida.txt");
        for(int i=0;i<rutas_camiones.size();i++){
            fichero.write(rutas_camiones.get(i).getNodos().size()+"\n");
            for(int j=0;j<rutas_camiones.get(i).getNodos().size();j++){
                fichero.write((rutas_camiones.get(i).getNodos().get(j)+1)+"\n");
            }
       
        }
        
        fichero.flush();
        fichero.close();
    }
    
    public void crearArchivoCalculos(ArrayList<RutaCamion> rutas_camiones) throws IOException{
        
        FileWriter fichero = new FileWriter("./calculos.txt");
        for(int i=0;i<rutas_camiones.size();i++){
            fichero.write("Ruta "+i+" Distancia:"+rutas_camiones.get(i).getTotalRuta()+"\n");
        }
        
        fichero.flush();
        fichero.close();
    }
  
}
