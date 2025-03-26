package com.saucedemo.webdrivers;

import com.aventstack.extentreports.Status;
import com.saucedemo.utils.Report;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> _driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return _driver.get();
    }

    public static void setDriver(WebDriver driver) {
        _driver.set(driver);
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            Report.log(Status.INFO, "Encerra a sessão.");
        }
    }
}