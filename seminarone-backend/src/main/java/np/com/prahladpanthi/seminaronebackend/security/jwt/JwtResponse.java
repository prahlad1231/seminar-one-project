package np.com.prahladpanthi.seminaronebackend.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;


@AllArgsConstructor
@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -983743L;

    private final String jwtToken;

    private String role;

    private Long userId;

    private String username;

}
