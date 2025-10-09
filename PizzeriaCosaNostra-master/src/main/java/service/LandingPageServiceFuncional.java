package service;
import dao.LandingPageDAO;
import dao.LandingPageDAOProperties;
import model.LandingPageContent;

public class LandingPageServiceFuncional implements LandingPageService {

    private LandingPageDAO LP;

    public LandingPageServiceFuncional(LandingPageDAO d) {
        this.LP=d;
    }

    @Override
    public LandingPageContent getLandingPageContent(String idioma) {

        LandingPageContent segunIdioma= new LandingPageContent(LP.quienes_somos(idioma),
                                                                LP.amor_productos(idioma),
                                                                LP.experiencia(idioma));
        return segunIdioma;
    }
}
