package dao;

import model.Ingrediente;
import model.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    //TEST para recuperar locales cuando el archivo no existe
    @Test
    public void testRecuperarLocales(){
        //Ejecutamos el metodo que queremos probar
        List<Local> resultado= dao.recuperarFichero();

        //Verificamos que retorna una lista vacía cuando no hay archivo
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    //TEST para recuperar locales en un archivo con datos
    @Test
    public void testRecuperarLocales_ConDatos(){
        //Creamos archivo con datos DIRECTAMENTE
        List<Local> localesEsperados= new ArrayList<>();
        localesEsperados.add(new Local("1", "CasaPaco", "PROV2", "12324",
                "Coruña", "Cambre", "29389", 123));

        crearArchivoConDatos(localesEsperados);

        //Ejecutamos el metodo que queremos probar
        List<Local> resultado= dao.recuperarFichero();

        //Verficamos resultados
        assertNotNull(resultado);
        assertEquals(1,resultado.size());
        assertEquals("CasaPaco",resultado.get(0).getNombre());
    }

    //TEST para actualizar un local- NUEVO INGREDIENTE
    @Test
    public void testActualizarLocal_Nuevo(){
        //Creamos un nuevo local
        Local nuevo= new Local("4", "CasaFredi", "PR23V2", "12324",
                "Coruña", "Cambre", "29389", 123);

        //Ejecutamos el comando que queremos probar
        dao.actualizarLocal(nuevo);

        //Verificamos que se creó el archivo y tiene datos
        File archivo= new File(ARCHIVO_PRUEBA);
        assertTrue(archivo.exists());

        //Verificamos resultados
        List<Local> resultado = dao.recuperarFichero();
        assertEquals(1, resultado.size());
        assertEquals("CasaFredi", resultado.get(0).getNombre());
    }

    //TEST para guardar locales
    @Test
    public void testGuardarLocales(){
        //Creamos una lista de tipo Local
        List<Local> locales= new ArrayList<>();

        //Creamos locales y los añadimos a la lista
        locales.add(new Local("45", "casaManolo", "PR23V2", "12324",
                "Coruña", "Cambre", "29389", 123));
        locales.add(new Local("94", "casaManuela", "PR23V2", "12324",
                "Coruña", "Cambre", "29389", 123));

        //Ejecutamos el comando que queremos probar
        dao.guardarCSV(locales);

        //Verificamos que el archivo existe
        File local= new File(ARCHIVO_PRUEBA);
        assertTrue(local.exists());

        //Verificamos los resultados
        List<Local> resultado= dao.recuperarFichero();
        assertEquals(2,resultado.size());
    }

    public void crearArchivoConDatos( List <Local> list){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PRUEBA    ))){
            //Sobreescribimos la cabecera para no perderla en el archivo CSV
            String cabecera= "ID,Nombre,Dirección,CodigoPostal,Población,Provincia,Teléfono,m2";
            writer.write(cabecera);
            writer.newLine();

            for (Local lo: list){
                String lineaCSV= lo.getId()+","+
                        lo.getNombre()+","+
                        lo.getDireccion()+","+
                        lo.getCodigoPostal()+","+
                        lo.getPoblacion()+","+
                        lo.getProvincia()+","+
                        lo.getTelefono()+","+
                        lo.getM2()+",";

                //Escribimos la linea en el fichero
                writer.write(lineaCSV);
                writer.newLine();
            }

        }catch (IOException e){
            System.out.println("Error en: "+e.getMessage());
        }
    }




}
