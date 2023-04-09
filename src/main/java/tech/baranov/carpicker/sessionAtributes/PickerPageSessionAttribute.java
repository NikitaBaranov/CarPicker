package tech.baranov.carpicker.sessionAtributes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickerPageSessionAttribute {

    private Map<String, Boolean> makes;
    private Map<String, Boolean> bodies;
    private Map<String, Boolean> drivetrains;

    private String make;
    private String body;
    private String drivetrain;

}
