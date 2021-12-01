package kr.co.team.res.controller.web.api;
import NiceID.Check.CPClient;
import kr.co.team.res.common.Base;
import kr.co.team.res.domain.vo.user.MobileAuthLogVO;
import kr.co.team.res.service.web.api.MobileAuthLogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * NiceApiController [Nice 본인인증 서비스컨트롤러]
 * @author : jerry
 * @version : 1.0.0
 * 작성일 : 2021/08/12
**/
@Controller
public class NiceApiController extends Base {
    private final String ApiNiceCall = "/api/nice/call";
    private final String ApiNiceSuccess = "/api/nice/success";
    private final String ApiNiceFail = "/api/nice/fail";
    private final MobileAuthLogService mobileAuthLogService;

    @GetMapping(value = ApiNiceCall)
    public String niceCall(Model model,
                           @Value("http://localhost:14327") String domain,
                           @Value("${nice.api.code}") String sSiteCode,
                           @Value("${nice.api.password}") String sSitePassword,
                           HttpSession session,
                           HttpServletRequest request) {
        CPClient niceCheck = new NiceID.Check.CPClient();
        String sRequestNumber = "REQ0000000001";
        sRequestNumber = niceCheck.getRequestNO(sSiteCode);
        session.setAttribute("REQ_SEQ", sRequestNumber);
        String sAuthType = "";
        String popgubun = "N";
        String customize = "";
        String sGender = "";
        domain = request.getRequestURL().toString();
        domain = domain.replace("/api/nice/call", "");
        String sReturnUrl = domain + "/api/nice/success";
        String sErrorUrl = domain + "/api/nice/fail";
        String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber + "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode + "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType + "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl + "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl + "11:POPUP_GUBUN" + popgubun.getBytes().length + ":" + popgubun + "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize + "6:GENDER" + sGender.getBytes().length + ":" + sGender;
        String sMessage = "";
        String sEncData = "";
        int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
        if (iReturn == 0) {
            sEncData = niceCheck.getCipherData();
        } else if (iReturn == -1) {
            sMessage = "암호화 시스템 에러입니다.";
        } else if (iReturn == -2) {
            sMessage = "암호화 처리오류입니다.";
        } else if (iReturn == -3) {
            sMessage = "암호화 데이터 오류입니다.";
        } else if (iReturn == -9) {
            sMessage = "입력 데이터 오류입니다.";
        } else {
            sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
        }

        model.addAttribute("sEncData", sEncData);
        model.addAttribute("sMessage", sMessage);
        return "/pages/api/niceApiCall";
    }

    @RequestMapping(value = ApiNiceSuccess)
    public String niceSuccess(Model model, HttpServletRequest request, @Value("${nice.api.code}") String sSiteCode, @Value("${nice.api.password}") String sSitePassword, HttpSession session) {
        CPClient niceCheck = new CPClient();
        String sEncodeData = this.requestReplace(request.getParameter("EncodeData"), "encodeData");
        String sCipherTime = "";
        String sRequestNumber = "";
        String sResponseNumber = "";
        String sAuthType = "";
        String sName = "";
        String sDupInfo = "";
        String sConnInfo = "";
        String sBirthDate = "";
        String sGender = "";
        String sNationalInfo = "";
        String sMobileNo = "";
        String sMobileCo = "";
        String sMessage = null;
        String sPlainData = "";
        MobileAuthLogVO form = new MobileAuthLogVO();
        int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);
        if (iReturn == 0) {
            sPlainData = niceCheck.getPlainData();
            sCipherTime = niceCheck.getCipherDateTime();
            HashMap mapresult = niceCheck.fnParse(sPlainData);
            sRequestNumber = (String)mapresult.get("REQ_SEQ");
            sResponseNumber = (String)mapresult.get("RES_SEQ");
            sAuthType = (String)mapresult.get("AUTH_TYPE");
            sName = (String)mapresult.get("NAME");
            sBirthDate = (String)mapresult.get("BIRTHDATE");
            sGender = (String)mapresult.get("GENDER");
            sNationalInfo = (String)mapresult.get("NATIONALINFO");
            sDupInfo = (String)mapresult.get("DI");
            sConnInfo = (String)mapresult.get("CI");
            sMobileNo = (String)mapresult.get("MOBILE_NO");
            sMobileCo = (String)mapresult.get("MOBILE_CO");
            String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
            if (!sRequestNumber.equals(session_sRequestNumber)) {
                sMessage = "세션값 불일치 오류입니다.";
                sResponseNumber = "";
                sAuthType = "";
            }
        } else if (iReturn == -1) {
            sMessage = "복호화 시스템 오류입니다.";
        } else if (iReturn == -4) {
            sMessage = "복호화 처리 오류입니다.";
        } else if (iReturn == -5) {
            sMessage = "복호화 해쉬 오류입니다.";
        } else if (iReturn == -6) {
            sMessage = "복호화 데이터 오류입니다.";
        } else if (iReturn == -9) {
            sMessage = "입력 데이터 오류입니다.";
        } else if (iReturn == -12) {
            sMessage = "사이트 패스워드 오류입니다.";
        } else {
            sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
        }

