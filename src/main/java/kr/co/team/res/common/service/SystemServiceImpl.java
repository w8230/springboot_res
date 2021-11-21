package kr.co.team.res.common.service;

import kr.co.team.res.common.Base;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/*
@Service
public class SystemServiceImpl extends Base implements SystemService {

    @Valid("${spring.profiles.active}")
    private String springProfilesActive = "real";*//*


    //라인 메세지 알림 LineMassageUtils 작성 안함.

    @Async
    @Override
    public void notifySysTickgoAsync(String message) {
        LineMessageUtils.notifyToSystemManagerTickgo(message);
    }


}
*/
