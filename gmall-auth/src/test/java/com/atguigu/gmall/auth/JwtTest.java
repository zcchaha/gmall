package com.atguigu.gmall.auth;

import com.atguigu.gmall.common.utils.JwtUtils;
import com.atguigu.gmall.common.utils.RsaUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    // 别忘了创建D:\\project\rsa目录
	private static final String pubKeyPath = "E:\\ideaWorkspase\\12-191125\\zcc\\myOwn\\java\\rsa\\rsa.pub";
    private static final String priKeyPath = "E:\\ideaWorkspase\\12-191125\\zcc\\myOwn\\java\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "esfsacd234");
    }

    @BeforeEach
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "11");
        map.put("username", "liuyan");
        // 生成token
        String token = JwtUtils.generateToken(map, privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6IjExIiwidXNlcm5hbWUiOiJsaXV5YW4iLCJleHAiOjE1OTE1MTI3MjN9.HY7u9VjGj6E72ALrCad7LeeDe7ps4Gc75OrcO84o3uObypGvqSB6JueRKHfAvnrN2xpk6oYoruq0MFZNuxZEahLwlBo5uQH3eV4bEM_kwf8wim_v7B7yFWOpU22xum1isPDxzB3CWRN1wJsm90lj2eBRTSGq6b8or_ArC1Izp9Iw8Kl5MqJVDM3efBgiRJpEUWlt1yIr4xZ4dhBk5jlnerV-MEq5R6j3ncJ_5SJHDLStq1bAbJkPX4Q0qcQmu9bAICNiihEKtpt3rHzFvVcY-LSIGMpaPBLTVrpSRjsLqI3uMAP9rkH4n1ReQ8IAJXe8KB-nOeiZGW1eySiF3nUrzw";

        // 解析token
        Map<String, Object> map = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + map.get("id"));
        System.out.println("userName: " + map.get("username"));
    }
}