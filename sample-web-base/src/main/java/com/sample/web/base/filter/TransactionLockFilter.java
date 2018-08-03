package com.sample.web.base.filter;

import com.sample.domain.service.system.TransactionLockService;
import com.sample.web.base.WebConst;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sample.web.base.helper.CookieHelper.getCookie;
import static com.sample.web.base.helper.RequestCheckHelper.isUpdateRequest;


@Slf4j
public class TransactionLockFilter extends OncePerRequestFilter {

    private static final String ERROR_URL = "/api/error/";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        val sessionCookie = getCookie(request, WebConst.SESSION_ID).orElse(null);
        val functionCookie = getCookie(request, WebConst.FUNCTION_ID).orElse(null);
        if (sessionCookie != null && isUpdateRequest(request)) {

            val sessionId = sessionCookie.getValue();
            val functionId = functionCookie.getValue();
            val key = sessionId + "_" + functionId;
            log.info("request start = " + key);
            if(TransactionLockService.exists(key)) {
                request.getContextPath();
                response.sendRedirect(request.getContextPath() + ERROR_URL + String.valueOf(HttpStatus.CONFLICT.value()));
                return;
            }
            TransactionLockService.set(key);
        }

        filterChain.doFilter(request, response);
    }
}
