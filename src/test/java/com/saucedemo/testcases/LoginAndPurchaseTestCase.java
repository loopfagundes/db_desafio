package com.saucedemo.testcases;

import com.saucedemo.steps.LoginAndPurchaseStep;
import com.saucedemo.utils.BaseTest;
import com.saucedemo.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginAndPurchaseTestCase extends BaseTest {

    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    @Test(
            description = "CT-001, Efetua login e finalizar a compra.",
            groups = {"web"}
    )
    public void fluxoDeCompraComSucessoTest() throws Exception {
        LoginAndPurchaseStep loginERealizarACompra = new LoginAndPurchaseStep(driver());
        loginERealizarACompra.iniciaFluxoDeCompra();
    }
}