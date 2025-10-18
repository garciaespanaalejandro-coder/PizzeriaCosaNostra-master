package dao;
import model.LandingPageContent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LandingPageDAOProperties implements LandingPageDAO{
    private String Path;

    public LandingPageDAOProperties(String path) {
        this.Path=path;
    }

    //Funciones sobreescritas para recuperar el valor de cada apartado
    @Override
    public String quienes_somos(String idioma) {
        return leerProperties("quienes_somos.", idioma);
    }
    @Override
    public String amor_productos(String idioma) {
        return leerProperties("amor_productos.", idioma);
    }
    @Override
    public String experiencia(String idioma) {return leerProperties("experiencia.", idioma);}

    //Funcion de lectura de fichero y que devuelve el valor segun la clave y idioma
    public String leerProperties(String inicioKey, String idioma) {
        String valor="";
        try (FileInputStream fis = new FileInputStream(this.Path)) {
            Properties props = new Properties();
            props.load(fis);
            //Unimos inicioKey y idioma para generar la clave entera
            valor = props.getProperty(inicioKey + idioma, "");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return valor;
    }
}
