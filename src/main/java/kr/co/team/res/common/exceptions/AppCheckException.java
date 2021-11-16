package kr.co.team.res.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppCheckException extends RuntimeException {
    private static final long serialVersionUID = -7322897902204372630L;

    protected Logger log = LoggerFactory.getLogger(getClass());

    public AppCheckException(String message) {super(message);}
}
