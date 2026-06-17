package de.uni_bayreuth.se.demo.service;

import de.uni_bayreuth.se.demo.model.Coffee;
import de.uni_bayreuth.se.demo.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    public Coffee getCoffeeByName(String name) {
        return coffeeRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Coffee shop not found: " + name));
    }

    public List<Coffee> getAccessibleCoffees() {
        return coffeeRepository.findAccessible();
    }

    public Coffee updateCoffeeByName(String name, Coffee updatedCoffee) {
        // NEU für Aufgabe 4: Manuelle Validierung ohne neue Imports
        if (updatedCoffee.name() == null || updatedCoffee.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Der Name darf nicht leer sein.");
        }
        if (updatedCoffee.price() <= 0) {
            throw new IllegalArgumentException("Der Preis muss größer als 0 sein.");
        }

        // Prüfen, ob der Kaffee überhaupt existiert
        Coffee existing = coffeeRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Coffee konnte nicht geupdatet werden: " + name));

        // Das Update im Repository ausführen
        return coffeeRepository.update(name, updatedCoffee);
    }
}