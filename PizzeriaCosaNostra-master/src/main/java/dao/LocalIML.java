package dao;

import model.Ingrediente;
import model.Local;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalIML implements LocalDAO{

    private String path;
    //Le pasamos la ruta del archivo csv para poder trabajar con el archivo posteriormente
    public LocalIML(String path){
        this.path=path;
    }
    @Override
    public List<Local> leerFichero() {
        List<Local> locales = new ArrayList<>();
        try(BufferedReader reader= new BufferedReader(new FileReader(path))){
            String linea;
            boolean inicial=true;
            while((linea=reader.readLine())!=null){
                //Saltamos la linea de la cabecera
                if (inicial){
                    inicial=false;
                    continue;
                }
                //Separamos la linea en campos usando la coma como delimitador de palabras
                String[] campos= linea.split(",");

                String id= campos[1];
                String nombre= campos[2];
                String direccion= campos[3];
                String CP=campos[4];
                String poblacion= campos[5];
                String provincia= campos[6];
                String telefono= campos[7];
                String m2=campos[8];
                //Parseamos los metros cuadrados a Int porque la clase local pide que los m2 sean de tipo int y no de tipo String
                int m2R=Integer.parseInt(m2);
                Local lo= new Local(id,nombre,direccion,CP,poblacion,provincia,telefono,m2R );
                locales.add(lo);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return locales;
    }

    @Override
    public void actualizarLocal(Local lo) {

    }
}
