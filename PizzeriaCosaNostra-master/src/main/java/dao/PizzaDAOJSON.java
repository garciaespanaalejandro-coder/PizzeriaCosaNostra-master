package dao;

import com.google.gson.Gson;
import model.Pizza;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAOJSON implements  PizzaDAO{
    private String pathJSON;
    private Gson gson;


    public PizzaDAOJSON(String pathJSON) {
        this.pathJSON = pathJSON;
        this.gson = new Gson();
    }
    //dejamos vacio porque no lo va usar
    @Override
    public List<Pizza> recuperarPizzas() {
        return new ArrayList<>();
    }

    //dejamos vacio porque no lo va usar
    @Override
    public boolean actualizarPizzas(Pizza pizz) {
        return false;
    }

    @Override
    public void generarJson(List<Pizza> listPiz) {
        try {
            // Guardar los cambios en el archivo JSON
            Writer writer = new FileWriter(this.pathJSON);
            //le pasamos la lista de pizzas y el writer
            gson.toJson(listPiz, writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error al guardar cambios en JSON: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
