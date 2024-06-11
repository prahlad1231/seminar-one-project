package np.com.prahladpanthi.seminaronebackend.controller;

import lombok.RequiredArgsConstructor;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.security.service.UserDetailsImpl;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping(APIConstants.BASE_API)
@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3000/"})
public class BaseController {

    private final IUserService userService;

    public UserEntity getLoggedInUser(Authentication authentication) {
        var principal = (UserDetailsImpl) authentication.getPrincipal();
        UserEntity user = userService.findByUsername(principal.getUsername()).orElseThrow();
        return user;
    }

}
