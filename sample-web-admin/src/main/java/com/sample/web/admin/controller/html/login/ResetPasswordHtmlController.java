package com.sample.web.admin.controller.html.login;

import static com.sample.web.base.WebConst.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.domain.service.login.LoginService;
import com.sample.web.base.controller.html.AbstractHtmlController;
import com.sample.web.base.util.RequestUtils;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理側パスワードリセット
 */
@Controller
@SessionAttributes(types = { ChangePasswordForm.class })
@Slf4j
public class ResetPasswordHtmlController extends AbstractHtmlController {

    @Autowired
    ChangePasswordFormValidator changePasswordFormValidator;

    @Autowired
    LoginService loginService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ModelAttribute("changePasswordForm")
    public ChangePasswordForm changePasswordForm() {
        return new ChangePasswordForm();
    }

    @InitBinder("changePasswordForm")
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(changePasswordFormValidator);
    }

    @Override
    public String getFunctionName() {
        return "A_RESET_PASSWORD";
    }

    /**
     * パスワードのリセット 初期表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping(RESET_PASSWORD_URL)
    public String resetPassword(@ModelAttribute ResetPasswordForm form, Model model) {
        val token = form.getToken();

        if (StringUtils.isNotEmpty(token)) {
            // 有効性をチェックする
            if (!loginService.isValidPasswordResetToken(token)) {
                return "redirect:/resetPassword?error";
            }
        }

        return "modules/login/resetPassword";
    }

    /**
     * パスワードのリセット メール送信処理
     * 
     * @param form
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping(RESET_PASSWORD_URL)
    public String resetPassword(@Validated @ModelAttribute ResetPasswordForm form, BindingResult result,
            RedirectAttributes attributes) {
        // 入力チェックエラーがある場合は、元の画面にもどる
        if (result.hasErrors()) {
            setFlashAttributeErrors(attributes, result);
            return "redirect:/resetPassword";
        }

        // パスワードリセットのメールを送信する
        val email = form.getEmail();
        val url = StringUtils.join(RequestUtils.getSiteUrl(), CHANGE_PASSWORD_URL);
        loginService.sendResetPasswordMail(email, url);

        attributes.addFlashAttribute(GLOBAL_MESSAGE, getMessage("resetPassword.sent"));
        return "redirect:/resetPassword?sent";
    }

    /**
     * パスワード変更 初期表示
     * 
     * @param form
     * @param model
     * @return
     */
    @GetMapping(CHANGE_PASSWORD_URL)
    public String changePassword(@ModelAttribute ChangePasswordForm form, Model model) {
        return "modules/login/changePassword";
    }

    /**
     * パスワード変更 更新処理
     *
     * @param form
     * @param result
     * @param sessionStatus
     * @param attributes
     * @return
     */
    @PostMapping(CHANGE_PASSWORD_URL)
    public String changePassword(@Validated @ModelAttribute ChangePasswordForm form, BindingResult result,
            SessionStatus sessionStatus, RedirectAttributes attributes) {
        // 入力チェックエラーがある場合は、元の画面にもどる
        if (result.hasErrors()) {
            setFlashAttributeErrors(attributes, result);
            return StringUtils.join("redirect:/changePassword?token=", form.getToken());
        }

        val token = form.getToken();
        val password = passwordEncoder.encode(form.getPassword());

        // 有効性をチェックする
        if (loginService.updatePassword(token, password)) {
            // セッションのChangePasswordFormをクリアする
            sessionStatus.setComplete();

            return "redirect:/changePassword?done";
        } else {
            return "redirect:/changePassword";
        }
    }

    @Override
    public boolean authorityRequired() {
        // 権限チェックを求めない
        return false;
    }
}
