package com.example.stepdefinitions;

import com.example.pages.LoginPage;
import com.microsoft.playwright.*;
import com.example.utils.BrowserFactory;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class LoginSteps {
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);
    private static Page page;
    private static LoginPage loginPage;
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/test/resources/config/application.properties"));
            logger.info("Loaded application properties file.");
        } catch (Exception e) {
            logger.error("Failed to load properties file", e);
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    @Given("User launches URL")
    public void user_launches_url() {
        String url = properties.getProperty("app.url", "https://www.automationexercise.com/login");
        launchBrowserAndNavigate(url);
    }

    @Given("User launches URL {string}")
    public void user_launches_url_param(String url) {
        launchBrowserAndNavigate(url);
    }

    private void launchBrowserAndNavigate(String url) {
        logger.info("Launching Playwright and browser using BrowserFactory.");
        page = BrowserFactory.getPage();
        loginPage = new LoginPage(page);
        logger.info("Navigating to URL: {}", url);
        loginPage.navigateTo(url);
    }


    @When("User enters login email and password")
    public void user_enters_login_email_and_password() {
        String email = properties.getProperty("app.username", "amoldeokar@gmail.com");
        String password = properties.getProperty("app.password", "Test@123");
        enterCredentials(email, password);
    }

    @When("User enters login email {string} and password {string}")
    public void user_enters_login_email_and_password_param(String email, String password) {
        enterCredentials(email, password);
    }

    private void enterCredentials(String email, String password) {
        logger.info("Entering email: {} and password.", email);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }


    @When("User clicks on Login")
    public void user_clicks_on_login() {
        logger.info("Clicking on Login button.");
        loginPage.clickLogin();
    }

    @Then("User verifies Automation Exercise logo on Home Page")
    public void user_verifies_logo_on_home_page() {
        verifyLogoAndClose();
    }

    @Then("User verifies Automation Exercise logo on Home Page using outline")
    public void user_verifies_logo_on_home_page_outline() {
        verifyLogoAndClose();
    }

    private void verifyLogoAndClose() {
        logger.info("Verifying Automation Exercise logo on Home Page.");
        boolean logoVisible = loginPage.isLogoVisible();
        logger.info("Logo visible: {}", logoVisible);
        Assert.assertTrue("Logo is not visible", logoVisible);
        BrowserFactory.closeAll();
        logger.info("Browser and Playwright closed.");
    }
}
