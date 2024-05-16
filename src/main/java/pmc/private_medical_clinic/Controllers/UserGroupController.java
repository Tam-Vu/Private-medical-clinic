package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.UserGroupDto;
import pmc.private_medical_clinic.Entity.UserGroup;
import pmc.private_medical_clinic.Services.UserGroupService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usergroups")
@CrossOrigin(origins = "*")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @ResponseBody
    @GetMapping("/")
    public List<UserGroup> getAllUserGroups() {
        return userGroupService.getAllUserGroups();
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<UserGroup> createUserGroups(@RequestBody UserGroupDto userGroupDto) {
        UserGroup usergroup = userGroupService.createUserGroups(userGroupDto);
        return ResponseEntity.ok(usergroup);
    }

    @ResponseBody
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserGroup> updateUserGroups(@PathVariable("id") Long id, @RequestBody UserGroupDto userGroupDto) {
        UserGroup usergroup = userGroupService.updateUserGroups(id, userGroupDto);
        if (usergroup != null) {
            return ResponseEntity.ok(usergroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserGroup> getUserGroup(@PathVariable("id") Long id) {
        UserGroup usergroup = userGroupService.getUserGroupById(id);
        if (usergroup != null) {
            return ResponseEntity.ok(usergroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
