package pmc.private_medical_clinic.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pmc.private_medical_clinic.Dto.MedicineDto;
import pmc.private_medical_clinic.Dto.PrincipleDto;
import pmc.private_medical_clinic.Dto.UnitDto;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.*;
import pmc.private_medical_clinic.Repositories.MedicineRepo;
import pmc.private_medical_clinic.Repositories.PrincipleRepo;
import pmc.private_medical_clinic.Repositories.UnitRepo;
import pmc.private_medical_clinic.Services.*;
import pmc.private_medical_clinic.failureHandler.IncorrectPasswordException;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/principle")
@CrossOrigin(origins = "*")
public class PrincipleController{

    @Autowired
    private UserService userService;

    @Autowired
    private PrincipleRepo principleRepo;

    @Autowired
    private PrincipleService principleService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineRepo medicineRepo;

    @Autowired
    private UnitRepo unitRepo;

    @GetMapping("/setting")
    @ResponseBody
    public ResponeInfo<SettingResponseInfo> getSetting(Model model, Principal principal) {
        ResponeInfo<SettingResponseInfo> responeInfo = new ResponeInfo<>();
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(principal.getName());
            User user = ((CustomUserDetails) userDetails).getUser();
            Principle principle = principleRepo.findById(1).get();
            List<Medicine> medicineList = medicineRepo.findAllNotDelete();
            List<Unit> unitList = unitRepo.findAll();
            SettingResponseInfo settingResponse = new SettingResponseInfo(user, principle, medicineList, unitList);
            responeInfo.setData(settingResponse);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("Setting information retrieved successfully");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PostMapping("/update-infomation")
    @ResponseBody
    public ResponeInfo<User> updateInfo(@RequestBody UserDto userDto, Principal principal) {
        ResponeInfo<User> responeInfo = new ResponeInfo<>();
        try {
            User user = userService.updateInfo(userDto, principal);
            responeInfo.setData(user);
            responeInfo.setMessage("update user information successfully");
            responeInfo.setStatusCode(200);
        }
        catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PostMapping("/change-password")
    @ResponseBody
    public ResponeInfo<User> changePassword(@RequestBody UserDto userDto, Principal principal) {
        ResponeInfo<User> responeInfo = new ResponeInfo<>();
        try {
            User user = userService.changePassword(userDto, principal);
            responeInfo.setData(user);
            responeInfo.setMessage("update user password successfully");
            responeInfo.setStatusCode(200);
        } catch (IncorrectPasswordException e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PostMapping("/change-principle")
    @ResponseBody
    public ResponeInfo<Principle> changePrinciple(@RequestBody PrincipleDto principleDto) {
        ResponeInfo<Principle> responeInfo = new ResponeInfo<>();
        try {
            Principle updatedPrinciple = principleService.updateDetails(principleDto);
            responeInfo.setData(updatedPrinciple);
            responeInfo.setMessage("update principle setting successfully");
            responeInfo.setStatusCode(200);
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @GetMapping("medicine/detail/{id}")
    @ResponseBody
    public ResponeInfo<Medicine> showMedicineById(@PathVariable("id") Long medicineId) {
        ResponeInfo<Medicine> responeInfo = new ResponeInfo<>();
        try {
            Medicine medicine = medicineService.showMedicineNotDeletedById(medicineId);
            responeInfo.setData(medicine);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("view medicine successfully");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PostMapping("medicine/add")
    @ResponseBody
    public ResponeInfo<Medicine> addMedicine(@ModelAttribute MedicineDto medicineDto,
                                             @RequestParam("file") MultipartFile file) {
        ResponeInfo<Medicine> responeInfo = new ResponeInfo<>();
        try {
            Unit unit = unitRepo.findById(medicineDto.getMaDonVi()).get();
            Medicine medicine = medicineService.saveMedicine(medicineDto, unit, file);
            responeInfo.setData(medicine);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("create medicine successfully");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PutMapping("medicine/delete/{id}")
    @ResponseBody
    public ResponeInfo<Medicine> deletedMedicine(@PathVariable("id") Long medicineId) {
        ResponeInfo<Medicine> responeInfo = new ResponeInfo<>();
        try {
            Medicine medicine = medicineService.deleteMedicine(medicineId);
            responeInfo.setData(medicine);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("delete medicine successfully");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }

    @PutMapping("medicine/update/{id}")
    @ResponseBody
    public ResponeInfo<Medicine> updateMedicine(@PathVariable("id") Long medicineId,
                                                @RequestBody MedicineDto medicineDto,
                                                @RequestParam("file") MultipartFile file) {
        ResponeInfo<Medicine> responeInfo = new ResponeInfo<>();
        try {
            Medicine medicine = medicineService.updateMedicine(medicineId, medicineDto, file);
            responeInfo.setData(medicine);
            responeInfo.setStatusCode(200);
            responeInfo.setMessage("delete medicine successfully");
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
        }
        return responeInfo;
    }
}
