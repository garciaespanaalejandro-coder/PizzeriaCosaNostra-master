package dao;

import model.Ingrediente;
import model.Local;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalDAOIMPL implements LocalDAO{

    private String path;
    //Le pasamos la ruta del archivo csv para poder trabajar con el archivo posteriormente
    public LocalDAOIMPL(String path){
        this.path=path;
    }
    @Override
    public List<Local> recuperarFichero() {
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

                //guardamos los campos en Strings para poder crear un objeto y meterlo en el arraylist
                String id = campos[0];
                String nombre = campos[1];
                String direccion = campos[2];
                String CP = campos[3];
                String poblacion = campos[4];
                String provincia = campos[5];
                String telefono = campos[6];
                String m2=campos[7];
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
        List<Local> listaLocales = recuperarFichero();
        boolean encontrado = false;
        //buscamos si el ingrediente ya existe
        for (int i = 0; i < listaLocales.size(); i++) {
            Local actual = listaLocales.get(i);
            //comprobamos si el id es igual, en caso de que sea igual lo modifica.
            if (actual.getId().equalsIgnoreCase(lo.getId())) {
                listaLocales.set(i, lo);
                encontrado = true;
                break;
            }
        }

        //si no encontró el id igual, lo añade
        if (!encontrado) {
            listaLocales.add(lo);
        }

    }

    public void guardarCSV( List <Local> list){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            //Sobreescribimos la cabecera para no perderla en el archivo CSV
            String cabecera= "ID,Nombre,Dirección,CodigoPostal,Población,Provincia,Teléfono,m2";
            writer.write(cabecera);
            writer.newLine();

            for (Local lo: list){
                String lineaCSV= lo.getId()+","+
                        lo.getNombre()+","+
                        lo.getDireccion()+","+
                        lo.getCodigoPostal()+","+
                        lo.getPoblacion()+","+
                        lo.getProvincia()+","+
                        lo.getTelefono()+","+
                        lo.getM2()+",";

                //Escribimos la linea en el fichero
                writer.write(lineaCSV);
                writer.newLine();
            }

        }catch (IOException e){
            System.out.println("Error en: "+e.getMessage());
        }
    }
}
