package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Locacoes;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;


public class LocacoesService {

	public Locacoes alugarFilme(Usuario usuario, List<Filme> filmes) throws LocadoraException {

		validarUsuarioNaoPodeSerNulo(usuario);
		validarFilmeNaoPodeSerNulo(filmes);
		validarListaFilmesNaoPodeSerNula(filmes);

		Locacoes locacoes = new Locacoes();
		locacoes.setFilme(filmes);
		locacoes.setUsuario(usuario);
		locacoes.setDataLocacao(new Date());
	    locacoes.setValor(filmes.stream().map(Filme::getPrecoLocacao).reduce(Double::sum).orElse(0.00));


		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacoes.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacoes;
	}

	private void validarListaFilmesNaoPodeSerNula(List<Filme> filmes) {
		filmes.forEach(
				f -> {
					try {
						validarFilmes(f);
					} catch (LocadoraException e) {
						e.printStackTrace();
					}
				}
		);
	}

	private void validarFilmeNaoPodeSerNulo(List<Filme> filmes) throws LocadoraException {
		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio!");
		}
	}

	private void validarUsuarioNaoPodeSerNulo(Usuario usuario) throws LocadoraException {
		if( usuario ==null )
			throw new LocadoraException("Usuario vazio!");
	}

	private void validarFilmes(Filme f) throws LocadoraException {
		if(f == null)
		throw new LocadoraException("Filme vazio!");
	}


	public static void main(String[] args) throws Exception {
		LocacoesService service = new LocacoesService();
		Usuario usuario = new Usuario("Marcelo");
		List<Filme> filme = Collections.singletonList(new Filme("Batman", 2, 2.50));
		Locacoes locacoes = service.alugarFilme(usuario,filme);
		Date dataLocacao =  locacoes.getDataLocacao();


		System.out.println(locacoes.getValor());
		System.out.println(locacoes.getUsuario().getNome());
		System.out.println(dataLocacao.getTime());
		System.out.println(locacoes.getDataRetorno());
		System.out.println("teste");
	}
}