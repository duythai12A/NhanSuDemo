package com.example.nhansudemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
/*
* Phan Duy Thai
* 9/7/2022
* */
// tao token va kiem tra token
public class JwtUtil {
    private String secret = "phanduythai";//Ten khoa bi mat

    public String extractUsername(String token) {//trích xuất tên người dùng
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {//trích xuất hết hạn
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {//trích xuất yêu cầu
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {//trích xuất tất cả yêu cầu
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {//kiểm tra hạn token
        return extractExpiration(token).before(new Date());
    }
    public String generateToken(String username){//tao token
        Map<String,Object>claims = new HashMap<>();
        return createToken(claims,username);
    }
    private String createToken(Map<String, Object>claims, String subject){//tao token
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))//thoi het han = thoi gian hien tai + 10h
                .signWith(SignatureAlgorithm.HS256, secret).compact();//kieu ma hoa
    }
    public Boolean validateToken(String token, UserDetails userDetails) {//kiem tra token
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
