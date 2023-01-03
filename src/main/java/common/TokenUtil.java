package common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ziran.addresslist.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class TokenUtil{

    /** 过期30分钟 */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /** jwt密钥 */
    private static final String SECRET = "jwt_secret";
    /**
     * 生成 token
     * @param user
     * @return
     */
    public static String generateToken(Users user) {
        System.out.println("user" + user.getUserId());
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + EXPIRE_TIME;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create()
                .withAudience(user.getUserId().toString())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }


    /**
     *
     * @param token
     * @return userId
     * 获取指定 token 中的 userId
     */
    public static String getUserId(String token) {
        List<String> list= JWT.decode(token).getAudience();
        System.out.println("list" + list.get(0));
        return list.get(0);
    }

    /**
     * 获取 request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }


    /**
     *
     * @param request
     * @return
     * 获取 token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        token = token.replace("Bearer ", "");
        return token;
    }

    /**
     * 校验token
     * @param token
     * @return
     * */
    public static boolean checkSign(String token) {
        try {
            Algorithm algorithm  = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withClaim("username, username)
                    .build();
            verifier.verify(token);
            return true;
        }catch (JWTVerificationException e) {
            throw new RuntimeException("token 无效，请重新获取");
        }
    }
}