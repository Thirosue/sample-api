package com.sample.web.admin.controller.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.common.util.CipherUtils;
import com.sample.domain.Const;
import com.sample.domain.repository.system.AuthRepository;
import com.sample.domain.service.system.AuthService;
import com.sample.web.admin.controller.html.login.LoginForm;
import com.sample.web.base.WebConst;
import com.sample.web.base.controller.api.AbstractRestController;
import com.sample.web.base.controller.api.resource.Resource;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.val;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.UUID;

import static com.sample.domain.Const.*;
import static com.sample.web.base.WebConst.MESSAGE_SUCCESS;

@RestController
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthRestController extends AbstractRestController {

    @Override
    public String getFunctionName() {
        return "API_AUTH";
    }

    @Value("${application.secure_cookie}")
    Boolean secure_cookie;

    @Autowired
    AuthService authService;

    @Autowired
    ObjectMapper objectMapper;

    @ApiOperation(value = "ログイン", notes = "ログイン", httpMethod = "POST", response = Resource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "セッション情報", response = Resource.class)
            ,@ApiResponse(code = 401, message = "認証エラー", response = Resource.class)})
    @PostMapping
    public Resource auth(@Validated @RequestBody LoginResource loginResource, HttpServletResponse response) throws Exception {
        val session = authService.auth(loginResource.email, loginResource.password);
        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(session));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));
        response.addCookie(createCookie(SESSION_ID, session.getCookie()));
        response.addCookie(createCookie(SESSION_INFO, CipherUtils.encryptAES(objectMapper.writeValueAsString(session))));
        return resource;
    }

    @ApiOperation(value = "セッション延長", notes = "セッション延長", httpMethod = "PUT", response = Resource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "担当者情報", response = Resource.class)
            ,@ApiResponse(code = 401, message = "認証エラー", response = Resource.class)})
    @PutMapping
    public Resource put(@NotNull @CookieValue(value=SESSION_ID, required=true) String sid) throws Exception {
        val session = authService.put(sid);
        Resource resource = resourceFactory.create();
        resource.setData(Arrays.asList(session));
        resource.setMessage(getMessage(MESSAGE_SUCCESS));
        return resource;
    }


    @ApiOperation(value = "ログアウト", notes = "ログアウト", httpMethod = "DELETE", response = Resource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "API応答(dataなし)", response = Resource.class)})
    @DeleteMapping
    public Resource delete(@NotNull @CookieValue(value=SESSION_ID, required=true) String sid) {
        authService.delete(sid);
        Resource resource = resourceFactory.create();
        resource.setMessage(getMessage(MESSAGE_SUCCESS));
        return resource;
    }

    private Cookie createCookie(String name, String sid) {
        val cookie = new Cookie(name, sid);
        cookie.setPath("/");
        cookie.setHttpOnly(secure_cookie);
        cookie.setSecure(secure_cookie);
        return cookie;
    }
}
