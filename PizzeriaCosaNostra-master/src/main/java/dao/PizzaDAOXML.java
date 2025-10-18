package dao;

import model.Carta;
import model.Pizza;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAOXML implements PizzaDAO{
    private String path;

    public PizzaDAOXML(String path) {
        this.path = path;
    }

    @Override
    public List<Pizza> recuperarPizzas() {
        try {
            // Crear o contexto JAXB para a clase raíz
            JAXBContext context = JAXBContext.newInstance(Carta.class);

            // Crear o unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Ler o ficheiro XML e convertelo a objeto Java
            Carta listaPizzas = (Carta) unmarshaller.unmarshal(new File(this.path));

            return listaPizzas.getPizzas();

        } catch (JAXBException e) {
            System.err.println("Error al leer el archivo Carta.xml: " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<Pizza>();

    }

    @Override
    public boolean actualizarPizzas(Pizza nueva) {
        //Creamos una lista del contenido Carta.xml llamando a recuperarPizzas()
        List<Pizza> lista = this.recuperarPizzas();
        boolean encontrado = false;
        // recorremos la lista
        for (int i = 0; i< lista.size(); i++){
            //creamos una pizza con la pizza adquirida mediante el .get(i)
            Pizza actual = lista.get(i);
            //Si coincide su ID, se elimina la actual y se añade la nueva
            if (actual.getId().equals(nueva.getId())){
                lista.remove(i);
                lista.add(i,nueva);
                encontrado = true;
                break;
            }
        }
        // si no coinciden añade la nueva a la lista
        if (!encontrado){
            lista.add(nueva);
        }
        //creamos una nueva carta y se la pasamos a guardarPizza()
        Carta car = new Carta();
        car.setPizzas(lista);
        this.guardarPizza(car);

        return encontrado;

    }

    public void guardarPizza (Carta car){
        try {
            JAXBContext context = JAXBContext.newInstance(Carta.class);
            Marshaller marshaller = context.createMarshaller();
            //Formato e indentacion
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //Guardamos en los ficheros
            marshaller.marshal(car, new File(this.path));
        } catch (JAXBException e) {
            System.err.println("Error al guardar cambios: " + e.getMessage());
        }

    }

    @Override
    public void generarJson(List<Pizza> listPIz) {}
}
