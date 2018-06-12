package com.nanochap.projectservice.util;


import com.nanochap.projectservice.vo.AccessTokenStateEnum;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: token 工具类
 * @Author: junqiang.lu
 * @Date: 2018/4/24
 */
public final class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private TokenUtil(){}

    /**
     * 秘钥
     */
    private static final byte[] SECRET="19cc80d55ca742d09ec642045a3f4353".getBytes();

    /**
     * 初始化head部分的数据为
     * {
     * 		"alg":"HS256",
     * 		"type":"JWT"
     * }
     */
    private static final JWSHeader header=new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null,
            null, null, null, null, null, null, null, null,
            null);

    /**
     * 生成token
     * @param payload，可以存储用户id，token生成时间，token过期时间等自定义字段
     * @return token字符串,若失败则返回null
     */
    public static String createToken(Map<String, Object> payload) {
        String tokenString=null;
        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
        try {
            // 将jwsObject 进行HMAC签名
            jwsObject.sign(new MACSigner(SECRET));
            tokenString=jwsObject.serialize();
        } catch (JOSEException e) {
            logger.error("生成签名失败: ",e);
        }
        return tokenString;
    }

    /**
     * 校验token是否合法，返回Map集合,集合中主要包含:state状态码,data鉴权成功后从token中提取的数据
     * 该方法在过滤器中调用，每次请求API时都校验
     * @param token
     * @return  Map<String, Object>
     */
    public static Map<String, Object> validToken(String token) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier verifier = new MACVerifier(SECRET);

            if (jwsObject.verify(verifier)) {
                JSONObject jsonObj = payload.toJSONObject();
                // token校验成功（此时没有校验是否过期）
                resultMap.put("state", AccessTokenStateEnum.VALID.toString());
                // 若payload包含ext字段，则校验是否过期
                if (jsonObj.containsKey("ext")) {
                    long extTime = Long.valueOf(jsonObj.get("ext").toString());
                    long curTime = new Date().getTime();
                    // 过期了
                    if (curTime > extTime) {
                        resultMap.clear();
                        resultMap.put("state", AccessTokenStateEnum.EXPIRED.toString());
                    }
                }
                resultMap.put("data", jsonObj);
            } else {
                // 校验失败
                resultMap.put("state", AccessTokenStateEnum.INVALID.toString());
            }
        } catch (Exception e) {
            // token格式不合法导致的异常
            logger.info("token格式错误: ",e);
            resultMap.clear();
            resultMap.put("state", AccessTokenStateEnum.INVALID.toString());
        }
        return resultMap;
    }

  

}
