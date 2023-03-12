
package com.springboot.boot.utils;

import com.springboot.boot.modules.admin.entity.MpUser;
import com.springboot.boot.modules.admin.entity.MpUserExample;
import com.springboot.boot.modules.admin.mapper.MpUserMapper;
import com.springboot.boot.modules.admin.vo.UserInfoVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author mq
 * @date 2020/3/2
 **/
@Component
public class TokenCheckUtil {
    @Resource
    private  MpUserMapper  mpUserMapper;
    @Autowired
    private ConstantISCProperties constantISCProperties;
    public void parseToken(String token) throws Exception {
        Claims claims = parseJWT(token);
        Object username = claims.get("userId");

    }
    /**
     * 解析JWT字符串获取Claims容器
     *
     * @param jwt JWT字符串
     * @return Claims容器
     * @throws Exception 解析异常
     */
    public Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = getParseKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
    /**
     * 获取解析用密钥
     *
     * @return 解析用密钥对象
     */
    public SecretKey getParseKey() {
        byte[] encodedKey = Base64.decodeBase64("guojiadianwang");
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "");
    }
    /**
     * 获取加密用密钥
     *
     * @return 加密用密钥对象
     */
    public SecretKey getKey() {
        byte[] encodedKey = Base64.decodeBase64("guojiadianwang");
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "");
    }
    /**
     * 设置加密签名算法
     *
     * @return 设置加密签名算法
     */
    public SignatureAlgorithm getSignatureAlgorithm() {
        return SignatureAlgorithm.HS256;
    }
    public UserInfoVo getToken(UserInfoVo userInfoVo) {
        SignatureAlgorithm signatureAlgorithm = getSignatureAlgorithm();
        SecretKey key = getKey();
        //设置启动时间和过期时间
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + Long.valueOf(constantISCProperties.getExpMillis());
        Date now = new Date(nowMillis);
        Date expDate = new Date(expMillis);
        //JWT中存的信息
        Map map = new HashMap();
        map.put("userId", userInfoVo.getIscuserid());
        JwtBuilder builder = Jwts.builder();
        builder.setId(UUID.randomUUID().toString()).
                setSubject("jwt").
                setIssuer(userInfoVo.getIscuserid()).
                setIssuedAt(now).
                signWith(signatureAlgorithm, key).
                setExpiration(expDate).
                addClaims(map);
        //获取koken
        String token = builder.compact();
        userInfoVo.setToken(token);
        long id = SnowFlakeUtils.getFlowIdInstance().nextId();
        MpUser mpUser  = new  MpUser();
        BeanCopy.copy(userInfoVo,mpUser);
        mpUser.setId(id);
        mpUser.setDeleFlag(0);
        MpUserExample  mpUserExample = new  MpUserExample();
        mpUserExample.createCriteria().andIscuseridEqualTo(mpUser.getIscuserid());
        long IscuseridCount =   mpUserMapper.countByExample(mpUserExample);
        if(IscuseridCount==0){
            mpUserMapper.insert(mpUser);
        }
        //判断user表里面有没有数据没有新增有就跳过
        return userInfoVo;
    }
}