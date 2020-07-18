package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProdutoPage {
    private WebDriver driver;
    private By nomeProduto = By.className("h1");
    private By precoProduto = By.cssSelector(".current-price span:nth-child(1)");
    private By tamanhoProduto = By.id("group_1");
    private By inputCorPreta = By.xpath("//ul[@id='group_2']//input[@value='11']");
    private By quantidadeProduto = By.id("quantity_wanted");
    private By botaoAddToCart = By.className("add-to-cart");

    public ProdutoPage(WebDriver driver){
        this.driver = driver;
    }

    public String obterNomeProduto(){
        return driver.findElement(nomeProduto).getText().toLowerCase();
    }

    public String obterPrecoProduto(){
        return driver.findElement(precoProduto).getText();
    }

    public Select encontrarDropdownSize(){
        return new Select(driver.findElement(tamanhoProduto));
    }

    public List<String> obterOpcoesSelecionada(){
        List<WebElement> elementosSelecionados = encontrarDropdownSize().getAllSelectedOptions();
        List<String> listaOpcoesSelecionadas = new ArrayList();
        for(WebElement elemento : elementosSelecionados){
            listaOpcoesSelecionadas.add(elemento.getText());
        }
        return listaOpcoesSelecionadas;
    }

    public void selecionarOpcaoDropDown(String opcao){
        encontrarDropdownSize().selectByVisibleText(opcao);
    }

    public void selecionarCorPreta(){
        driver.findElement(inputCorPreta).click();
    }

    public void alterarQuantidade(int quantidade){
        driver.findElement(quantidadeProduto).clear();
        driver.findElement(quantidadeProduto).sendKeys(Integer.toString(quantidade));
    }

    public void clicarBotaoAddToCart(){
        driver.findElement(botaoAddToCart).click();
    }
}
