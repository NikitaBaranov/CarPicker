package tech.baranov.carpicker.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tech.baranov.carpicker.models.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CarsService {

    private final MakeService makeService;
    private final BodyService bodyService;
    private final DrivetrainService drivetrainService;

    private final List<Car> cars = new ArrayList<>();

    public List<Car> getAll() {
        return cars;
    }

    public void addCar(String make, String model, String body, String drivetrain) {
        if (Strings.isBlank(make) || Strings.isBlank(model) || Strings.isBlank(body) || Strings.isBlank(drivetrain)) {
            return;
        }

        cars.add(Car.builder()
                .make(makeService.getByName(make))
                .model(model)
                .body(bodyService.getByName(body))
                .drivetrain(drivetrainService.getByName(drivetrain))
                .build());
    }

    public List<Car> getBy(String make, String body, String drivetrain) {

        Stream<Car> carStream = cars.stream();

        if (Strings.isNotBlank(make)) {
            carStream = carStream.filter(car -> car.getMake().equals(makeService.getByName(make)));
        }

        if (Strings.isNotBlank(body)) {
            carStream = carStream.filter(car -> car.getBody().equals(bodyService.getByName(body)));
        }

        if (Strings.isNotBlank(drivetrain)) {
            carStream = carStream.filter(car -> car.getDrivetrain().equals(drivetrainService.getByName(drivetrain)));
        }

        return carStream.toList();
    }

    public void markActive(List<Car> cars, Map<String, Boolean> makes, Map<String, Boolean> bodies, Map<String, Boolean> drivetrains) {
        for (Car car : cars) {
            makes.put(car.getMake().getName(), true);
            bodies.put(car.getBody().getName(), true);
            drivetrains.put(car.getDrivetrain().getName(), true);
        }
    }

    @PostConstruct
    public void postConstruct() {
        cars.add(Car.builder()
                .make(makeService.getByName("Toyota"))
                .body(bodyService.getByName("SUV"))
                .drivetrain(drivetrainService.getByName("AWD"))
                .model("RAV4")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Toyota"))
                .body(bodyService.getByName("Coupe"))
                .drivetrain(drivetrainService.getByName("RWD"))
                .model("Supra")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Toyota"))
                .body(bodyService.getByName("Sedan"))
                .drivetrain(drivetrainService.getByName("FWD"))
                .model("Camry")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Toyota"))
                .body(bodyService.getByName("Truck"))
                .drivetrain(drivetrainService.getByName("AWD"))
                .model("Tundra")
                .build());

        cars.add(Car.builder()
                .make(makeService.getByName("Ford"))
                .body(bodyService.getByName("Coupe"))
                .drivetrain(drivetrainService.getByName("RWD"))
                .model("Mustang")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Ford"))
                .body(bodyService.getByName("SUV"))
                .drivetrain(drivetrainService.getByName("AWD"))
                .model("Bronco")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Ford"))
                .body(bodyService.getByName("SUV"))
                .drivetrain(drivetrainService.getByName("FWD"))
                .model("EcoSport")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Ford"))
                .body(bodyService.getByName("Truck"))
                .drivetrain(drivetrainService.getByName("AWD"))
                .model("F-150")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Ford"))
                .body(bodyService.getByName("Truck"))
                .drivetrain(drivetrainService.getByName("AWD"))
                .model("Maverick")
                .build());

        cars.add(Car.builder()
                .make(makeService.getByName("Mazda"))
                .body(bodyService.getByName("Sedan"))
                .drivetrain(drivetrainService.getByName("FWD"))
                .model("Mazda3")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Mazda"))
                .body(bodyService.getByName("Coupe"))
                .drivetrain(drivetrainService.getByName("RWD"))
                .model("MX-5")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Mazda"))
                .body(bodyService.getByName("SUV"))
                .drivetrain(drivetrainService.getByName("AWD"))
                .model("CX-5")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Mazda"))
                .body(bodyService.getByName("SUV"))
                .drivetrain(drivetrainService.getByName("FWD"))
                .model("CX-30")
                .build());
        cars.add(Car.builder()
                .make(makeService.getByName("Mazda"))
                .body(bodyService.getByName("SUV"))
                .drivetrain(drivetrainService.getByName("AWD"))
                .model("CX-90")
                .build());

    }
}
