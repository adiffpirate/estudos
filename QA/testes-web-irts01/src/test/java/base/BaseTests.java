package base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;

public class BaseTests {
    private static WebDriver driver;
    protected HomePage homePage;

    @BeforeAll
    public static void inicializar(){
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\adm\\AppData\\Local\\Microsoft\\WindowsApps\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @BeforeEach
    public void carregarPaginaInicial(){
        driver.get("https://marcelodebittencourt.com/demoprestashop/");
        homePage = new HomePage(driver);
    }

    @AfterAll
    public static void finalizar(){
        driver.quit();
    }
}
