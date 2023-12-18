package np.com.prahladpanthi.seminaronebackend.security.service;

import np.com.prahladpanthi.seminaronebackend.entity.RoleEntity;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeminarOneUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public SeminarOneUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoleEntityList()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<RoleEntity> roleList) {
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
