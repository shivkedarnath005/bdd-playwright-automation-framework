package com.example.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.*;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;

public class Hooks {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before(order = 0)
    public void setupExtentReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("BDD Playwright Test");
        logger.info("Extent Report initialized");
    }

    @Before(order = 1)
    public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        logger.info("Browser launched");
    }

    @AfterStep
    public void captureScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed() && page != null) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshot.png")));
            scenario.attach(screenshot, "image/png", "Failed Step Screenshot");
            test.fail("Step failed. Screenshot attached.");
            logger.error("Step failed. Screenshot captured.");
        }
    }

    @After
    public void tearDown() {
        if (browser != null) {
            browser.close();
            logger.info("Browser closed");
        }
        if (playwright != null) {
            playwright.close();
            logger.info("Playwright closed");
        }
        if (extent != null) {
            extent.flush();
            logger.info("Extent Report flushed");
        }
    }
}
