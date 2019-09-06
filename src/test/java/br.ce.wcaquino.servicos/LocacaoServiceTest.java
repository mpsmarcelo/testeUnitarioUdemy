package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;


import java.util.Date;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LocacaoServiceTest {
    @Test

    public void teste() {

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
        Assert.assertTrue(valor);
        Assert.assertTrue(data_locacao);
        Assert.assertTrue(data_retorno);

        //Assertiva com fluente interface assertThat
        assertThat(valor, is(equalTo(true)));
        assertThat(data_locacao,is(equalTo(true)));
        assertThat(data_retorno,is(equalTo(true)));

        //Comparação entre String
        comparaString("marcelo","Marcelo");

        //Comparação entre objetos, para isso e necessário implementar o metodo equals na classe Usuario.
        Usuario usuario1 = new Usuario("Paulo");
        Usuario usuario2 = new Usuario("Paulo");
        comparaUsuario(usuario1, usuario2);


    }

    private void comparaUsuario(Usuario usu1, Usuario usu2 ) {

//        Assert.assertEquals(usu1,usu2);
          assertThat(usu1,is(equalTo(usu2)));
    }


    public void comparaString(String a, String b) {

          Assert.assertTrue(a.equalsIgnoreCase(b));

    }

}