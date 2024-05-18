package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Entity.Authorization;
import pmc.private_medical_clinic.Services.AuthorizationService;

import java.util.List;
import pmc.private_medical_clinic.Dto.AuthorizationDto;

@RestController
@RequestMapping("/api/v1/authorizations")
@CrossOrigin(origins = "*")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @ResponseBody
    @GetMapping("/")
    public List<Authorization> getAllAuthorizations() {
        return authorizationService.getAllAuthorizations();
    }

    @ResponseBody
    @PutMapping(value = "/{id}")
    public ResponseEntity<Authorization> updateAuthorizationById(@PathVariable("id") Long id) {
        Authorization authorization = authorizationService.updateAuthorizationById(id);
        if (authorization != null) {
            return ResponseEntity.ok(authorization);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/userGroup/{userGroupId}")
    public List<Authorization> findAllAuthorizationsByUserGroup(@PathVariable("userGroupId") Long userGroupId) {
        return authorizationService.findAllAuthorizationsByUserGroup(userGroupId);
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Authorization> createAuthorization(@RequestBody AuthorizationDto authorizationDto) {
        Authorization authorization = authorizationService.createAuthorization(authorizationDto);
        if (authorization != null) {
            return ResponseEntity.ok(authorization);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(value = "/")
    public ResponseEntity<Authorization> updateAuthorization(@RequestBody AuthorizationDto authorizationDto) {
        Authorization authorization = authorizationService.updateAuthorization(authorizationDto);
        if (authorization != null) {
            return ResponseEntity.ok(authorization);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
