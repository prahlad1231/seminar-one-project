package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.dto.custom.ChangePasswordDto;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;

import java.util.Optional;

public interface IUserService extends IBaseService<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    void changePassword(Long userId, ChangePasswordDto changePasswordDto);
}
