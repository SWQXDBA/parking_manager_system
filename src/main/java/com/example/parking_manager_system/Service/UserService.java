package com.example.parking_manager_system.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.parking_manager_system.Dao.UserDao;
import com.example.parking_manager_system.Pojo.ParkingUser;
import javassist.bytecode.ByteArray;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    Set<String> tokens = new HashSet<>();
    public boolean containsToken(String token){
        if(token==null){
            return false;
        }
        return tokens.contains(token);
    }
    public void addToken(String token){
        tokens.add(token);
    }
    @Value("${authKey}")
    String authKey;
    public String login(String userName, String passWord){
        ParkingUser user = userDao.getParkingUserByUserNameAndPassWord(userName,toMd5(passWord));

        if(user==null){
            return null;
        }
        Calendar calendar=  Calendar.getInstance();

        calendar.add(Calendar.MINUTE,30);//设置过期时间
        String token = JWT.create()
                .withClaim("userId", user.getId())//传递的消息 这个是明文的 不应该存放敏感信息如密码等
                .withClaim("userName", user.getUserName())
                .withExpiresAt(calendar.getTime())//设置过期时间
                .sign(Algorithm.HMAC256(authKey));

        addToken(token);
        return  token;
    }
    public boolean register(String userName,String passWord){
        if(userDao.getParkingUserByUserName(userName)!=null){
            return false;
        }
        ParkingUser user = new ParkingUser();
        user.setUserName(userName);
        user.setPassWord(toMd5(passWord));
        userDao.save(user);
        return true;
    }
    private static String toMd5(String str) {
        return DigestUtils.md5Hex(str);
    }

}
