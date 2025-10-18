package service;
import dao.LandingPageDAO;
import dao.LandingPageDAOProperties;
import model.LandingPageContent;

public class LandingPageServiceFuncional implements LandingPageService {
    //atributo que es la interface LandingPageDAO
    private LandingPageDAO LP;

    //Al construc, le pasamos un LandingPageDAO y lo asignamos al atributo
    public LandingPageServiceFuncional(LandingPageDAO d) {
        this.LP=d;
    }

    @Override
    public LandingPageContent getLandingPageContent(String idioma) {
        //Creamos un LandingPageContent(que es el model) y le asignamos las Strings
        //Desde nuestro atributo llamamos a las funciones del LandingPageDAOProperties
        LandingPageContent segunIdioma= new LandingPageContent(LP.quienes_somos(idioma),
                                                                LP.amor_productos(idioma),
                                                                LP.experiencia(idioma));
        return segunIdioma;
    }
}
