package com.springboot.boot.filters;


import com.alibaba.fastjson.JSONObject;
import com.sgcc.isc.service.adapter.utils.AESUtils;
import com.sgcc.isc.ualogin.client.CASClient;
import com.sgcc.isc.ualogin.client.CASTicket;
import com.sgcc.isc.ualogin.client.IscServiceTicketValidator;
import com.sgcc.isc.ualogin.client.util.Base64Util;
import com.sgcc.isc.ualogin.client.util.IscSSOResourceUtil;
import com.sgcc.isc.ualogin.client.vo.IscSSOUserBean;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

@Service
@Slf4j
public class SsoLogin {

    //认证接口调用
    public void isc_sso_Funcs() {
        try {
            String server = "http://isctest.jiafly.cn:22001/isc_sso"; //统一认证服务地址
            String username = "xtadmin", password = "Isc$2017.";
            String service = "http://192.168.1.133:8080/mp/TestCount/testList/";//业务应用访问地址
            //String unit = "sgcc";
            String unit = "";
            System.out.println("调用ISC_SSO服务进行登录认证开始...");
            password = new String(Base64Util.encode(password.getBytes("UTF-8")));
            CASTicket t = CASClient.getTicket(server + "/v1/tickets", username, password, unit, service);
            //password = AESUtils.encrypt(password, AESUtils.keySeed);
            //CASTicket t = CASClient.getTicketByAES(server + "/v1/tickets", username, password, service);
            String tgt = t.getTicketGrantingTicket();
            String st = t.getServiceTicket();
            String userInfo = t.getUserInfo();
            System.out.println(tgt + "\n" + st + "\n" + userInfo + "\n" + service);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //验证登录票
    public void tgtValidate() {
        try {
            String server = "http://isctest.jiafly.cn:22001/isc_sso"; //统一认证服务地址
            String username = "xtadmin", password = "Isc$2017.";
            String service = "http://192.168.1.53:8080";//业务应用访问地址
            String unit = "sgcc";

            password = new String(Base64Util.encode(password.getBytes("UTF-8")));
            CASTicket t = CASClient.getTicket(server + "/v1/tickets", username, password, unit, service);

            String tgt = t.getTicketGrantingTicket();
            String st = t.getServiceTicket();
            String userInfo = t.getUserInfo();
//           // System.out.println(tgt + "\n" + st + "\n" + userInfo + "\n" + service);
//            // List list= JSONObject.parseArray(userInfo);
//            JSONObject json = JSONObject.parseObject(userInfo);
//            System.out.println(tgt + "\n" + st + "\n" + userInfo + "\n" + service);
//            String iscUserId = json.getString("iscUserId");
//            System.out.println(iscUserId);
            IscServiceTicketValidator sv = new IscServiceTicketValidator();
            /*统一认证服务端校验器地址*/
            //   sv.setCasValidateUrl("https://isctest.jiafly.cn:22001/isc_sso/serviceValidate");
            sv.setCasValidateUrl("https://isctest.jiafly.cn:22011/isc_sso/serviceValidate");
            // sv.setCasValidateUrl(server);
            /*业务系统LoginModule访问地址*/
            sv.setService(service);
            sv.setServiceTicket(st);
            sv.setServiceTicket(st);
            // sv.setService("http://192.168.1.53:8080/#/index/homePage");
           //sv.setServiceTicket("ST-60-V49bDKpkK11CjGYDdb21-isctest.jiafly.cn");
            //  sv.setServiceTicket("ST-46-vUd2jEVY2EovzwWBadzV-isctest.jiafly.cn");


            System.out.println(st);
            String user = null;
            try {
                sv.validate();
            } catch (SAXException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // String   mlResponse = sv.getResponse();
            if (sv.isAuthenticationSuccesful()) {
                user = sv.getUser();
            } else {
                String errorCode = sv.getErrorCode();
                String errorMessage = sv.getErrorMessage();
                /* handle the error */
                System.out.println("errorInfo -----------> " + errorCode + "\r\n" + errorMessage);
            }
            System.out.println("userinfo >>>>>>>>>>>> " + user);


            IscSSOUserBean iscSSOUserBean = null;
            try {
                /*获取当前用户登录信息*/
                iscSSOUserBean = IscSSOResourceUtil.transferIscUserBean(user);
                /*当前登录用户ID*/
                String userid = iscSSOUserBean.getIscUserId();
                System.out.println(userid);
                /*当前登录用户账号*/
                String loginName = iscSSOUserBean.getIscUserSourceId();
                System.out.println(loginName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //   sv.validate();
            // CASClient.tgtValidate()
            //   Boolean validateResult = CASClient.tgtValidate(server, tgt, iscUserId);
            //  System.out.println( "validateResult:="+       validateResult);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //验证登录票
    public String rederectUrl() {
        try {
            String server = "http://isctest.jiafly.cn:22001/isc_sso"; //统一认证服务地址
            String username = "xtadmin", password = "Isc$2017.";
            String service = "http://192.168.1.133:8080/mp/TestCount/testList/";//业务应用访问地址
            //String unit = "sgcc";
            String unit = "";
            System.out.println("调用ISC_SSO服务进行登录认证开始...");
            password = new String(Base64Util.encode(password.getBytes("UTF-8")));
            CASTicket t = CASClient.getTicket(server + "/v1/tickets", username, password, unit, service);
            //password = AESUtils.encrypt(password, AESUtils.keySeed);
            //CASTicket t = CASClient.getTicketByAES(server + "/v1/tickets", username, password, service);
            String tgt = t.getTicketGrantingTicket();
            String st = t.getServiceTicket();
            String userInfo = t.getUserInfo();
            System.out.println(tgt + "\n" + st + "\n" + userInfo + "\n" + service);
            // List list= JSONObject.parseArray(userInfo);
            JSONObject json = JSONObject.parseObject(userInfo);
            System.out.println(tgt + "\n" + st + "\n" + userInfo + "\n" + service);
            String iscUserId = json.getString("iscUserId");
            System.out.println(iscUserId);
            Boolean validateResult = CASClient.tgtValidate(server, tgt, "ss");
            System.out.println("validateResult:=++++++" + validateResult);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String redirectUrl = "yahoo.com";
        ModelAndView mav = new ModelAndView(redirectUrl);

        return "redirect:" + mav;
    }


}
