/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pmc.private_medical_clinic.Dto.UserDto;
import pmc.private_medical_clinic.Entity.ResponeInfo;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Services.UserService;

/**
 *
 * @author duyth
 */
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseBody
    @PutMapping(value = "/{id}")
    public ResponeInfo<User> updateUserById(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        ResponeInfo<User> responeInfo = new ResponeInfo<>();
        try {
            User user = userService.updateUserById(id, userDto);
            responeInfo.setData(user);
            responeInfo.setStatusCode(200);
            return responeInfo;
        } catch (Exception e) {
            responeInfo.setStatusCode(500);
            responeInfo.setMessage(e.getMessage());
            return responeInfo;
        }
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/userName/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName) {
        User user = userService.getUserByUsername(userName);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(value = "deactivate/{id}")
    public ResponseEntity<User> deactivateUserById(@PathVariable("id") Long id) {
        User user = userService.deactivateUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//      @ResponseBody
//    @GetMapping("/phoneNumber/{phoneNumber}")
//    public  ResponseEntity<Patient> getPatientByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
//        Patient patient = patientService.getPatientByPhoneNumber(phoneNumber);
//     if (patient != null) {
//            return ResponseEntity.ok(patient);
//        } else {
//            return ResponseEntity.ok(new Patient());
//        }
//    }
//
//
//
//
//    @ResponseBody
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Boolean> deletePatientById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(patientService.deletePatientById(id));
//    }
}
