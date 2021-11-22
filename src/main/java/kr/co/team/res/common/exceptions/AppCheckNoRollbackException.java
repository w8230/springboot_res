package kr.co.team.res.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppCheckNoRollbackException extends Exception {
    private static final long serialVersionUID = -7322897902204372630L;

    protected Logger log = LoggerFactory.getLogger(getClass());

    public AppCheckNoRollbackException(String message) {
        super(message);
    }
}
