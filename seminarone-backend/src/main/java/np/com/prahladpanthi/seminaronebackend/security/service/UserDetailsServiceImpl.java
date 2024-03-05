package np.com.prahladpanthi.seminaronebackend.security.service;

import np.com.prahladpanthi.seminaronebackend.entity.RolesEntity;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.repository.UserRepository;
import np.com.prahladpanthi.seminaronebackend.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    private IRolesService rolesService;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, IRolesService rolesService) {
        this.userRepository = userRepository;
        this.rolesService = rolesService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
        List<RolesEntity> rolesEntities =
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getGrantedAuthorities()
    }

    private Set<SimpleGrantedAuthority> getGrantedAuthorities(List<RolesEntity> rolesEntityList, String role) {

    }
}
