package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.exceptions.UsuarioException;


public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {


		if(filme.getEstoque() ==0){
			throw new FilmeSemEstoqueException();
		}
		if( usuario ==null ){
			throw new LocadoraException("Usuario vazio!");
		}
		if(filme ==null ){
			throw new LocadoraException("Filme vazio!");
		}
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

	public static void main(String[] args) throws Exception {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Marcelo");
		Filme filme = new Filme("Batman",2,2.50);
		Locacao locacao = service.alugarFilme(usuario,filme);
		Date dataLocacao =  locacao.getDataLocacao();


		System.out.println(locacao.getValor());
		System.out.println(locacao.getUsuario().getNome());
		System.out.println(dataLocacao.getTime());
		System.out.println(locacao.getDataRetorno());
		System.out.println("teste");
	}
}