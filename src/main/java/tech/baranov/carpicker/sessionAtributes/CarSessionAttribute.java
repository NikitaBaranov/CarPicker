package tech.baranov.carpicker.sessionAtributes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import tech.baranov.carpicker.models.Body;
import tech.baranov.carpicker.models.Drivetrain;
import tech.baranov.carpicker.models.Make;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarSessionAttribute {

    private List<Make> makes;
    private List<Body> bodies;
    private List<Drivetrain> drivetrains;

    private String make;
    private String body;
    private String drivetrain;

    private String model;

    public boolean isSubmitDisabled() {
        return Strings.isBlank(make) ||
                Strings.isBlank(body) ||
                Strings.isBlank(drivetrain) ||
                Strings.isBlank(model);
    }

}
