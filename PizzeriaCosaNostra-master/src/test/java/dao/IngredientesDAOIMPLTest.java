package dao;

import model.Ingrediente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientesDAOIMPLTest {
    // Archivo que se usará solo para pruebas
    private static final String TEST_PATH = "PizzeriaCosaNostra-master/src/test/resources/ingredientes_test.dat";

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
        lista.add(new Ingrediente("1113","pan","paco","paquito",true,
                false,false,false));

        // Guardamos la lista en un archivo .dat para simular el archivo real
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TEST_PATH))) {
            oos.writeObject(lista);
        }

    }

    /*
    * Test para recuperarIngredientes()
    * Verifica que se lea correctamente la lista desde el archivo
    */

    @Test
    void testRecuperarIngredientes(){
        //Llamamos al metodo que queremos probar
        List<Ingrediente> ingredientes=dao.recuperarIngredientes();

        //Comprobamos que la lista tenga 2 ingredientes
        assertEquals(2, ingredientes.size());

        //Comprobamos que el primer ingrediente se llame patata
        assertEquals("patata",ingredientes.get(0).getNombre());
    }

    /*
    * Test para actualizarIngrediente()
    * Verifica que se añada un nuevo ingrediente si no existe
    */

    @Test
    void testActualizarIngrediente() {
        // Creamos un ingrediente nuevo
        Ingrediente nuevo = new Ingrediente("1213","atun","paco","paquito",true,
                false,false,false);

        // Llamamos al método que debería añadirlo
        dao.actualizarIngrediente(nuevo);

        // Recuperamos la lista actualizada
        List<Ingrediente> lista = dao.recuperarIngredientes();

        // Comprobamos de manera simple si el ingrediente se añadió
        boolean encontrado = false; // variable que indica si encontramos el ingrediente
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals("3")) {
                encontrado = true;
                break; // salimos del bucle al encontrarlo
            }
        }

        // Verificación final: el ingrediente debe estar en la lista
        assertTrue(encontrado);
    }

    /**
     * Test para guardarIngredientes()
     * Verifica que se guarde correctamente una lista en el archivo
     */
    @Test
    void testGuardarIngredientes() {
        // Creamos una nueva lista de ingredientes para guardar
        List<Ingrediente> lista = new ArrayList<>();
        lista.add(new Ingrediente("2213","calaamr","paco","paquito",true,
                false,false,false));

        // Guardamos la lista usando el método del DAO
        dao.guardarIngredientes(lista);

        // Recuperamos la lista desde el archivo para comprobar
        List<Ingrediente> leidos = dao.recuperarIngredientes();

        // Comprobamos que se guardó exactamente 1 ingrediente
        assertEquals(1, leidos.size());

        // Comprobamos que el nombre del ingrediente guardado sea "Jamón"
        assertEquals("Jamón", leidos.get(0).getNombre());
    }

    /**
     * @AfterEach
     * Este método se ejecuta después de cada test
     * Sirve para limpiar el archivo de prueba y que no afecte a otros tests
     */
    @AfterEach
    void tearDown() {
        File f = new File(TEST_PATH);
        if (f.exists()) {
            f.delete(); // borramos el archivo
        }
    }
}
