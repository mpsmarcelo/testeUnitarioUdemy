package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Locacoes;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;

public class LocacoesServiceTest {

    LocacoesService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception ;

    @Before
    public void setup(){
         service  = new LocacoesService();
    }

    @Test
    public void testeLocacao() throws Exception { /*utilizando Assert.assert*/

        //cenario

        Usuario usuario = new Usuario("Marcelo");
        List<Filme> filme = Arrays.asList(new Filme("Batman", 1, 5.0));


        Locacoes locacoes = service.alugarFilme(usuario, filme);

        error.checkThat(locacoes.getValor(),CoreMatchers.is(equalTo(5.0)));   /* verifique que valor e igual a 5, assertThat sem utlizar linguagem estatica */
        error.checkThat(locacoes.getValor(),is(not(6.0)));
        error.checkThat(isMesmaData(locacoes.getDataLocacao(),new Date()), is(true));
        error.checkThat(isMesmaData(locacoes.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

     @Test
    public void testeLocacao2()  throws Exception {/*utilizando ErrorCollector */

        //cenario
        Usuario usuario = new Usuario("Marcelo");
         List<Filme> filme = Arrays.asList(new Filme("Batman", 1, 5.0));

        Boolean valor;
        Boolean data_locacao;
        Boolean data_retorno;

        //acao
        Locacoes locacao = service.alugarFilme(usuario, filme);


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
        Usuario usuario = new Usuario("Marcelo");
        List<Filme> filme = Arrays.asList(new Filme("Batman", 1, 5.0));

        //acao
            Locacoes locacao = service.alugarFilme(usuario, filme);

            error.checkThat(locacao.getValor(),is(equalTo(5.0)));
            error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()), is(equalTo(true)));
            error.checkThat(isMesmaData(locacao.getDataRetorno(),obterDataComDiferencaDias(1)),is(true));

    }

    @Test
    public void testeLocacao_filmeSemEstoque() throws Exception {

        Usuario usuario = new Usuario("Marcelo");
        List<Filme> filme = Arrays.asList(new Filme("Batman", 0, 5.0));
        service.alugarFilme(usuario,filme);

    }


    @Test
    public void testeLocacao_UsuarioVazio() throws FilmeSemEstoqueException {

        List<Filme> filme = Arrays.asList(new Filme("Batman", 1, 5.0));;

        try{
            service.alugarFilme(null,filme);
            Assert.fail("Usuario deveria estar preenchido");
        }catch (LocadoraException e){
           Assert.assertThat(e.getMessage(),CoreMatchers.is("Usuario vazio!"));
        }

    }

}