package dao;

import model.Ingrediente;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class IngredientesDAOIMPLTest {
    // Archivo que se usará solo para pruebas
    private static final String TEST_PATH = "src/test/resources/ingredientes_test.dat";

    // Instancia del DAO
    private IngredientesDAOIMPL dao;

    /*
    * Este metodo se ejecuta antes de cada test
    * Sirve para crear un archivo de prueba con datos iniciales
    */

    @BeforeEach
    void setUp() throws Exception{
        dao= new IngredientesDAOIMPL(TEST_PATH);

        //Creamos una lista de ingredientes inicial
        List<Ingrediente> lista= new ArrayList<>();
        lista.add(new Ingrediente("1112","patata","pepe","pepe",true,
                true,false,false));
    }
}
