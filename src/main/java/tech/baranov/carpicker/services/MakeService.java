package tech.baranov.carpicker.services;

import org.springframework.stereotype.Service;
import tech.baranov.carpicker.models.Make;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MakeService {

    private final Map<String, Make> makes;

    public MakeService() {
        this.makes = new HashMap<>();
        for (String make : List.of("Toyota", "Ford", "Mazda", "VW")) {
            makes.put(make, Make.builder().name(make).build());
        }
    }

    public List<Make> getAll(){
        return makes.values().stream().toList();
    }

    public List<String> getAllNames(){
        return makes.keySet().stream().sorted().toList();
    }

    public Make getByName(String name) {
        return makes.get(name);
    }

}
