package tech.baranov.carpicker.services;

import org.springframework.stereotype.Service;
import tech.baranov.carpicker.models.Drivetrain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DrivetrainService {

    private final Map<String, Drivetrain> drivetrains;

    public DrivetrainService() {
        this.drivetrains = new HashMap<>();
        for (String make : List.of("AWD", "4WD", "FWD", "RWD")) {
            drivetrains.put(make, Drivetrain.builder().name(make).build());
        }
    }

    public List<Drivetrain> getAll(){
        return drivetrains.values().stream().toList();
    }

    public List<String> getAllNames(){
        return drivetrains.keySet().stream().sorted().toList();
    }

    public Drivetrain getByName(String name) {
        return drivetrains.get(name);
    }

}
