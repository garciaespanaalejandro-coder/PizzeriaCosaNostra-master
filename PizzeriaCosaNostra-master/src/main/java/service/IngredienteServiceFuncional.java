package service;

import dao.IngredientesIMPL;
import model.Ingrediente;

import java.io.IOException;
import java.util.List;

public class IngredienteServiceFuncional implements IngredienteService{
    private IngredientesIMPL IMPL;

    //Al contructor le pasamos un IngredientesIMPPL y lo asignamos al atributo
    public IngredienteServiceFuncional(IngredientesIMPL d){
        this.IMPL=d;
    }

    @Override
    public List<Ingrediente> listadoIngredientes() throws IOException, ClassNotFoundException {
        return IMPL.recuperarIngredientes();
    }

    @Override
    public void actualizarIngrediente(Ingrediente ing) throws IOException, ClassNotFoundException {
        IMPL.actualizarIngrediente(ing);
    }

}
