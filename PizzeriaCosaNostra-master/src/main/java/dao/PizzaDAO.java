package dao;

import model.Pizza;

import java.util.List;

public interface PizzaDAO {
    public List<Pizza> recuperarPizzas();
    public void actualizarPizzas();
    public void generarJson();

}
