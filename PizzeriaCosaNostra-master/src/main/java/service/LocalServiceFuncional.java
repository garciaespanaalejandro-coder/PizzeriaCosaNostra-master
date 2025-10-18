package service;

import dao.LocalDAOIMPL;
import model.Local;

import java.io.IOException;
import java.util.List;

public class LocalServiceFuncional implements LocalService{

    private LocalDAOIMPL localDAOIMPL;

    public LocalServiceFuncional(LocalDAOIMPL localDAOIMPL){
        this.localDAOIMPL=localDAOIMPL;
    }
    @Override
    public List<Local> listadoLocales() throws IOException {
        return localDAOIMPL.recuperarFichero();
    }

    @Override
    public void actualizarLocal(Local l) throws IOException {
        localDAOIMPL.actualizarLocal(l);
    }
}
