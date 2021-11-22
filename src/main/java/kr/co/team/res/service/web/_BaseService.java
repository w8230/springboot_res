package kr.co.team.res.service.web;

import kr.co.team.res.common.Base;
import kr.co.team.res.common.exceptions.AppCheckException;
import kr.co.team.res.common.exceptions.AppCheckNoRollbackException;
import org.springframework.transaction.annotation.Transactional;

// rollbackFor = AppCheckException.class 설정을 해도 RuntimeException 상속이 아니면 안됨.
//@Transactional(readOnly = true, rollbackFor = { RuntimeException.class, Error.class, AppCheckException.class } )
@Transactional(readOnly=true )
public abstract class _BaseService extends Base {

    /**
     * 반드시 체크를 해야하는 오류를 전달함.
     * @param errorMessage
     * @throws AppCheckException
     */
    public void throwAppCheckException(String errorMessage) throws AppCheckException {
        throw new AppCheckException(errorMessage);
    }

    /**
     * 반드시 체크를 해야하는 오류, 롤백안됨.
     * @param errorMessage
     * @throws AppCheckException
     */
    public void throwAppCheckNoRollbackException(String errorMessage) throws AppCheckNoRollbackException {
        throw new AppCheckNoRollbackException(errorMessage);
    }
}