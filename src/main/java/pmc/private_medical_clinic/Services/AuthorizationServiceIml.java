package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Entity.Authorization;
import pmc.private_medical_clinic.Repositories.AuthorizationRepo;
import java.util.List;

@Service
public class AuthorizationServiceIml implements AuthorizationService{
    @Autowired
    private AuthorizationRepo authorizationRepo;

    @Override
    public List<Authorization> getAllAuthorizations() {
        return authorizationRepo.findAllAuthorizations();
    }

    @Override
    public List<Authorization> findAllAuthorizationsByUserGroup(long userGroupId){
        return (List<Authorization>)authorizationRepo.findAllAuthorizationsByUserGroup(userGroupId);
    }

    @Override
    public Authorization updateAuthorizationById(long id){
        Authorization authorization = authorizationRepo.findById(id).get();
        if(authorization != null){
            authorization.setAccess(!authorization.isAccess());
            authorizationRepo.save(authorization);
        }
        return authorization;
    }

}