        form.setDecrHr(sCipherTime);
        form.setDmnNo(sRequestNumber);
        form.setRspNo(sResponseNumber);
        form.setAttcMns(sAuthType);
        form.setNm(sName);
        form.setDupSbscrbCfmVal(sDupInfo);
        form.setCnecInfoCfmVal(sConnInfo);
        form.setBrthdy(sBirthDate);
        form.setSexdstn(sGender);
        form.setIseFrerInfo(sNationalInfo);
        form.setMbtlnum(sMobileNo);
        form.setTelecom(sMobileCo);
        form.setFailrCode((String)null);
        form.setMssage(sMessage);
        form.setEncrData(sEncodeData);
        this.mobileAuthLogService.insert(form);
        model.addAttribute("resultValue", 2);
        model.addAttribute("sCipherTime", sCipherTime);
        model.addAttribute("sRequestNumber", sRequestNumber);
        model.addAttribute("sResponseNumber", sResponseNumber);
        model.addAttribute("sAuthType", sAuthType);
        model.addAttribute("sName", sName);
        model.addAttribute("sDupInfo", sDupInfo);
        model.addAttribute("sConnInfo", sConnInfo);
        model.addAttribute("sBirthDate", sBirthDate);
        model.addAttribute("sGender", sGender);
        model.addAttribute("sNationalInfo", sNationalInfo);
        model.addAttribute("sMobileNo", sMobileNo);
        model.addAttribute("sMobileCo", sMobileCo);
        model.addAttribute("sMessage", sMessage);
        model.addAttribute("sPlainData", sPlainData);
        return "/pages/api/niceSuccess";
    }

    @RequestMapping(value = ApiNiceFail)
    public String niceFail(Model model, HttpServletRequest request, @Value("${nice.api.code}") String sSiteCode, @Value("${nice.api.password}") String sSitePassword, HttpSession session) {
        CPClient niceCheck = new CPClient();
        String sEncodeData = this.requestReplace(request.getParameter("EncodeData"), "encodeData");
        String sCipherTime = "";
        String sRequestNumber = "";
        String sErrorCode = "";
        String sAuthType = "";
        String sMessage = null;
        String sPlainData = "";
        MobileAuthLogVO form = new MobileAuthLogVO();
        int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);
        if (iReturn == 0) {
            sPlainData = niceCheck.getPlainData();
            sCipherTime = niceCheck.getCipherDateTime();
            HashMap mapresult = niceCheck.fnParse(sPlainData);
            sRequestNumber = (String)mapresult.get("REQ_SEQ");
            sErrorCode = (String)mapresult.get("ERR_CODE");
            sAuthType = (String)mapresult.get("AUTH_TYPE");
        } else if (iReturn == -1) {
            sMessage = "복호화 시스템 에러입니다.";
        } else if (iReturn == -4) {
            sMessage = "복호화 처리오류입니다.";
        } else if (iReturn == -5) {
            sMessage = "복호화 해쉬 오류입니다.";
        } else if (iReturn == -6) {
            sMessage = "복호화 데이터 오류입니다.";
        } else if (iReturn == -9) {
            sMessage = "입력 데이터 오류입니다.";
        } else if (iReturn == -12) {
            sMessage = "사이트 패스워드 오류입니다.";
        } else {
            sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
        }

        form.setDecrHr(sCipherTime);
        form.setDmnNo(sRequestNumber);
        form.setAttcMns(sAuthType);
        form.setFailrCode(sErrorCode);
        form.setMssage(sMessage);
        form.setEncrData(sEncodeData);
        this.mobileAuthLogService.insert(form);
        model.addAttribute("sCipherTime", sCipherTime);
        model.addAttribute("sRequestNumber", sRequestNumber);
        model.addAttribute("sErrorCode", sErrorCode);
        model.addAttribute("sAuthType", sAuthType);
        model.addAttribute("sMessage", sMessage);
        model.addAttribute("sPlainData", sPlainData);
        return "/pages/api/niceFail";
    }

    private String requestReplace(String paramValue, String gubun) {
        String result = "";
        if (paramValue != null) {
            paramValue = paramValue.replaceAll("%2B", "+");
            paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            paramValue = paramValue.replaceAll("\\*", "");
            paramValue = paramValue.replaceAll("\\?", "");
            paramValue = paramValue.replaceAll("\\[", "");
            paramValue = paramValue.replaceAll("\\{", "");
            paramValue = paramValue.replaceAll("\\(", "");
            paramValue = paramValue.replaceAll("\\)", "");
            paramValue = paramValue.replaceAll("\\^", "");
            paramValue = paramValue.replaceAll("\\$", "");
            paramValue = paramValue.replaceAll("'", "");
            paramValue = paramValue.replaceAll("@", "");
            paramValue = paramValue.replaceAll("%", "");
            paramValue = paramValue.replaceAll(";", "");
            paramValue = paramValue.replaceAll(":", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll("#", "");
            paramValue = paramValue.replaceAll("--", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll(",", "");
            if (gubun != "encodeData") {
                paramValue = paramValue.replaceAll("\\+", "");
                paramValue = paramValue.replaceAll("/", "");
                paramValue = paramValue.replaceAll("=", "");
            }

            result = paramValue;
        }

        return result;
    }

    private String replaceData(String data) {
        return data == null ? null : data.replaceAll("%2B", "+");
    }

    public NiceApiController(final MobileAuthLogService mobileAuthLogService) {
        this.mobileAuthLogService = mobileAuthLogService;
    }
}
