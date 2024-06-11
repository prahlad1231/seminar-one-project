package np.com.prahladpanthi.seminaronebackend.service.impl;

import lombok.RequiredArgsConstructor;
import np.com.prahladpanthi.seminaronebackend.dto.custom.ChangePasswordDto;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.exception.InsufficientDataException;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.repository.UserRepository;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import np.com.prahladpanthi.seminaronebackend.util.BCryptUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long> implements IUserService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void changePassword(Long userId, ChangePasswordDto changePasswordDto) {
        if (userId == null || changePasswordDto == null || changePasswordDto.getNewPassword().isEmpty() ||
            changePasswordDto.getOldPassword().isEmpty()) {
            throw new InsufficientDataException("Please provide all details!");
        }
        var optionalUserEntity = userRepository.findById(userId);
        UserEntity user = optionalUserEntity.orElseThrow(() -> new NotFoundException("User not found!"));
        if (!BCryptUtils.match(changePasswordDto.getOldPassword(), user.getPassword())) {
            throw new NotFoundException("Old password is incorrect!");
        }
        user.setPassword(BCryptUtils.bCrypt(changePasswordDto.getNewPassword()));
        userRepository.save(user);
    }
}
