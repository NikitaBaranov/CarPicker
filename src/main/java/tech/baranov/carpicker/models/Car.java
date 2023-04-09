package tech.baranov.carpicker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Car {

    private final Make make;
    private final Body body;
    private final Drivetrain drivetrain;

    private final String model;

}
