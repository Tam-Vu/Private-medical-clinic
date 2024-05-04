package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UserGroupDto;
import pmc.private_medical_clinic.Entity.UserGroup;
import pmc.private_medical_clinic.Repositories.UserGroupRepo;

import java.util.List;
@Service
public class UserGroupServiceIml implements UserGroupService{
    @Autowired
    private UserGroupRepo userGroupRepo;

    @Override
    public List<UserGroup> getAllUserGroups() {
        return userGroupRepo.findAll();
    }

    @Override
    public UserGroup createUserGroups(UserGroupDto userGroupDto){
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName(userGroupDto.getGroupName());
        userGroup.setNote(userGroupDto.getNote());
        userGroupRepo.save(userGroup);
        return userGroup;
    }

    @Override
    public UserGroup updateUserGroups(Long id, UserGroupDto userGroupDto){
        UserGroup userGroup = userGroupRepo.findById(id).get();
        userGroup.setGroupName(userGroupDto.getGroupName());
        userGroup.setNote(userGroupDto.getNote());
        if(userGroup != null)
        {
            userGroupRepo.save(userGroup);
        }
        return userGroup;
    }
}
