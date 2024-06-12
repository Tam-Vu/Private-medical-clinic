package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.ArgumentDto;
import pmc.private_medical_clinic.Entity.Argument;
import pmc.private_medical_clinic.Services.ArgumentService;

@Controller
@RequestMapping("api/v1/arguments")
@CrossOrigin(origins = "*")

public class ArgumentController {

    @Autowired
    private final ArgumentService argumentService;

    public ArgumentController(ArgumentService argumentService) {
        this.argumentService = argumentService;
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<Argument> getArgument() {
        Argument argument = argumentService.getArgument();
        if (argument != null) {
            return ResponseEntity.ok(argument);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping("update/argument")
    public Argument updateArgument(@RequestBody ArgumentDto argumentDto) {
        return argumentService.updateArgument(argumentDto);
    }
}
