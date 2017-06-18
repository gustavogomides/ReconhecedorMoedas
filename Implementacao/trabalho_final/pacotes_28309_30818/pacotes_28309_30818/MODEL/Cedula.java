package pacotes_28309_30818.MODEL;

import java.awt.image.BufferedImage;

public class Cedula {

	private int valor;
	private int quantidade;
	private BufferedImage imagem;

	public Cedula(int valor, int quantidade) {
		super();
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

}
