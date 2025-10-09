package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="Carta")
public class Carta implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Pizza> pizzas = new ArrayList<>();

    public Carta() { }

    @XmlElement(name="Pizza")
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}

