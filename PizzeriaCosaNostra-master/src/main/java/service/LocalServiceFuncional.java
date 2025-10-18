package service;

import dao.LocalDAO;
import dao.LocalDAOIMPL;
import model.Local;

import java.io.IOException;
import java.util.List;

public class LocalServiceFuncional implements LocalService{

    private LocalDAO localDAO;

    public LocalServiceFuncional(LocalDAO l){
        this.localDAO=l;
    }

    //Desde nuestro atributo llamamos a las funciones del PizzaDAOXML
    @Override
    public List<Local> listadoLocales() throws IOException {
        return localDAO.recuperarFichero();
    }

    @Override
    public void actualizarLocal(Local l) throws IOException {
        localDAO.actualizarLocal(l);
    }
}
