package np.com.prahladpanthi.seminaronebackend.security.service;

import np.com.prahladpanthi.seminaronebackend.entity.PermissionEntity;
import np.com.prahladpanthi.seminaronebackend.entity.RolePermissionEntity;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.repository.UserRepository;
import np.com.prahladpanthi.seminaronebackend.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    private IRolePermissionService rolePermissionService;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, IRolePermissionService rolePermissionService) {
        this.userRepository = userRepository;
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));

        List<RolePermissionEntity> rolePermissionEntityList = rolePermissionService.findAllByRoleId(user.get().getRolesEntity().getId());
        List<PermissionEntity> permissionEntityList = rolePermissionEntityList.stream().map(RolePermissionEntity::getPermissionEntity).collect(Collectors.toList());
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getGrantedAuthorities(permissionEntityList, user.get().getRolesEntity().getName());

        return new UserDetailsImpl(user.get().getUsername(), user.get().getPassword(), user.get().getActive(), simpleGrantedAuthorities);
    }

    private Set<SimpleGrantedAuthority> getGrantedAuthorities(List<PermissionEntity> permissionEntityList, String role) {
        Set<SimpleGrantedAuthority> permissions = permissionEntityList.stream().map(
                permissionEntity -> new SimpleGrantedAuthority(permissionEntity.getName())
        ).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + role));

        return permissions;
    }
}
