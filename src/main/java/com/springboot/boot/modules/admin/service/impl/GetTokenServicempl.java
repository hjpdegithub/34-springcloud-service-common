package com.springboot.boot.modules.admin.service.impl;

import com.sgcc.isc.ualogin.client.IscServiceTicketValidator;
import com.sgcc.isc.ualogin.client.util.IscSSOResourceUtil;
import com.sgcc.isc.ualogin.client.vo.IscSSOUserBean;
import com.springboot.boot.modules.admin.service.GetTokenService;
import com.springboot.boot.modules.admin.vo.UserInfoVo;
import com.springboot.boot.utils.ConstantISCProperties;
import com.springboot.boot.utils.TokenCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * @ClassName ClassifyServiceImpl
 * @Description TODO 分类管理业务层
 * @Author jhzhou
 * @Date 2022/3/11 0011 14:17
 * @Version 1.0
 **/
@Service
@Slf4j
public class GetTokenServicempl implements GetTokenService {

    @Autowired
    private ConstantISCProperties constantISCProperties;
    @Autowired
    private TokenCheckUtil tokenCheckUtil;

    @Override
    public UserInfoVo getToken(String ticket) {
        UserInfoVo userInfoVo = new UserInfoVo();
        if (true) {
            String user = null;
            IscServiceTicketValidator sv = new IscServiceTicketValidator();
            /*统一认证服务端校验器地址*/
            sv.setCasValidateUrl(constantISCProperties.getCasValidateUrl());
            String service = constantISCProperties.getService();
            /*业务系统LoginModule访问地址*/
            sv.setService(service);
            sv.setServiceTicket(ticket);
            try {
                sv.validate();
            } catch (SAXException e) {
                e.printStackTrace();
                userInfoVo.setResult(-1);
                return userInfoVo;
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
                userInfoVo.setResult(-1);
                return userInfoVo;
            } catch (Exception e) {
                e.printStackTrace();
                userInfoVo.setResult(-1);
                return userInfoVo;
            }
            if (sv.isAuthenticationSuccesful()) {
                System.out.println("this is ok result");
                user = sv.getUser();
            } else {
                String errorCode = sv.getErrorCode();
                String errorMessage = sv.getErrorMessage();
                userInfoVo.setUrl(constantISCProperties.getLogInUrl() + "service=" + constantISCProperties.getService());
                userInfoVo.setResult(-1);
                userInfoVo.setErrCode(sv.getErrorCode());
                userInfoVo.setErrMessage(sv.getErrorMessage());
                System.out.println("errorInfo -----------> " + errorCode + "\r\n" + errorMessage);
                return userInfoVo;
            }
            IscSSOUserBean iscSSOUserBean = null;
            try {
                iscSSOUserBean = IscSSOResourceUtil.transferIscUserBean(user);
                userInfoVo.setName(iscSSOUserBean.getName());
                userInfoVo.setIp(iscSSOUserBean.getIp());
                userInfoVo.setIscuserid(iscSSOUserBean.getIscUserId());
                userInfoVo.setBaseorgname(iscSSOUserBean.getBaseOrgName());
                userInfoVo.setIsadmin(iscSSOUserBean.getIsAdmin());
                userInfoVo.setBaseorgid(iscSSOUserBean.getBaseOrgId());
                userInfoVo.setIscadcode(iscSSOUserBean.getIscAdCode());
                userInfoVo.setNetscop(iscSSOUserBean.getNetscop());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tokenCheckUtil.getToken(userInfoVo);
        return userInfoVo;
    }


}
