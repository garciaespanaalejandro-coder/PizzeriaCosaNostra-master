package dao;

import model.Carta;
import model.Pizza;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaDAOXMLTest {
    private String testXMLPath= "Test_Pizza.xml";
    private PizzaDAOXML daoXML;

    // Crear archivo temporal
    @BeforeEach
    public void setUp() throws Exception {
        Carta car = new Carta();

        List<Pizza> lista = new ArrayList<>();
        Pizza p1 = new Pizza("1", "Margarita", "esta rica", 500, 15.2, 30, new ArrayList<String>());
        Pizza p2 = new Pizza("2", "Tutto", "esta muy rica", 600, 20.5, 40, new ArrayList<String>());

        lista.add(p1);
        lista.add(p2);
        car.setPizzas(lista);

        // Guardar en un XML
        JAXBContext context = JAXBContext.newInstance(Carta.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshaller.marshal(car, new File(testXMLPath));

        daoXML = new PizzaDAOXML(testXMLPath);
    }

    // eliminar archivo temporal de prueba
    @AfterEach
    public void tearDown() {
        File archi = new File(testXMLPath);
        if (archi.exists()){
            archi.delete();
        }
    }

    // comprobar el recuperar pizzas
    @Test
    void testRecuperarPizzas() {
        List<Pizza> pizzas = daoXML.recuperarPizzas();
        assertEquals(2, pizzas.size(), "Debería recuperar 2 pizzas");
        assertEquals("Margarita", pizzas.get(0).getNombre());
    }

    //comprobar el actualizar una existente
    @Test
    void testActualizarPizzaExistente() {
        Pizza nueva = new Pizza("1", "Margarita Deluxe", "con mucho queso", 850, 8.0, 15, new ArrayList<String>());
        boolean result = daoXML.actualizarPizzas(nueva);

        assertTrue(result); // xa existía

        List<Pizza> pizzas = daoXML.recuperarPizzas();
        assertEquals("Margarita Deluxe", pizzas.get(0).getNombre());
    }

    // comprobar el agregar una nueva
    @Test
    void testAgregarPizzaNueva() {
        Pizza nueva = new Pizza("3", "Cuatro quesos", "mucho de quesos", 1200, 15.0, 11, new ArrayList<String>());
        boolean actualizado = daoXML.actualizarPizzas(nueva);

        assertFalse(actualizado); // porque non existía

        List<Pizza> pizzas = daoXML.recuperarPizzas();
        assertEquals(3, pizzas.size(), "tiene que haber 3 pizzas al agregar una nueva");
    }



}
