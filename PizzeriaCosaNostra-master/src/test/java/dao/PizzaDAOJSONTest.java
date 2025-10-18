package dao;

import com.google.gson.Gson;
import model.Pizza;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PizzaDAOJSONTest {
    private PizzaDAOJSON daoJson;
    private String testJsonPath = "test_pizza.json";
    private Gson gson = new Gson();

    @BeforeEach
    public void setUp() throws IOException {

        daoJson = new PizzaDAOJSON(testJsonPath);

        File fil = new File(testJsonPath);
        if (fil.exists()) {
            fil.delete();
        }
    }

    // eliminar archivo temporal de prueba
    @AfterEach
    public void tearDown() {
        File archi = new File(testJsonPath);
        if (archi.exists()){
            archi.delete();
        }
    }

    @Test
        public void testGenerarJson_CrearArchivo() {

            Pizza piz1 = new Pizza("1", "Margarita", "esta rica", 500, 15.2, 30, new ArrayList<String>());
            Pizza piz2 = new Pizza("2", "Tutto", "esta muy rica", 600, 20.5, 40, new ArrayList<String>());

            List<Pizza> lista = new ArrayList<>();
            lista.add(piz1);
            lista.add(piz2);

            daoJson.generarJson(lista);

            File file = new File(testJsonPath);
            assertTrue(file.exists(), "El archivo JSON debe haberse creado");
            assertTrue(file.length() > 0, "El archivo JSON no debe estar vacío");
        }

    @Test
    public void testGenerarJson_ContenidoCorrecto() throws IOException {
        List<Pizza> lista = new ArrayList<>();
        lista.add(new Pizza("1", "Margarita", "esta rica", 500, 15.2, 30, new ArrayList<String>()));

        daoJson.generarJson(lista);

        // Leer contenido del archivo y verificar estructura JSON
        try (Reader reader = new FileReader(testJsonPath)) {
            Pizza[] pizzas = this.gson.fromJson(reader, Pizza[].class);
            Pizza pizz = pizzas[0];

            assertEquals("1", pizz.getId());
            assertEquals("Margarita", pizz.getNombre());
            assertEquals("esta rica", pizz.getDescripcion());
            assertEquals(500, pizz.getCalorias());
            assertEquals(15.2, pizz.getPrecio());

        }
    }

    @Test
    public void testGenerarJson_ListaVacia() throws IOException {
        List<Pizza> pizz = new ArrayList<>();
        daoJson.generarJson(pizz);

        File file = new File(testJsonPath);
        assertTrue(file.exists(), "Debe crear el archivo aunque la lista está vacía");

        try (FileReader reader = new FileReader(testJsonPath)) {
            Pizza[] pizzasLeidas = gson.fromJson(reader, Pizza[].class);
            assertEquals(0, pizzasLeidas.length, "La lista leida tiene que estar vacía");
        }
    }

}
