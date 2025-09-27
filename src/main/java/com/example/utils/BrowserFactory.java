package com.example.utils;

import com.microsoft.playwright.*;

public class BrowserFactory {
    private static Playwright playwright;
    private static Browser browser;

    public static Browser getBrowser() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        if (browser == null) {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }
        return browser;
    }

    public static Page getPage() {
        return getBrowser().newPage();
    }

    public static void closeAll() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
