package com.example.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    // Locators
    private final String emailInput = "input[data-qa='login-email']";
    private final String passwordInput = "input[data-qa='login-password']";
    private final String loginButton = "button[data-qa='login-button']";
    private final String logoSelector = "img[alt='Website for automation practice']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void enterEmail(String email) {
        page.fill(emailInput, email);
    }

    public void enterPassword(String password) {
        page.fill(passwordInput, password);
    }

    public void clickLogin() {
        page.click(loginButton);
    }

    public boolean isLogoVisible() {
        return page.isVisible(logoSelector);
    }
}
