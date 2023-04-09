package tech.baranov.carpicker.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import tech.baranov.carpicker.models.Car;
import tech.baranov.carpicker.services.*;
import tech.baranov.carpicker.sessionAtributes.PickerPageSessionAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@SessionAttributes("pageOptions")
@Log4j2
public class SearchController {

    private final MakeService makeService;
    private final BodyService bodyService;
    private final DrivetrainService drivetrainService;

    private final CarsService carsService;

    @ModelAttribute("pageOptions")
    public PickerPageSessionAttribute search() {
        return PickerPageSessionAttribute.builder()
                .makes(makeService.getAllNames().stream().collect(Collectors.toMap(key -> key, value -> true)))
                .bodies(bodyService.getAllNames().stream().collect(Collectors.toMap(key -> key, value -> true)))
                .drivetrains(drivetrainService.getAllNames().stream().collect(Collectors.toMap(key -> key, value -> true)))
                .build();
    }

    @RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String postSearch(Model model, @ModelAttribute("pageOptions") PickerPageSessionAttribute pickerPageSessionAttribute) {
        log.info("Before controller pickerPageSessionAttribute : {}", pickerPageSessionAttribute);

        List<Car> cars = carsService.getBy(pickerPageSessionAttribute.getMake(), pickerPageSessionAttribute.getBody(), pickerPageSessionAttribute.getDrivetrain());

        pickerPageSessionAttribute.setMakes(makeService.getAllNames().stream().collect(Collectors.toMap(key -> key, value -> false)));
        pickerPageSessionAttribute.setBodies(bodyService.getAllNames().stream().collect(Collectors.toMap(key -> key, value -> false)));
        pickerPageSessionAttribute.setDrivetrains(drivetrainService.getAllNames().stream().collect(Collectors.toMap(key -> key, value -> false)));

        carsService.markActive(cars, pickerPageSessionAttribute.getMakes(), pickerPageSessionAttribute.getBodies(), pickerPageSessionAttribute.getDrivetrains());

        model.addAttribute("pageOptions", pickerPageSessionAttribute);
        model.addAttribute("cars", cars);

        log.info("After controller pickerPageSessionAttribute : {}", pickerPageSessionAttribute);

        return "search";
    }

}
