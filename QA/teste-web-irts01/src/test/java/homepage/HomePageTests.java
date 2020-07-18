package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProdutoPage;

import javax.swing.plaf.synth.SynthLookAndFeel;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomePageTests extends BaseTests {
    @Test
    public void testContarProdutos_oitoProdutosDiferentes(){
        carregarPaginaInicial();
        assertThat(homePage.contarProdutos(), is(8));
    }

    @Test
    public void testValidarCarrinhoZerado_ZeroItensNoCarrinho(){
        int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
        //System.out.println(produtosNoCarrinho);
        assertThat(produtosNoCarrinho, is(0));
    }

    ProdutoPage produtoPage;
    @Test
    public void testValidarDetalhesDoProduto_DescricaoEValorIguais(){
        int indice = 0;
        // Le o nome e preco do produto na homepage
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);
        //System.out.println(nomeProduto_HomePage);
        //System.out.println(precoProduto_HomePage);

        // Le o nome e preco na pagina do produto
        produtoPage = homePage.clicarProduto(indice);
        String nomeProduto_produtoPage = produtoPage.obterNomeProduto();
        String precoProduto_produtoPage = produtoPage.obterPrecoProduto();
        //System.out.println(nomeProduto_produtoPage);
        //System.out.println(precoProduto_produtoPage);

        // Verifica se os dois nomes e precos sao iguais
        assertThat(nomeProduto_HomePage, is(nomeProduto_produtoPage));
        assertThat(precoProduto_HomePage, is(precoProduto_produtoPage));
    }

    LoginPage loginPage;
    @Test
    public void testLoginComSucesso_UsuarioLogado(){
        // Clica no botao "Sign In" na homepage e vai para a pagina de login
        loginPage = homePage.clicarBotaoSignIn();
        // Preenche usuario e senha
        loginPage.preencherEmail("teste@teste.com");
        loginPage.preencherPassword("teste");
        // Clica no botao "Sign In" para logar
        loginPage.clicarBotaoSignIn();
        // Valida se o usuario esta logado de fato
        assertThat(homePage.estaLogado("Teste Testador"), is(true));
    }

    @Test
    public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso(){
        // Se o usuario nao estiver logado
        if(!homePage.estaLogado("Teste Testador")){
            testLoginComSucesso_UsuarioLogado();
        }
        // Seleciona produto
        carregarPaginaInicial();
        testValidarDetalhesDoProduto_DescricaoEValorIguais();
        // Seleciona tamanho
        produtoPage.selecionarOpcaoDropDown("M");
        List<String> listaOpcoes = produtoPage.obterOpcoesSelecionada();
        // Seleciona cor
        produtoPage.selecionarCorPreta();
        // Seleciona quantidade
        produtoPage.alterarQuantidade(2);
        // Adicionar ao carrinho
        produtoPage.clicarBotaoAddToCart();
    }
}
