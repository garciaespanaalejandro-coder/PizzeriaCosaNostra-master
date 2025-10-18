package dao;

import model.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class LocalDAOIMPLTest {
    private LocalDAOIMPL dao;
    private final String ARCHIVO_PRUEBA= "test_local.csv";

    //Creamos un archivo de prueba con la dirección
    @BeforeEach
    public void setUp(){
        dao= new LocalDAOIMPL(ARCHIVO_PRUEBA);
    }

    //Eliminamos el archivo de prueba después hacer las pruebas (test)
    @AfterEach
    public void tearDown(){
        File archivo= new File(ARCHIVO_PRUEBA);
        if (archivo.exists()){
            archivo.delete();
        }
    }

    //TEST para recuperarLocales cuando el archivo no existe
    @Test
    public void testRecuperarLocales(){
        //Ejecutamos el método que queremos probar
        List<Local> resultado= dao.recuperarFichero();

        //Verificamos que retorna una lista vacía cuando no hay archivo
    }

}
