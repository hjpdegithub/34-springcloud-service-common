package com.springboot.boot.modules.admin.controller;

import com.sgcc.isc.ualogin.client.IscServiceTicketValidator;
import com.sgcc.isc.ualogin.client.util.IscSSOResourceUtil;
import com.sgcc.isc.ualogin.client.vo.IscSSOUserBean;
import com.springboot.boot.modules.admin.entity.MpSecondClassify;
import com.springboot.boot.modules.admin.service.AppClassifyService;
import com.springboot.boot.modules.admin.service.GetTokenService;
import com.springboot.boot.modules.admin.vo.classify.app.ClassifyTypeShowStudyVo;
import com.springboot.boot.modules.admin.vo.classify.app.SearchStudyVo;
import com.springboot.boot.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

/**
 * @ClassName AppClassifyController
 * @Description TODO 前端分页展示
 * @Author jhzhou
 * @Date 2022/3/14 0014 14:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("token")
@Slf4j
@Api(tags = "1.4.1.2", description = "获得token")
@CrossOrigin
public class TokenController {
    @Autowired
    private GetTokenService getTokenService;
    @ApiOperation(value = "获得token", notes = "获得token")
    @GetMapping(value = "/getToken")
    public ApiResult getToken(@RequestParam("ticket") String ticket) {
        return ApiResult.success(getTokenService.getToken(ticket));
    }
}
