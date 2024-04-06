package np.com.prahladpanthi.seminaronebackend.controller;

import lombok.extern.slf4j.Slf4j;
import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.security.jwt.JwtRequest;
import np.com.prahladpanthi.seminaronebackend.security.jwt.JwtResponse;
import np.com.prahladpanthi.seminaronebackend.security.jwt.JwtToken;
import np.com.prahladpanthi.seminaronebackend.security.service.UserDetailsServiceImpl;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Optional;

@RestController
@Slf4j
public class AuthenticationController extends BaseController {

    private IUserService userService;

    private JwtToken jwtToken;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AuthenticationController(IUserService userService,
                                    JwtToken jwtToken,
                                    UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.jwtToken = jwtToken;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/api/v1/test")
    public ResponseEntity<ResponseDto> test() {
        return new ResponseEntity<>(new ResponseDto("Test successful!"), HttpStatus.OK);
    }

    @PostMapping("/api/v1/authenticate")
    public ResponseEntity<ResponseDto> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest,
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
        final String token = jwtToken.generateToken(userDetails);

        return new ResponseEntity<>(new ResponseDto("Successfully authenticated!",
                new JwtResponse(token, user.getRolesEntity().getName(), user.getId(), user.getUsername())), HttpStatus.OK);
    }

    private boolean authenticate(UserEntity user, String password) {

        return BCryptUtils.match(password, user.getPassword());
    }
}
