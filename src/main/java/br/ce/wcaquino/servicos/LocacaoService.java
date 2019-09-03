package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	public static void main(String[] args) {

		//cenario
		LocacaoService locacaoService =  new LocacaoService();
		Usuario usuario = new Usuario("Marcelo");
		Filme filme = new Filme("Batman",1,5.0 );

		//acao
		Locacao locacao = locacaoService.alugarFilme(usuario,filme);

		//verificacao
		System.out.println(locacao.getValor() == 5.0);
		System.out.println(isMesmaData(locacao.getDataLocacao(),new Date()));
		System.out.println(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));
	}
}