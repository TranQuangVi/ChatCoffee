/*
package com.DoAn.ChatCoffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.math.BigInteger;
import java.security.SecureRandom;


@Controller
@RequestMapping("/login")
public class GoogleLoginController {

    private final ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    public GoogleLoginController(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping("/google")
    public ModelAndView loginWithGoogle() {
        ClientRegistration googleRegistration = clientRegistrationRepository.findByRegistrationId("google");
        String authorizationUri = googleRegistration.getProviderDetails().getAuthorizationUri();
        String redirectUri = googleRegistration.getRedirectUri();

        // Thay đổi giá trị của your_state_value và your_nonce_value tại đây
        SecureRandom secureRandom = new SecureRandom();
        String stateValue = new BigInteger(130, secureRandom).toString(32);
        SecureRandom secureRandomnonceValue = new SecureRandom();
        long nonceValue = secureRandomnonceValue.nextLong();

        String modifiedAuthorizationUri = authorizationUri + "?state=" + stateValue + "&nonce=" + nonceValue;

        ModelAndView modelAndView = new ModelAndView("redirect:" + modifiedAuthorizationUri);
        modelAndView.addObject("http://localhost:8080/login/oauth2/code/google", redirectUri);
        return modelAndView;
    }


    @GetMapping("/login/oauth2/code/google")
    public String handleGoogleCallback(Authentication authentication) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = oauthToken.getPrincipal();

        // Truy xuất thông tin người dùng từ OAuth2User
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String picture = oauth2User.getAttribute("picture");
        System.out.println(email + " " + name + " " + picture);


        // Xử lý thông tin người dùng và xác thực

        return "redirect:/"; // Chuyển hướng sau khi xử lý thành công
    }
}
*/
