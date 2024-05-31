package np.com.prahladpanthi.seminaronebackend.controller;

import lombok.RequiredArgsConstructor;
import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.security.service.UserDetailsImpl;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(APIConstants.BASE_API)
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class BaseController {

    private final IUserService userService;


    public UserEntity getLoggedInUser(Authentication authentication) {
        var principal = (UserDetailsImpl) authentication.getPrincipal();
        UserEntity user = userService.findByUsername(principal.getUsername()).orElseThrow();
        return user;
    }

}
