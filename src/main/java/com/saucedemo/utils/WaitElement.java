package com.saucedemo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitElement {
    private final WebDriverWait wait;

    public WaitElement(WebDriver driver, long timeoutInSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public WaitElement(WebDriver driver) {
        this(driver, 15);
    }

    public WebElement visibilityOf(By by) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Elemento não encontrado ou não visível: " + by, e);
        }
    }

    public WebElement toBeClickable(By by) {
        WebElement element = visibilityOf(by);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Elemento não está clicável: " + by, e);
        }
    }
}