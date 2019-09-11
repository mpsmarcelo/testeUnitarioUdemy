package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class LocacaoServiceTest {


    @Rule
    public ErrorCollector erro = new ErrorCollector();
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testeLocacao()  throws Exception {

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

//        Assert.assertEquals(1,3);
//        assertThat(1,is(equalTo(3)));
//        assertThat("Marcelo",is(equalTo("marcelo")));

        //Assertivas com Assert
        Assert.assertTrue(valor);
        Assert.assertTrue(data_locacao);
        Assert.assertTrue(data_retorno);


        //Assertiva com fluente interface assertThat
        //Assertivas  assertThat sem utlizar linguagem estatica
        erro.checkThat(valor, CoreMatchers.is(CoreMatchers.equalTo(true)));


        //Assertivas utlizando linguagem estatica
        erro.checkThat(valor, is(equalTo(true)));
        erro.checkThat(data_locacao, is(equalTo(true)));
        erro.checkThat(data_retorno, is(equalTo(true)));

        //Comparação entre String
        erro.checkThat("Marcelo", is(equalTo("Marcelo")));
//        erro.checkThat("Marcel", is(equalTo("Marcelo")));
        //Comparação entre objetos, para isso e necessário implementar o metodo equals na classe Usuario.
        Usuario usuario1 = new Usuario("Paulo");
        Usuario usuario2 = new Usuario("Paulo");
//        erro.checkThat(usuario1, new Is(sameInstance(usuario2)));


//    }
//
//    private void comparaUsuario(Usuario usu1, Usuario usu2 ) {
//
////        Assert.assertEquals(usu1,usu2);
//          assertThat(usu1,is(equalTo(usu2)));
//    }
//
//
//    public void comparaString(String a, String b) {
//
//          Assert.assertTrue(a.equalsIgnoreCase(b));
//
//    }
    }


    @Test(expected = Exception.class)
    public void testeLocacao_filmeSemEstoque() throws Exception {
        LocacaoService locacaoService = new LocacaoService();

        Usuario usuario = new Usuario("Marcelo");
        Filme filme = new Filme("Batman", 0, 5.0);

        locacaoService.alugarFilme(usuario,filme);

    }

    @Test(expected = Exception.class)
    public void testeLocacao_filmeSemEstoqueNovo() throws Exception {
        LocacaoService locacaoService = new LocacaoService();

        Usuario usuario = new Usuario("Marcelo");
        Filme filme = new Filme("Batman", 0, 5.0);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");

        locacaoService.alugarFilme(usuario,filme);


    }

}