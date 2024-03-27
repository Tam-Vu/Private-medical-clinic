package pmc.private_medical_clinic.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipleController {
    @GetMapping("/Principle")
    public String Test() {
        return "hello";
    }
}
