package com.example.parking_manager_system.Model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.parking_manager_system.Configs.ConfigurationPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTHelper {
    @Autowired
    ConfigurationPropertiesConfig config;

    public DecodedJWT decode(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(config.getAuthKey())).build();//通过算法构建一个Verifier验证者 用来验证这个token
        DecodedJWT verify = jwtVerifier.verify(token);//解码(验证)
        return verify;
    }
}
