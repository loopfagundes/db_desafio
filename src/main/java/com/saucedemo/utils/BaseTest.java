package com.saucedemo.utils;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.saucedemo.webdrivers.BrowserEnum;
import com.saucedemo.webdrivers.DriverFactory;
import com.saucedemo.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({ExtentITestListenerClassAdapter.class, Report.class})
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.createInstance(BrowserEnum.CHROME);
        DriverManager.setDriver(driver);
        driver.get(Property.get("url"));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}