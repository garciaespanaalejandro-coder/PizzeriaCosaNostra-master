/*package dao;

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

            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizarLocal(Local lo) {

    }
}*/
