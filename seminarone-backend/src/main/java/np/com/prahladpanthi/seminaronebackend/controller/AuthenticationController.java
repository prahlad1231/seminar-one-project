package np.com.prahladpanthi.seminaronebackend.controller;

import lombok.extern.slf4j.Slf4j;
import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.security.jwt.JwtRequest;
import np.com.prahladpanthi.seminaronebackend.security.jwt.JwtResponse;
import np.com.prahladpanthi.seminaronebackend.security.jwt.JwtTokenIssuer;
import np.com.prahladpanthi.seminaronebackend.security.service.UserDetailsImpl;
import np.com.prahladpanthi.seminaronebackend.security.service.UserDetailsServiceImpl;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Optional;

@RestController
@Slf4j
public class AuthenticationController extends BaseController {

    private IUserService userService;

    private JwtTokenIssuer jwtTokenIssuer;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AuthenticationController (IUserService userService,
                                    JwtTokenIssuer jwtTokenIssuer,
                                    UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.jwtTokenIssuer = jwtTokenIssuer;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseDto> test() {
        return new ResponseEntity<>(new ResponseDto("Test successful!"), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> authenticate(@RequestBody JwtRequest authenticationRequest,
                                                                 @RequestParam(defaultValue = "web", value = "source") String source) throws AuthenticationException {
        Optional<UserEntity> optionalUser;
        UserEntity user = null;

        log.info("Authenticating...........");

        if (authenticationRequest.getUsername() != null) {
            optionalUser = userService.findByUsername(authenticationRequest.getUsername());
            user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
            if (!user.getActive()) throw new NotFoundException("User not found!");
        }

        if (source.equalsIgnoreCase("mobile")) {
            // if the authentication is requested from mobile
        }

        if (!authenticate(user, authenticationRequest.getPassword())) {
            throw new AuthenticationException("Incorrect username/password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenIssuer.generateToken(userDetails);

        return new ResponseEntity<>(new ResponseDto("Successfully authenticated!",
                new JwtResponse(token, user.getRolesEntity().getName(), user.getId(), user.getUsername())), HttpStatus.OK);
    }

    private boolean authenticate(UserEntity user, String password) {

        return BCryptUtils.match(password, user.getPassword());
    }

    @GetMapping("/usertest")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDto> userTest() {
        return new ResponseEntity<>(new ResponseDto("Successfully accessed by user!"), HttpStatus.OK);
    }

    @GetMapping("/admintest")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> adminTest() {
        return new ResponseEntity<>(new ResponseDto("Successfully accessed by admin!"), HttpStatus.OK);
    }

    @GetMapping("/secured")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ResponseDto> anyUserTest(Authentication authentication) {
        var principal = (UserDetailsImpl) authentication.getPrincipal();
        UserEntity user = userService.findByUsername(principal.getUsername()).orElseThrow();
        return new ResponseEntity<>(new ResponseDto("Successfully accessed by user: " + user.getUsername()), HttpStatus.OK);
    }
}
