package kr.co.team.res.service.web;

import kr.co.team.res.common.Base;
import kr.co.team.res.common.exceptions.AppCheckException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class BaseService extends Base {

    public void throwAppCheckException(String errorMessage) throws AppCheckException {

    }
}
