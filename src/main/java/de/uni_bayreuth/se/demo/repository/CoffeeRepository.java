package de.uni_bayreuth.se.demo.repository;

import de.uni_bayreuth.se.demo.model.Coffee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CoffeeRepository {

    // new java.util.ArrayList<>(...) macht die Liste veränderbar, ohne einen neuen import zu benötigen!
    private List<Coffee> coffees = new java.util.ArrayList<>(List.of(
            new Coffee(1L, "Campus Cafe", 2.50, true),
            new Coffee(2L, "Library Coffee", 2.00, false),
            new Coffee(3L, "Botanical Beans", 3.20, true),
            new Coffee(4L, "Informatik", 5.78, false)
    ));

    public List<Coffee> findAll() {
        return coffees;
    }

    public Optional<Coffee> findByName(String name) {
        return coffees.stream()
                .filter(c -> c.name().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Coffee> findAccessible() {
        return coffees.stream()
                .filter(Coffee::wheelchairAccessible)
                .toList();
    }

    // NEU: Update-Methode (Aufgabe 3)
    public Coffee update(String name, Coffee updatedCoffee) {
        for (int i = 0; i < coffees.size(); i++) {
            Coffee c = coffees.get(i);
            if (c.name().equalsIgnoreCase(name)) {
                // Wir erstellen ein neues Coffee-Objekt mit der ALTEN ID, aber den NEUEN Werten
                Coffee toSave = new Coffee(c.id(), updatedCoffee.name(), updatedCoffee.price(), updatedCoffee.wheelchairAccessible());
                coffees.set(i, toSave);
                return toSave;
            }
        }
        return null;
    }
}