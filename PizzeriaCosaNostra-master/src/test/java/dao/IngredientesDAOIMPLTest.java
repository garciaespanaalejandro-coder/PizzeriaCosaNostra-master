package dao;

import model.Ingrediente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientesDAOIMPLTest {
    private IngredientesDAOIMPL dao;
    private final String ARCHIVO_PRUEBA = "test_ingredientes.dat";

    @BeforeEach
    public void setUp() {
        dao = new IngredientesDAOIMPL(ARCHIVO_PRUEBA);
    }

    @AfterEach
    public void tearDown() {
        File archivo = new File(ARCHIVO_PRUEBA);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    //TEST para recuperar ingredientes cuando el archivo no existe
    @Test
    public void testRecuperarIngredientes() {
        //NO creamos archivo antes - probamos el caso de error
        List<Ingrediente> resultado = dao.recuperarIngredientes();

        //Verificamos que retorna una lista vacía cuando no hay arvhivo
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    //TEST para recuperar ingredientes en un archivo con datos
    @Test
    public void testRecuperarIngredientes_ConDatos() {
        //Creamos archivo con datos DIRECTAMENTE
        List<Ingrediente> ingredientesEsperados = new ArrayList<>();
        ingredientesEsperados.add(new Ingrediente("1", "Tomate", "PROV1", "Verduras",
                false, false, false, false));
        try{
            crearArchivoConDatos(ingredientesEsperados);
        } catch (IOException e) {
            System.out.println("Error en: "+e.getMessage());
        }

        //Ejecutamos el metodo que queremos probar
        List<Ingrediente> resultado = dao.recuperarIngredientes();

        //Verificamos los resultados
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Tomate", resultado.get(0).getNombre());
    }

    // TEST para actualizar un ingrediente - NUEVO INGREDIENTE
    @Test
    public void testActualizarIngrediente_Nuevo() {
        //Creamos un nuevo ingrediente
        Ingrediente nuevo = new Ingrediente("1", "Tomate", "PROV1", "Verduras",
                false, false, false, false);

        //Ejecutamos el comando que queremos probar
        dao.actualizarIngrediente(nuevo);

        //Verificamos que se creó el archivo y tiene datos
        File archivo = new File(ARCHIVO_PRUEBA);
        assertTrue(archivo.exists());

        List<Ingrediente> resultado = dao.recuperarIngredientes();
        assertEquals(1, resultado.size());
        assertEquals("Tomate", resultado.get(0).getNombre());
    }

    //TEST para guardar ingredientes
    @Test
    public void testGuardarIngredientes() {

        // Creamos una lista y añadimos los ingredientes que posterior creamos
        List<Ingrediente> ingredientes = new ArrayList<>();

        //Creamos ingredientes y los añadimos a la lista
        ingredientes.add(new Ingrediente("1", "Azúcar", "PROV2", "Dulces",
                false, false, false, false));
        ingredientes.add(new Ingrediente("2", "Sal", "PROV3", "Salinas",
                false, false, false, false));

        //Ejecutamos el comando que queremos probar
        dao.guardarIngredientes(ingredientes);

        //Verificamos que el archivo existe
        File archivo = new File(ARCHIVO_PRUEBA);
        assertTrue(archivo.exists());

        //Verificamos los resultados
        List<Ingrediente> resultado = dao.recuperarIngredientes();
        assertEquals(2, resultado.size());
    }

    private void crearArchivoConDatos(List<Ingrediente> ingredientes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PRUEBA))) {
            oos.writeObject(ingredientes);
        }
    }
}