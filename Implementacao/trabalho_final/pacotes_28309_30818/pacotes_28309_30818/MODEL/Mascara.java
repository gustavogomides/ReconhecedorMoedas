package pacotes_28309_30818.MODEL;

public class Mascara {

	public Mascara(char[][] mascara, double valor, int nLin, int nCol) {
		super();
		this.mascara = mascara;
		this.valor = valor;
		this.nLin = nLin;
		this.nCol = nCol;
	}

	char[][] mascara;
	double valor;
	int nLin, nCol;

	public int getnLin() {
		return nLin;
	}

	public int getnCol() {
		return nCol;
	}

	public void setnLin(int nLin) {
		this.nLin = nLin;
	}

	public void setnCol(int nCol) {
		this.nCol = nCol;
	}

	public Mascara(char[][] mascara, double valor) {
		this.mascara = mascara;
		this.valor = valor;
	}

	public char[][] getMascara() {
		return mascara;
	}

	public double getValor() {
		return valor;
	}

	public void setMascara(char[][] mascara) {
		this.mascara = mascara;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
