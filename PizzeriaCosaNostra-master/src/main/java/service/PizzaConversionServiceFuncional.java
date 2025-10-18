package service;

import dao.PizzaDAO;

public class PizzaConversionServiceFuncional implements PizzaConversionService{
    //tenemos 2 atributos, 2 DAOs diferentes, para con uno leer xml y el otro para escribir el JSON
    private PizzaDAO pDAOXML;
    private PizzaDAO pDAOJSON;

    public PizzaConversionServiceFuncional(PizzaDAO pDAOXML, PizzaDAO pDAOJSON) {
        this.pDAOXML = pDAOXML;
        this.pDAOJSON = pDAOJSON;
    }

    @Override
    public boolean generarFicheroPizzas() {
        boolean creacionJSON = false;

        // a .generarJson() de la clase PizzaDAOJSON le pasamos la funcion .recuperarPizzas() de la clase PizzaDAOXML
        try{
            pDAOJSON.generarJson(pDAOXML.recuperarPizzas());
            creacionJSON= true;
        } catch (Exception e) {
            System.out.println("Error al generar el archivo .JSON");
            creacionJSON=false;
        }
        return creacionJSON;
    }
}
