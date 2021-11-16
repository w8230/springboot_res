package kr.co.team.res.common.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

public class DualAuthenticationServiceException extends AuthenticationServiceException {
    public DualAuthenticationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DualAuthenticationServiceException(String message) {
        super(message);
    }
}
