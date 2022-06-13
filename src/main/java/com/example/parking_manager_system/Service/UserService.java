package com.example.parking_manager_system.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.parking_manager_system.Configs.ConfigurationPropertiesConfig;
import com.example.parking_manager_system.Dao.UserDao;
import com.example.parking_manager_system.Model.JWTHelper;
import com.example.parking_manager_system.Pojo.ParkingUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

  /*  @Value("${authKey}")
    String authKey;*/

    @Autowired
    JWTHelper jwtHelper;
    @Autowired
    ConfigurationPropertiesConfig config;
    Set<String> tokens = new HashSet<>();

    /**
     * 检查是否存在这个token以及token的有效性
     * @param token
     * @return
     */
    public boolean checkToken(String token){
        if(token==null){
            return false;
        }
        if(!tokens.contains(token)){
            return false;
        }
        //接下来验证token的有效性
        try{
            jwtHelper.decode(token);
        }catch (Exception e){
           return false;
        }
        return true;
    }
    public ParkingUser getUserByRequest(HttpServletRequest request) {
        try{
            Cookie tokenCookie = WebUtils.getCookie(request, "token");
            if(tokenCookie==null){
                return null;
            }

            String token = tokenCookie.getValue();
            if(!tokens.contains(token)){
                return null;
            }
            DecodedJWT decode = jwtHelper.decode(token);
            Long userId = decode.getClaim("userId").asLong();

            Optional<ParkingUser> optional = userDao.findById(userId);

           return optional.orElse(null);
        }catch (Exception e){
            return null;
        }

    }
    public void addToken(String token){
        tokens.add(token);
    }

    /**
     * 进行登录验证 成功后返回一个新的token
     * @param userName
     * @param password
     * @return
     */
    public String login(String userName, String password){
        ParkingUser user = userDao.getParkingUserByUserNameAndPassWord(userName,toMd5(password));

        if(user==null){
            return null;
        }
        Calendar calendar=  Calendar.getInstance();

        calendar.add(Calendar.MINUTE,300);//设置过期时间
        String token = JWT.create()
                .withClaim("userId", user.getId())//传递的消息 这个是明文的 不应该存放敏感信息如密码等
                .withClaim("userName", user.getUserName())
                .withExpiresAt(calendar.getTime())//设置过期时间
                .sign(Algorithm.HMAC256(config.getAuthKey()));

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
