package pacotes_28309_30818.MODEL;

public class Moeda {

	private char[][] moedas;
	private double valor;
	private int nLin, nCol;

	public char[][] getMoedas() {
		return moedas;
	}

	public double getValor() {
		return valor;
	}

	public int getnLin() {
		return nLin;
	}

	public int getnCol() {
		return nCol;
	}

	public void setMoedas(char[][] moedas) {
		this.moedas = moedas;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setnLin(int nLin) {
		this.nLin = nLin;
	}

	public void setnCol(int nCol) {
		this.nCol = nCol;
	}
}
