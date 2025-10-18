package dao;

import model.Pizza;

import java.util.List;

public class PizzaDAOXML implements PizzaDAO{

    @Override
    public List<Pizza> recuperarPizzas() {
        return List.of();
    }

    @Override
    public void actualizarPizzas() {

    }

    public void guardarPizza (List<Pizza> lista){

    }

    @Override
    public void generarJson() {}
}
