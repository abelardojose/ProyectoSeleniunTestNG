package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AutomationUtils;
import utilities.BaseTest;
import utilities.Logs;

import java.time.Duration;

public class SauceDemoTests extends BaseTest {

    @Test
    public void invalidUserTest() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        Logs.info("Capturo mensaje de error ");
        final var msjError = driver.findElement(By.xpath("//h3[@data-test='error']"));
        final var msjErrorWeb = "Epic sadface: Sorry, this user has been locked out.";

        Logs.info("Valido mensaje de error");
        Assert.assertTrue(msjError.isDisplayed());
        Assert.assertEquals(msjError.getText(), msjErrorWeb);
    }

    @Test
    public void validUserTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

    }

    @Test
    public void detailsProductoTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

        Logs.info("Seleccionar un producto de la pagina");

        final var listProducts = driver.findElements(By.cssSelector("img[class='inventory_item_img']"));

        listProducts.get(0).click();

        Logs.info("Validamos los datos del producto");
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_name")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_price")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_desc")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_img")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.id("add-to-cart")).isDisplayed());
        softAssert.assertAll();
    }

    @Test
    public void SelectNameTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

        final var Filtro = driver.findElement(By.className("product_sort_container"));
        final var selectFiltro = new Select(Filtro);
        Logs.info("Cambio el Filtro a Name (A to Z)");
        selectFiltro.selectByVisibleText("Name (Z to A)");

        final var listNameProducts = driver.findElements(By.className("inventory_item_name"));

        Logs.info("Validaciones de Name de Imagenes");
        Assert.assertEquals(listNameProducts.get(0).getText(), "Test.allTheThings() T-Shirt (Red)");
        Assert.assertEquals(listNameProducts.get(listNameProducts.size() - 1).getText(), "Sauce Labs Backpack");
    }

    @Test
    public void selectPriceTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

        final var Filtro = driver.findElement(By.className("product_sort_container"));
        final var selectFiltro = new Select(Filtro);
        Logs.info("Cambio el Filtro a Price (low to high)");
        selectFiltro.selectByVisibleText("Price (low to high)");

        final var listNameProducts = driver.findElements(By.className("inventory_item_price"));

        Logs.info("Validaciones de Precio de los productos");
        Assert.assertEquals(listNameProducts.get(0).getText().substring(1), "7.99");
        Assert.assertEquals(listNameProducts.get(listNameProducts.size() - 1).getText().substring(1), "49.99");
    }

    @Test
    public void linkFacebookTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

        final var btnFacebook = driver.findElement(By.xpath("//a[text()='Facebook']"));

        Logs.info("Verificando hipervinculos");
        softAssert.assertTrue(btnFacebook.isDisplayed());
        softAssert.assertTrue(btnFacebook.isEnabled());
        softAssert.assertEquals(btnFacebook.getAttribute("href"), "https://www.facebook.com/saucelabs");
        softAssert.assertAll();
    }

    @Test
    public void linkLinkedinTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

        final var btnLinkedin = driver.findElement(By.xpath("//a[text()='LinkedIn']"));

        Logs.info("Verificando hipervinculos");
        softAssert.assertTrue(btnLinkedin.isDisplayed());
        softAssert.assertTrue(btnLinkedin.isEnabled());
        softAssert.assertEquals(btnLinkedin.getAttribute("href"), "https://www.linkedin.com/company/sauce-labs/");
        softAssert.assertAll();
    }

    @Test
    public void linkAboutTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

        Logs.info("Clickeando la button hamburguesa");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        AutomationUtils.sleep(2000);
        final var btnAbout = driver.findElement(By.id("about_sidebar_link"));

        Logs.info("Verificando hipervinculos");
        softAssert.assertTrue(btnAbout.isDisplayed());
        softAssert.assertTrue(btnAbout.isEnabled());
        softAssert.assertEquals(btnAbout.getAttribute("href"), "https://saucelabs.com/");
        softAssert.assertAll();
    }

    @Test
    public void returnSessionTest() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

        Logs.info("Clickeando la button hamburguesa");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        final var btnLogout = driver.findElement(By.id("logout_sidebar_link"));
        btnLogout.click();

        Logs.info("Validamos pagina de Login");
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void cookie1Test() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

        Logs.info("Validando que exista una sola cookie");
        Assert.assertEquals(driver.manage().getCookies().size(), 1);

        Logs.info("Borrar todas las cookies");
        driver.manage().deleteAllCookies();

        Logs.info("Validando que no haya cookies");
        Assert.assertEquals(driver.manage().getCookies().size(), 0);
    }

    @Test
    public void cookie2Test() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        final var txtLogo = driver.findElement(By.className("app_logo"));

        Logs.info("Valido ingreso a la pagina principal");
        Assert.assertTrue(txtLogo.isDisplayed());
        Assert.assertEquals(txtLogo.getText(), "Swag Labs");

        Logs.info("Obtener la cookie con name 'session-username'");
        final var cookieLogin = driver.manage().getCookieNamed("session-username");

        Logs.info("Validando el value del cookie");
        Assert.assertEquals(cookieLogin.getValue(), "standard_user");
    }

    @Test
    public void relativeLocators1Test() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        Logs.debug("Obteniendo locator");
        final var conocido = By.id("add-to-cart-sauce-labs-bolt-t-shirt");

        final var locatorDesconocido = (By) RelativeLocator
                .with(By.className("inventory_item_price"))
                .below(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));

        final var price = Double.parseDouble(driver.findElement(locatorDesconocido).getText().substring(1));
        Logs.debug("Obteniendo el valor del price: " + price);

        Logs.info("Validando el precio");
        Assert.assertEquals(price, 15.99);
    }

    @Test
    public void relativeLocators2Test() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Ingresando a la url %s", url);
        driver.get(url);

        Logs.info("Ingresando el username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Logs.info("Ingresando el password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Logs.info("Clickeando button Login ");
        driver.findElement(By.id("login-button")).click();

        Logs.debug("Obteniendo el locator desconocido del 'Add to cart'");
        final var locatorDesconocido = (By) RelativeLocator
                .with(By.tagName("button"))
                .below(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));

        var cartButton = driver.findElement(locatorDesconocido);

        Assert.assertEquals(cartButton.getText(), "Add to cart");

        Logs.debug("El texto es: %s", driver.findElement(locatorDesconocido).getText());
        cartButton.click();
        Logs.debug("El texto es: %s", driver.findElement(locatorDesconocido).getText());

        cartButton = driver.findElement(locatorDesconocido);
        Assert.assertEquals(cartButton.getText(), "Remove");


    }
}
