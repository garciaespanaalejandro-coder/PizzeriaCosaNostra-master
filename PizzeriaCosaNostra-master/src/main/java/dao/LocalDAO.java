package dao;

import model.Local;

import java.util.List;

public interface LocalDAO {
    public List<Local> leerFichero();
    public void actualizarLocal(Local lo);

}
