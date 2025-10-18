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

    // TEST PARA recuperarIngredientes() - ARCHIVO NO EXISTE
    @Test
    public void testRecuperarIngredientes() {
        // NO creamos archivo antes - probamos el caso de error
        List<Ingrediente> resultado = dao.recuperarIngredientes();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    // TEST PARA recuperarIngredientes() - ARCHIVO CON DATOS
    @Test
    public void testRecuperarIngredientes_ConDatos() throws IOException {
        // Arrange: Creamos archivo con datos DIRECTAMENTE
        List<Ingrediente> ingredientesEsperados = new ArrayList<>();
        ingredientesEsperados.add(new Ingrediente("1", "Tomate", "PROV1", "Verduras",
                false, false, false, false));
        crearArchivoConDatos(ingredientesEsperados);

        // Act
        List<Ingrediente> resultado = dao.recuperarIngredientes();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Tomate", resultado.get(0).getNombre());
    }

    // TEST PARA actualizarIngrediente() - NUEVO INGREDIENTE
    @Test
    public void testActualizarIngrediente_Nuevo() {
        // Arrange
        Ingrediente nuevo = new Ingrediente("1", "Tomate", "PROV1", "Verduras",
                false, false, false, false);

        // Act
        dao.actualizarIngrediente(nuevo);

        // Assert: Verificamos que se creó el archivo y tiene datos
        File archivo = new File(ARCHIVO_PRUEBA);
        assertTrue(archivo.exists());

        List<Ingrediente> resultado = dao.recuperarIngredientes();
        assertEquals(1, resultado.size());
        assertEquals("Tomate", resultado.get(0).getNombre());
    }

    // TEST PARA guardarIngredientes()
    @Test
    public void testGuardarIngredientes() {
        // Arrange
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente("1", "Azúcar", "PROV2", "Dulces",
                false, false, false, false));
        ingredientes.add(new Ingrediente("2", "Sal", "PROV3", "Salinas",
                false, false, false, false));

        // Act
        dao.guardarIngredientes(ingredientes);

        // Assert
        File archivo = new File(ARCHIVO_PRUEBA);
        assertTrue(archivo.exists());

        List<Ingrediente> resultado = dao.recuperarIngredientes();
        assertEquals(2, resultado.size());
    }

    private void crearArchivoConDatos(List<Ingrediente> ingredientes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PRUEBA))) {
            oos.writeObject(ingredientes);
        }
    }
}