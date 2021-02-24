package com.zhanglubin.shoppingmallbackend.controller;

import com.zhanglubin.shoppingmallbackend.domain.AxiosResult;
import com.zhanglubin.shoppingmallbackend.domain.Oauth2Token;
import com.zhanglubin.shoppingmallbackend.domain.ResourcesUser;
import com.zhanglubin.shoppingmallbackend.domain.TokenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhanglubin
 * @date 2021/2/20
 */
@Controller
@RequestMapping("mall")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MallController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("index")
    public String indexPage() {
        return "home/index";
    }

    @RequestMapping("isLogin")
    @ResponseBody
    public AxiosResult isLogin(String code, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //如果session中存储了登录信息，则直接返回已登录
        if (null != session.getAttribute("providerUserName") && null != session.getAttribute("resourcesUserName")) {
            return new AxiosResult("false", (String)session.getAttribute("providerUserName"), "");
        }

        if (code == null || StringUtils.isEmpty(code)) {
            return new AxiosResult("true", "", "noLogin");
        }

        //验证code
        ResponseEntity<Oauth2Token> oauth2TokenResponseEntity = null;
        try {
            oauth2TokenResponseEntity = restTemplate.exchange("http://127.0.0.1:9930/oauth/token?grant_type=authorization_code&code=" + code + "&redirect_uri=http://127.0.0.1:9932/mall/index&scope=all&client_id=yinyinShop&client_secret=123456", HttpMethod.POST, null, Oauth2Token.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return new AxiosResult("true", "", "noLogin");
        }
        String access_token = oauth2TokenResponseEntity.getBody().getAccess_token();
        ResponseEntity<TokenResult> exchange = null;
        try {
            exchange = restTemplate.exchange("http://127.0.0.1:9930/oauth/check_token?token=" + access_token, HttpMethod.POST, null, TokenResult.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return new AxiosResult("true", "", "noLogin");
        }
        if ("false".equals(exchange.getBody().getActive())) {
            return new AxiosResult("true", "", "noLogin");
        }
        //访问资源服务器，获取相关资源
        ResponseEntity<ResourcesUser> exchange1 = null;
        try {
            exchange1 = restTemplate.exchange("http://127.0.0.1:9931/page/getUser?access_token=" + access_token, HttpMethod.POST, null, ResourcesUser.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return new AxiosResult("true", "", "noLogin");
        }
        String resourcesUserName = exchange1.getBody().getUsername();
        String providerUserName = exchange.getBody().getUser_name();
        System.out.println(resourcesUserName);
        System.out.println(providerUserName);
        //走正常登录流程，保存登录信息
        session.setAttribute("providerUserName", providerUserName);
        session.setAttribute("resourcesUserName", resourcesUserName);
        return new AxiosResult("false", providerUserName, "");
    }
}
