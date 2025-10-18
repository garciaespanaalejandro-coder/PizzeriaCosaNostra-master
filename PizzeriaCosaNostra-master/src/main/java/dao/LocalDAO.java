package dao;

import model.Local;

import java.util.List;

public interface LocalDAO {
    public List<Local> recuperarFichero();
    public void actualizarLocal(Local lo);

}
