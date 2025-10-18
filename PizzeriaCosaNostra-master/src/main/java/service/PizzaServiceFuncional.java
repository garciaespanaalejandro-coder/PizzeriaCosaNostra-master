package service;

import dao.PizzaDAO;
import model.Pizza;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class PizzaServiceFuncional implements PizzaService{
    //atributo que es la interface PizzaDAO
    private PizzaDAO pzDao;

    //Al construc, le pasamos un PizzaDAO y lo asignamos al atributo
    public PizzaServiceFuncional(PizzaDAO pzDao) {
        this.pzDao = pzDao;
    }

    @Override
    public List<Pizza> listadoPizzas() throws JAXBException {
        List<Pizza> list = new ArrayList<>(pzDao.recuperarPizzas());
        return list;
    }

    @Override
    public void actualizarPizza(Pizza p) throws JAXBException {
        pzDao.actualizarPizzas(p);

    }
}
