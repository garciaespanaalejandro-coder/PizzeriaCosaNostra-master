package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandingPageDAOPropertiesTest {
    private LandingPageDAOProperties daoProp;
    private String testFilePath = "test_landing.properties";

    // Crear archivo temporal de prueba
    @BeforeEach
    public void setUp() throws IOException {

        Properties props = new Properties();
        props.setProperty("quienes_somos.ES", "Somos una cadena gallega");
        props.setProperty("quienes_somos.EN", "We are a Galician family-owned");
        props.setProperty("amor_productos.ES", "Nuestras pizzas nacen del amor");
        props.setProperty("experiencia.ES", "Venir a nuestra pizze");

        try (FileWriter writer = new FileWriter(testFilePath)) {
            props.store(writer, "Archivo de prueba");
        }

        daoProp = new LandingPageDAOProperties(testFilePath);
    }
    // eliminar archivo temporal de prueba
    @AfterEach
    public void tearDown() {
        File archi = new File(testFilePath);
        if (archi.exists()){
            archi.delete();
        }
    }
    //probar las clases comprobando resultados
    @Test
    public void testQuienesSomos_ES() {
        String resultado = daoProp.quienes_somos("ES");
        assertEquals("Somos una cadena gallega", resultado);
    }

    @Test
    public void testQuienesSomos_EN() {
        String resultado = daoProp.quienes_somos("EN");
        assertEquals("We are a Galician family-owned", resultado);
    }

    @Test
    public void testAmorProductos_ES() {
        String resultado = daoProp.amor_productos("ES");
        assertEquals("Nuestras pizzas nacen del amor", resultado);
    }

    @Test
    public void testExperiencia_ES() {
        String resultado = daoProp.experiencia("ES");
        assertEquals("Venir a nuestra pizze", resultado);
    }

    @Test
    public void testClaveNoExistente() {
        String resultado = daoProp.experiencia("FR");
        assertEquals("", resultado, "Debe devolver cadena vacía si no existe la clave");
    }
}

