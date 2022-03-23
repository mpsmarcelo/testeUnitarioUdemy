package br.ce.wcaquino.servicos;



public class LocacaoServiceNew {


	public double calcularLocacao(double valor, int dias){
		if(isQuantideDiasLocacaoValido(dias));
		if(isValorLocacaoValido(valor));
		return valor * dias;

	}


	public boolean isQuantideDiasLocacaoValido(Integer dias){
		return dias > 0 ;
	}

	public boolean isValorLocacaoValido(Double valor){
		return valor > 0 ;
	}


}