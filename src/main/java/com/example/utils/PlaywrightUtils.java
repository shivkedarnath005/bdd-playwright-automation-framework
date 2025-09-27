package com.example.utils;

import com.microsoft.playwright.Page;

public class PlaywrightUtils {
    private Page page;

    public PlaywrightUtils(Page page) {
        this.page = page;
    }

    public void click(String selector) {
        page.click(selector);
    }

    public void fill(String selector, String value) {
        page.fill(selector, value);
    }

    public boolean isVisible(String selector) {
        return page.isVisible(selector);
    }

    public String getText(String selector) {
        return page.textContent(selector);
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void close() {
        page.close();
    }
}
