package tech.baranov.carpicker.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.baranov.carpicker.services.BodyService;
import tech.baranov.carpicker.services.CarsService;
import tech.baranov.carpicker.services.DrivetrainService;
import tech.baranov.carpicker.services.MakeService;
import tech.baranov.carpicker.sessionAtributes.CarSessionAttribute;

@Controller
@RequiredArgsConstructor
@SessionAttributes("carsOptions")
@Log4j2
public class CarsController {

    private final MakeService makeService;
    private final BodyService bodyService;
    private final DrivetrainService drivetrainService;

    private final CarsService carsService;

    @ModelAttribute("carsOptions")
    public CarSessionAttribute search() {
        return CarSessionAttribute.builder()
                .makes(makeService.getAll())
                .bodies(bodyService.getAll())
                .drivetrains(drivetrainService.getAll())
                .build();
    }

    @RequestMapping(path = "/cars-form", method = {RequestMethod.GET, RequestMethod.POST})
    public String carsForm(Model model, @ModelAttribute("carsOptions") CarSessionAttribute carsSessionAttribute) {
        log.info("Before controller carsSessionAttribute : {}", carsSessionAttribute);

        model.addAttribute("carsOptions", carsSessionAttribute);
        model.addAttribute("cars", carsService.getAll());

        log.info("After controller carsSessionAttribute : {}, isSubmitDisabled {}", carsSessionAttribute, carsSessionAttribute.isSubmitDisabled());

        return "cars";
    }

    @PostMapping(path = "/cars")
    public String carsCreate(Model model, @ModelAttribute("carsOptions") CarSessionAttribute carsSessionAttribute) {
        log.info("Before controller carsSessionAttribute : {}", carsSessionAttribute);

        carsService.addCar(
                carsSessionAttribute.getMake(),
                carsSessionAttribute.getModel(),
                carsSessionAttribute.getBody(),
                carsSessionAttribute.getDrivetrain()
        );

        carsSessionAttribute.setMake(null);
        carsSessionAttribute.setModel(null);
        carsSessionAttribute.setBody(null);
        carsSessionAttribute.setDrivetrain(null);

        model.addAttribute("carsOptions", carsSessionAttribute);
        model.addAttribute("cars", carsService.getAll());

        log.info("After controller carsSessionAttribute : {}, isSubmitDisabled {}", carsSessionAttribute, carsSessionAttribute.isSubmitDisabled());

        return "cars";
    }

}
