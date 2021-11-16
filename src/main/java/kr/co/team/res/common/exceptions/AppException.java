package kr.co.team.res.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppException extends RuntimeException{
    private static final long serialVersionUID = -7322897902204372630L;

    protected Logger log = LoggerFactory.getLogger(getClass());

    public AppException(String message) { super(message); }
}
