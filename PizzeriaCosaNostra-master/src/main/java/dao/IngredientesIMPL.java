package dao;

import model.Ingrediente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientesIMPL implements IngredientesDAO {

    String path="PizzeriaCosaNostra-master/src/main/resources/ingredientes.dat";

    public List<Ingrediente> recuperarIngredientes(){
        //deserializamos y retornamos una lista de ingredientes
        try (ObjectInputStream ois= new ObjectInputStream(new FileInputStream(path))){
            return (List<Ingrediente>) ois.readObject();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public void actualizarIngrediente(Ingrediente ing){
        //recuperamos la lista para poder trabajar con ella
        List<Ingrediente> listaIngredientes = recuperarIngredientes();
        boolean encontrado=false;
        //buscamos si el ingrediente ya existe
        for (int i=0; i<=listaIngredientes.size(); i++){
            Ingrediente actual= listaIngredientes.get(i);
            //comprobamos que si el id es igual, en caso de que sea igual lo modifica.
            if (actual.getId().equalsIgnoreCase(ing.getId())){
                listaIngredientes.add(i,ing);
                encontrado=true;
                break;
            }
        }

        //si no encontró el id igual, lo añade
        if (!encontrado){
            listaIngredientes.add(ing);
        }

        guardarIngredientes(listaIngredientes);
    }

    //le pasamos una lista para poder saber los ingredientes que vamos a querer serializar posteriormente
    public void guardarIngredientes(List<Ingrediente> lista){
        //serializamos la lista de ingredientes
        try(ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(lista);
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
