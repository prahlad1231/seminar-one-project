package np.com.prahladpanthi.seminaronebackend.controller;

import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.dto.custom.ChangePasswordDto;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIConstants.USER)
public class UserController extends BaseController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        super(userService);
        this.userService = userService;
    }

    @PostMapping(APIConstants.CHANGE_PASSWORD)
    public ResponseEntity<ResponseDto> changePassword(Authentication authentication,
                                                      @RequestBody ChangePasswordDto changePasswordDto) {
        Long userId = getLoggedInUser(authentication).getId();
        userService.changePassword(userId, changePasswordDto);
        return new ResponseEntity<>(new ResponseDto("Successfully changed password!"), HttpStatus.OK);
    }

}


