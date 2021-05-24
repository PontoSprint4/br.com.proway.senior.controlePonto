package br.com.proway.senior.utils;

public class Validadores {

	/**
	 * Verifica se o numero eh nulo ou se eh igual a zero.
	 * 
	 * @param numero Integer Numero a ser validado.
	 * @return boolean Retorna false se o numero for nulo ou igual a zero.
	 */
	static public boolean ehMenorIgualZeroOuNulo(Integer numero) {
		if (numero == null || numero <= 0)
			return true;
		return false;
	}
	
	/**
	 * Verifica se o objeto eh nulo.
	 * 
	 * @param objeto objeto que sera verificado.
	 * @return boolean
	 */
	static public boolean ehObjetoNulo(Object objeto) {
		if (objeto == null)
			return true;
		return false;
	}

	/**
	 * Verifica se ha caracteres nao alfanumericos.
	 * 
	 * @param verificar String a ser verificada
	 * @return boolean
	 */
	static public boolean apenasCaracteresValidos(String verificar) {
		return !verificar.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+.*");
	}
}
