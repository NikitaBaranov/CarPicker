package tech.baranov.carpicker.services;

import org.springframework.stereotype.Service;
import tech.baranov.carpicker.models.Body;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BodyService {

    private final Map<String, Body> bodies;

    public BodyService() {
        this.bodies = new HashMap<>();
        for (String make : List.of("Sedan", "SUV", "Truck", "Coupe")) {
            bodies.put(make, Body.builder().name(make).build());
        }
    }

    public List<Body> getAll(){
        return bodies.values().stream().toList();
    }

    public List<String> getAllNames(){
        return bodies.keySet().stream().sorted().toList();
    }

    public Body getByName(String name) {
        return bodies.get(name);
    }

}
