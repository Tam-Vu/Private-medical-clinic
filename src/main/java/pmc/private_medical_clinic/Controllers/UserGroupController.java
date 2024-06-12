package pmc.private_medical_clinic.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pmc.private_medical_clinic.Dto.UserGroupDto;
import pmc.private_medical_clinic.Entity.UserGroup;
import pmc.private_medical_clinic.Services.UserGroupService;

import java.util.List;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import pmc.private_medical_clinic.Entity.User;
import pmc.private_medical_clinic.Services.UserService;

@RestController
@RequestMapping("/api/v1/usergroups")
@CrossOrigin(origins = "*")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private UserService userService;

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

    @ResponseBody
    @PutMapping("deactivate/{id}")
    public ResponseEntity<UserGroup> deactivateUserGroupById(@PathVariable Long id) {
        UserGroup usergroup = userGroupService.deactivateUserGroupById(id);
        if (usergroup != null) {
            return ResponseEntity.ok(usergroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/username/{username}")
    public ResponseEntity<UserGroup> getUserGroupByUserName(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            UserGroup usergroup = user.getMaNhom();
            return ResponseEntity.ok(usergroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
