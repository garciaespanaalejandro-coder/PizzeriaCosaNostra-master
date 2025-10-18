package dao;

import model.Ingrediente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.File;
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

    // TEST para recuperarIngredientes cuando el archivo no existe
    @Test
    public void testRecuperarIngredientes(){
        //Ejecutamos el método que queremos probar
        List<Ingrediente> resultado = dao.recuperarIngredientes();

        //Verificamos que retorna una lista vacía cuando no hay archivo
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    // TEST para actualizarIngrediente cuando agregamos un nuevo ingrediente
    @Test
    public void testActualizarIngrediente_Nuevo() {
        //Preparamos un ingrediente nuevo
        Ingrediente nuevoIngrediente = new Ingrediente("1", "Tomate", "PROV001", "Verduras Frescas",
                false, false, false, false);

        //Ejecutamos el método actualizarIngrediente
        dao.actualizarIngrediente(nuevoIngrediente);

        //Verificamos que se agregó correctamente
        List<Ingrediente> resultado = dao.recuperarIngredientes();
        assertEquals(1, resultado.size());
        assertEquals("Tomate", resultado.get(0).getNombre());
    }

    // TEST para actualizarIngrediente cuando modificamos un ingrediente existente
    @Test
    public void testActualizarIngrediente_Modificar() {
        // Arrange: Creamos y guardamos un ingrediente
        Ingrediente ingredienteOriginal = new Ingrediente("1", "Harina", "PROV001", "Molinos",
                true, false, false, false);
        dao.actualizarIngrediente(ingredienteOriginal);

        // Creamos otro ingrediente con el mismo ID para modificar
        Ingrediente ingredienteModificado = new Ingrediente("1", "Harina Integral", "PROV001", "Molinos Salud",
                false, false, false, false);

        //Ejecutamos actualizarIngrediente con el mismo ID
        dao.actualizarIngrediente(ingredienteModificado);

        // Verificamos que se modificó y no se duplicó
        List<Ingrediente> resultado = dao.recuperarIngredientes();
        assertEquals(1, resultado.size());
        assertEquals("Harina Integral", resultado.get(0).getNombre());
        assertFalse(resultado.get(0).isGluten());
    }

    // TEST para guardarIngredientes, guardamos una lista completa
    @Test
    public void testGuardarIngredientes() {
        //Creamos una lista de ingredientes
        Ingrediente ingrediente1 = new Ingrediente("1", "Azúcar", "PROV002", "Dulces S.A.",
                false, false, false, false);
        Ingrediente ingrediente2 = new Ingrediente("2", "Sal", "PROV003", "Salinas",
                false, false, false, false);

        // Primero guardamos individualmente para crear la lista
        dao.actualizarIngrediente(ingrediente1);
        dao.actualizarIngrediente(ingrediente2);

        // Recuperamos la lista actual
        List<Ingrediente> listaActual = dao.recuperarIngredientes();

        //Ejecutamos guardarIngredientes con la lista
        dao.guardarIngredientes(listaActual);

        //Verificamos que los datos se mantienen después de guardar
        List<Ingrediente> resultado = dao.recuperarIngredientes();
        assertEquals(2, resultado.size());
        assertEquals("Azúcar", resultado.get(0).getNombre());
        assertEquals("Sal", resultado.get(1).getNombre());
    }
}
