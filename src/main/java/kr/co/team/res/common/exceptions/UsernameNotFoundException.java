package kr.co.team.res.common.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

public class UsernameNotFoundException extends AuthenticationServiceException {
    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
