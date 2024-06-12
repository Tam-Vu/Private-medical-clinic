package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Entity.Authorization;
import pmc.private_medical_clinic.Entity.UserGroup;

import java.util.List;
import pmc.private_medical_clinic.Dto.AuthorizationDto;

@Service
public interface AuthorizationService {

    List<Authorization> getAllAuthorizations();

    List<Authorization> findAllAuthorizationsByUserGroup(long userGroupId);

    Authorization updateAuthorizationById(long id);

    Authorization createAuthorization(AuthorizationDto authorizationDto);

    Authorization updateAuthorization(AuthorizationDto authorizationDto);
}
