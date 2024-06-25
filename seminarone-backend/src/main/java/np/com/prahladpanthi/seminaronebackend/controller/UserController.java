package np.com.prahladpanthi.seminaronebackend.controller;

import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.dto.custom.ChangePasswordDto;
import np.com.prahladpanthi.seminaronebackend.mapper.UserMapper;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIConstants.USER)
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
public class UserController extends BaseController {

    private final IUserService userService;

    private final UserMapper userMapper;

    public UserController(IUserService userService, UserMapper userMapper) {
        super(userService);
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(APIConstants.FIND_ALL)
    public ResponseEntity<ResponseDto> getAllUsers() {
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!",
                userMapper.mapToDto(userService.findAll())), HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(APIConstants.CHANGE_PASSWORD)
    public ResponseEntity<ResponseDto> changePassword(Authentication authentication,
                                                      @RequestBody ChangePasswordDto changePasswordDto) {
        Long userId = getLoggedInUser(authentication).getId();
        userService.changePassword(userId, changePasswordDto);
        return new ResponseEntity<>(new ResponseDto("Successfully changed password!"), HttpStatus.OK);
    }

}


