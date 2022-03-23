package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void testeLocacao(){ /*utilizando Assert.assert*/
        Object obj = null;
        Assert.assertTrue(true);
        Assert.assertFalse(false);
        Assert.assertNull(obj);


        Assert.assertEquals(Math.PI, 3.14, 0.01);
        Assert.assertEquals(1.02244,1.02255,0.01);
        Assert.assertNotEquals("Bola","bola");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));


        Usuario usuario = new Usuario("Marcelo");
        Usuario usuario1 = new Usuario("Marcelo");

        Assert.assertEquals(usuario,usuario1);
        Assert.assertNotSame(usuario,usuario1);

    }

    @Test
    public void testeLocacao1()  throws Exception {/*utilizando Assert.assertThat*/

        //cenario
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = new Usuario("Marcelo");
        Filme filme = new Filme("Batman", 1, 5.0);


        Locacao locacao = locacaoService.alugarFilme(usuario, filme);

        Assert.assertThat(locacao.getValor(),CoreMatchers.is(equalTo(5.0)));   /* verifique que valor e igual a 5, assertThat sem utlizar linguagem estatica */
        Assert.assertThat(locacao.getValor(),is(not(6.0)));
        Assert.assertThat(isMesmaData(locacao.getDataLocacao(),new Date()), is(true));
        Assert.assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

     @Test
    public void testeLocacao2()  throws Exception {/*utilizando ErrorCollector */

        //cenario
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = new Usuario("Marcelo");
        Filme filme = new Filme("Batman", 1, 5.0);

        Boolean valor;
        Boolean data_locacao;
        Boolean data_retorno;

        //acao
        Locacao locacao = locacaoService.alugarFilme(usuario, filme);


        //verificacao
        valor = locacao.getValor() == 5.0;
        data_locacao = isMesmaData(locacao.getDataLocacao(), new Date());
        data_retorno = isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1));


        //Assertivas com Assert
        error.checkThat(locacao.getValor(),CoreMatchers.is(equalTo(5.0)));   /* verifique que valor e igual a 5, CoreMatchers.is() foi importado passando a utilizar apenas o is */
        error.checkThat(locacao.getValor(),is(not(6.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
        error.checkThat(valor,  is(equalTo(true)));


        //Assertivas utlizando linguagem estatica
        error.checkThat(valor, is(equalTo(true)));
        error.checkThat(data_locacao, is(equalTo(true)));
        error.checkThat(data_retorno, is(equalTo(true)));

        //Comparação entre String
        error.checkThat("Marcelo", is(equalTo("Marcelo")));
        Usuario usuario1 = new Usuario("Paulo");
        Usuario usuario2 = new Usuario("Paulo");
        error.checkThat(usuario1, not(sameInstance(usuario2)));


    }


    @Test
    public void testeLocacao3()  throws Exception {/*utilizando ErrorCollector */

        //cenario
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = new Usuario("Marcelo");
        Filme filme = new Filme("Batman", 2, 5.0);

        //acao
            Locacao locacao = locacaoService.alugarFilme(usuario, filme);

            error.checkThat(locacao.getValor(),is(equalTo(5.0)));
            error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()), is(equalTo(true)));
            error.checkThat(isMesmaData(locacao.getDataRetorno(),obterDataComDiferencaDias(1)),is(true));

    }

    @Test
    public void testeLocacao_filmeSemEstoque() {
        LocacaoService locacaoService = new LocacaoService();

        Usuario usuario = new Usuario("Marcelo");
        Filme filme = new Filme("Batman", 0, 5.0);

        try{
            locacaoService.alugarFilme(usuario,filme);
            Assert.fail("Deveria ter lançado uma exceção");
        }catch (Exception e){
            Assert.assertThat(e.getMessage(),is("Filme sem estoque!"));

        }

    }

}