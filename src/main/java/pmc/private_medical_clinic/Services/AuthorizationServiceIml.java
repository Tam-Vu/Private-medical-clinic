package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Entity.Authorization;
import pmc.private_medical_clinic.Repositories.AuthorizationRepo;
import java.util.List;
import pmc.private_medical_clinic.Dto.AuthorizationDto;
import pmc.private_medical_clinic.Entity.Feat;
import pmc.private_medical_clinic.Entity.UserGroup;

@Service
public class AuthorizationServiceIml implements AuthorizationService {

    @Autowired
    private AuthorizationRepo authorizationRepo;

    @Override
    public List<Authorization> getAllAuthorizations() {
        return authorizationRepo.findAllAuthorizations();
    }

    @Override
    public List<Authorization> findAllAuthorizationsByUserGroup(long userGroupId) {
        return (List<Authorization>) authorizationRepo.findAllAuthorizationsByUserGroup(userGroupId);
    }

    @Override
    public Authorization updateAuthorizationById(long id) {
        Authorization authorization = authorizationRepo.findById(id).get();
        if (authorization != null) {
            authorization.setAccess(!authorization.isAccess());
            authorizationRepo.save(authorization);
        }
        return authorization;
    }

    @Override
    public Authorization createAuthorization(AuthorizationDto authorizationDto) {
        Authorization authorization = new Authorization();
        authorization.setAccess(authorizationDto.getIsAccess());
        UserGroup userGroup = new UserGroup();
        userGroup.setId(authorizationDto.getUserGroupId());
        Feat feat = new Feat();
        feat.setId(authorizationDto.getFeatId());
        authorization.setFeatId(feat);
        authorization.setUserGroupId(userGroup);
        return authorizationRepo.save(authorization);
    }

    @Override
    public Authorization updateAuthorization(AuthorizationDto authorizationDto) {
        Authorization authorization = authorizationRepo.findAllAuthorizations(authorizationDto.getFeatId(), authorizationDto.getUserGroupId());
        authorization.setAccess(authorizationDto.getIsAccess());
        return authorizationRepo.save(authorization);
    }

}
