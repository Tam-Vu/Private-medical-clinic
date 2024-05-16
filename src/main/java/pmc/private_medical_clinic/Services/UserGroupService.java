
package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.AppointmentListPatientDto;
import pmc.private_medical_clinic.Dto.UserGroupDto;
import pmc.private_medical_clinic.Entity.AppointmentListPatient;
import pmc.private_medical_clinic.Entity.UserGroup;

import java.util.List;

@Service
public interface UserGroupService{
    List<UserGroup> getAllUserGroups();
    UserGroup getUserGroupById(Long id);
    UserGroup createUserGroups(UserGroupDto userGroupDto);
    UserGroup updateUserGroups(Long id, UserGroupDto userGroupDto);
}
