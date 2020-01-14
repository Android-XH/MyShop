package shop.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import shop.controller.RequestCommon;
import shop.exception.AuthException;
import java.io.IOException;
import java.util.Date;


/**
 * Created by Administrator on 2019/3/17 0017.
 */
public class JWTUtil {

    /**
     * 加密算法 可以抽象到环境变量中配置
     */
    private static final String SECRET = "HMacSHA256";

    /**
     * token过期时间
     */
    public static final int calendarInterval = 10;

    public static <T> String createToken(T object) {
        try {
            JWTCreator.Builder builder = JWT.create();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            System.out.println(jsonString);
            builder.withSubject(jsonString);
            builder.withExpiresAt(TimeUtil.addDay(new Date(),calendarInterval));
            return builder.sign(Algorithm.HMAC256(SECRET));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密，传入一个加密后的token字符串和解密后的类型
    public static <T> T decode(String token, Class<T> classT) throws AuthException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        long exe = decodedJWT.getExpiresAt().getTime();
        long currentTimeMillis = System.currentTimeMillis();
        if(exe>currentTimeMillis){
            String json = decodedJWT.getSubject();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.readValue(json, classT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            throw new AuthException(RequestCommon.AUTH_TIME_OUT);
        }
        return null;
    }

}
