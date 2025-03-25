package com.saucedemo.steps;

import com.aventstack.extentreports.Status;
import com.saucedemo.dto.UserDataDTO;
import com.saucedemo.pageobjects.*;
import com.saucedemo.utils.Report;
import org.openqa.selenium.WebDriver;

import static com.saucedemo.widgets.Element.*;

public class LoginAndPurchaseStep {
    private final AuthLoginPage loginUsuario;
    private final ProductsPage products;
    private final CheckoutYourCartPage yourCart;
    private final CheckoutYourInformationPage yourInformation;
    private final CheckoutOverviewPage overview;
    private final FinishPage finish;

    public LoginAndPurchaseStep(WebDriver driver) {
        loginUsuario = new AuthLoginPage(driver);
        products = new ProductsPage(driver);
        yourCart = new CheckoutYourCartPage(driver);
        yourInformation = new CheckoutYourInformationPage(driver);
        overview = new CheckoutOverviewPage(driver);
        finish = new FinishPage(driver);
    }

    public void iniciaFluxoDeCompra() throws Exception {
        autenticarUsuario();
        adicionaItensAoCarrinho();
        verificaProdutosNoCarrinhoECheckout();
        preencheInformacoesDeCheckout();
        verificarEFinalizarCheckout();
        verificarFinalizacaoELogout();
    }

    private void autenticarUsuario() throws Exception {
        Report.logCapture(Status.INFO, "Acessar na tela de login");
        loginUsuario.usernameTextField().sendKeys(UserDataDTO.userData().getUsername());
        loginUsuario.passwordTextField().sendKeys(UserDataDTO.userData().getPassword());
        click(loginUsuario.loginButton());
    }

    private void adicionaItensAoCarrinho() throws Exception {
        Report.logCapture(Status.INFO, "Redirecionado para tela de Products");
        assertEquals(products.tituloProductNoTopoLabel(), "Products", "O titulo exibida na pagina está incorreta");
        click(products.primeiroItemAddToCartButton());
        click(products.segundoItemAddToCartButton());
        assertEquals(products.quantidadesDosProdutosNoIconeDoCarrinhoLabel(), "2", "As quantidades do item no icone do carrinho está incorreta.");
        click(products.iconeDoCarrinhoButton());
    }

    private void verificaProdutosNoCarrinhoECheckout() throws Exception {
        Report.logCapture(Status.INFO, "Redirecionado para tela de Your Cart");
        assertEquals(yourCart.tituloYourCartNoTopoLabel(), "Your Cart", "O titulo exibida na pagina está incorreta");
        assertEquals(yourCart.verificarONomeDoPrimeiroItemLabel(), "Sauce Labs Backpack", "O nome do primeiro item está incorreta");
        assertEquals(yourCart.verificarOValorDoPrimeiroItemLabel(), "29.99", "O valor do primeiro item está incorreta.");
        assertEquals(yourCart.verificarONomeDoSegundoItemLabel(), "Sauce Labs Fleece Jacket", "O nome do segundo item está incorreta.");
        assertEquals(yourCart.verificarOValorDoSegundoItemLabel(), "49.99", "O valor do segundo item está incorreta.");
        click(yourCart.checkoutButton());
    }

    private void preencheInformacoesDeCheckout() throws Exception {
        Report.logCapture(Status.INFO, "Redirecionado para tela de Checkout: Your Information");
        assertEquals(yourInformation.tituloCheckoutYourInformationNoTopoLabel(), "Checkout: Your Information", "O titulo exibida na pagina está incorreta");
        yourInformation.firstNameTextField().sendKeys(UserDataDTO.userData().getFirstName());
        yourInformation.lastNameTextField().sendKeys(UserDataDTO.userData().getLastName());
        yourInformation.postalCodeTextField().sendKeys(UserDataDTO.userData().getPostalCode());
        click(yourInformation.continueCheckoutButton());
    }

    private void verificarEFinalizarCheckout() throws Exception {
        Report.logCapture(Status.INFO, "Redircionado para tela de Checkout: Overview");
        assertEquals(overview.tituloCheckoutOverviewLabel(), "Checkout: Overview", "O titulo exibida na pagina está incorreta");
        verificarPrimeiroItemCheckout();
        verificarSegundoItemCheckout();
        verificarInformacoesCheckout();
        click(overview.finishButton());
    }

    private void verificarPrimeiroItemCheckout() throws Exception {
        assertEquals(overview.quantidadePrimeiroItemLabel(), "1", "A quantidade do primeiro item está incorreta.");
        assertEquals(overview.nomeDoPrimeiroItemCheckoutLabel(), "Sauce Labs Backpack", "O nome do primeiro item está incorreta");
        assertEquals(overview.valorDoPrimeiroItemCheckoutLabel(), "$29.99", "O valor do primeiro item está incorreta.");
    }

    private void verificarSegundoItemCheckout() throws Exception {
        assertEquals(overview.quantidadeSegundoItemLabel(), "1", "A quantidade do segundo item está incorreta.");
        assertEquals(overview.nomeDoSegundoItemCheckoutLabel(), "Sauce Labs Fleece Jacket", "O nome do segundo item está incorreta");
        assertEquals(overview.valorDoSegundoItemCheckoutLabel(), "$49.99", "O valor do segundo item está incorreta.");
    }

    private void verificarInformacoesCheckout() throws Exception {
        assertEquals(overview.obterFormaDePagamentoLabel(), "SauceCard #31337", "A forma de pagamento exibida não é a esperada.");
        assertEquals(overview.obterInformacoesDeEnvioLabel(), "FREE PONY EXPRESS DELIVERY!", "A mensagem de frete não está correta.");
        assertEquals(overview.valorTotalDosItensLabel(), "Item total: $79.98", "O valor total dos itens está incorreto.");
        assertEquals(overview.valorComTaxasLabel(), "Tax: $6.40", "O valor da taxa dos itens está incorreto.");
        assertEquals(overview.valorTotalFinalLabel(), "Total: $86.38", "O valor total final está incorreto.");
    }

    private void verificarFinalizacaoELogout() throws Exception {
        Report.logCapture(Status.INFO, "Redirecionado para tela de Finish");
        assertEquals(finish.tituloFinishNoTopoLabel(), "Finish", "O titulo exibida na pagina está incorreta");
        assertEquals(finish.tituloThankYouForYourOrderLabel(), "THANK YOU FOR YOUR ORDER", "A mensagem de confirmação de pedido está incorreto.");
        click(finish.tresListrasNoTopoButton());
        click(finish.logoutNoSideBarButton());
        Report.logCapture(Status.INFO,"Redirecionado para tela de Login");
    }
}