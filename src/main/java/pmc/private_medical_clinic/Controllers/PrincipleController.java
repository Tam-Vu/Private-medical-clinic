package pmc.private_medical_clinic.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pmc.private_medical_clinic.Dto.MedicineDto;
import pmc.private_medical_clinic.Dto.PrincipleDto;
import pmc.private_medical_clinic.Dto.UnitDto;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.Medicine;
import pmc.private_medical_clinic.Entity.Principle;
import pmc.private_medical_clinic.Entity.Unit;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Repositories.MedicineRepo;
import pmc.private_medical_clinic.Repositories.PrincipleRepo;
import pmc.private_medical_clinic.Repositories.UnitRepo;
import pmc.private_medical_clinic.Services.CustomUserDetails;
import pmc.private_medical_clinic.Services.MedicineService;
import pmc.private_medical_clinic.Services.PrincipleService;
import pmc.private_medical_clinic.Services.UserService;
import pmc.private_medical_clinic.failureHandler.IncorrectPasswordException;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/principle")
public class PrincipleController{

    @Autowired
    private UserService userService;

    @Autowired
    private PrincipleRepo principleRepo;

    @Autowired
    private PrincipleService principleService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineRepo medicineRepo;

    @Autowired
    private UnitRepo unitRepo;

    @GetMapping("/setting")
    public String getSetting(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());

        User user = ((CustomUserDetails) userDetails).getUser();
        model.addAttribute("user", user);

        Principle principle = principleRepo.findById(1).get();
        model.addAttribute("principle", principle);

        List<Medicine> medicineList = medicineRepo.findAllNotDelete();
        model.addAttribute("medicineList", medicineList);
        List<Unit> unitList = unitRepo.findAll();
        model.addAttribute("unitList", unitList);
        return "setting";
    }

    @PostMapping("/update-infomation")
    public String updateInfo(@ModelAttribute UserDto userDto, Principal principal) {
        User user = userService.updateInfo(userDto, principal);
        return "redirect:/principle/setting";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute UserDto userDto, Principal principal, HttpSession httpSession) {
        try {
            User user = userService.changePassword(userDto, principal);
            return "redirect:/principle/setting";
        } catch (IncorrectPasswordException e) {
            httpSession.setAttribute("error", e.getMessage());
            return "redirect:/principle/setting";
        }
    }

    @PostMapping("/change-principle")
    public String changePrinciple(@ModelAttribute PrincipleDto principleDto) {
        Principle updatedPrinciple = principleService.updateDetails(principleDto);
        return "redirect:/principle/setting";
    }

    @GetMapping("medicine/detail/{id}")
    @ResponseBody
    public Medicine showMedicineById(@PathVariable("id") Long medicineId) {
        Medicine medicine = medicineService.showMedicineNotDeletedById(medicineId);
        return medicine;
    }

    @PostMapping("medicine/add")
    public String addMedicine(@ModelAttribute MedicineDto medicineDto, @RequestParam("unitId") Integer unitId) {
        Unit unit = unitRepo.getById(unitId);
        Medicine medicine = medicineService.saveMedicine(medicineDto, unit);
        return "redirect:/principle/setting";
    }

    @PostMapping("medicine/delete/{id}")
    public String deletedMedicine(@PathVariable("id") Long medicineId) {
        Medicine medicine = medicineService.deleteMedicine(medicineId);
        return "redirect:/principle/setting";
    }

    @PutMapping("medicine/update/{id}")
    public String updateMedicine(@PathVariable("id") Long medicineId,
                                   @ModelAttribute MedicineDto medicineDto, Model model) {
        Medicine medicine = medicineService.updateMedicine(medicineId, medicineDto);
        return "redirect:/principle/setting";
    }
}
