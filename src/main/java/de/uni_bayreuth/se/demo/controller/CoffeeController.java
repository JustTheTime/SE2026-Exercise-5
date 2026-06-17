package de.uni_bayreuth.se.demo.controller;

import de.uni_bayreuth.se.demo.model.Coffee;
import de.uni_bayreuth.se.demo.service.CoffeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    private CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public List<Coffee> getAllCoffees() {
        return coffeeService.getAllCoffees();
    }

    @GetMapping("/{name}")
    public Coffee getCoffeeByName(@PathVariable String name) {
        return coffeeService.getCoffeeByName(name);
    }

    @GetMapping("/accessible")
    public List<Coffee> getAccessibleCoffees() {
        return coffeeService.getAccessibleCoffees();
    }

    @PutMapping("/{name}")
    public Coffee updateCoffeeByName(@PathVariable String name,
                                     @RequestBody Coffee updatedCoffee) {
        return coffeeService.updateCoffeeByName(name, updatedCoffee);
    }
}