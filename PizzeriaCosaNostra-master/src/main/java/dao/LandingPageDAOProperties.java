package dao;
import model.LandingPageContent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LandingPageDAOProperties implements LandingPageDAO{

    public String leerProperties(String inicioKey, String idioma) {
        String valor="";
        try (FileInputStream fis = new FileInputStream("PizzeriaCosaNostra-master/src/main/resources/config.properties")) {
            Properties props = new Properties();
            props.load(fis);
            valor = props.getProperty(inicioKey + idioma);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return valor;
    }

    @Override
    public String quienes_somos(String idioma) {

        return leerProperties("quienes_somos.", idioma);
    }
    @Override
    public String amor_productos(String idioma) {
        return leerProperties("amor_productos.", idioma);
    }
    @Override
    public String experiencia(String idioma) {
        return leerProperties("experiencia.", idioma);
    }
}
