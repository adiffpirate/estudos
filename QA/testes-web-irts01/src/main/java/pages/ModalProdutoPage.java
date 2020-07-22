package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ModalProdutoPage {
    private WebDriver driver;
    private final By mensagemProdutoAdicionado = By.id("myModalLabel");

    public ModalProdutoPage(WebDriver driver){
        this.driver = driver;
    }

    public String obterMensagemProdutoAdicionado(){
        // Como o modal tem um tempo de carregamento, usa-se o FluentWait para tentar
        // executar a acao a cada 1 segundo, com um timeout de 5 segundos
        // (o ignoring eh usado para "tratar" o erro quando nao eh encontrado o elemento)
        FluentWait wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemProdutoAdicionado));
        return driver.findElement(mensagemProdutoAdicionado).getText();
    }
}
