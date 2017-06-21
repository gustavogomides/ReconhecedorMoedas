package pacotes_28309_30818.CONTROL;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ControlarImagem {

	private char[][] imagemCinza;
	public char[][] imagemCinzaOriginal;
	private int nLinImagem;
	private int nColImagem;
	private BufferedImage imagemDada;
	public BufferedImage imagemOriginal;

	// *******************************************************************************************
	public ControlarImagem(String nomeArquivoImagemDada, Graphics desenho) {

		imagemDada = lerImagem(nomeArquivoImagemDada);
		imagemOriginal = imagemDada;
		if (imagemDada != null) {
			mostrarImagemBuffer(imagemDada, desenho);
			criarImagemCinza(imagemDada, true);
		}
	}

	// *******************************************************************************************
	// METODO PARA GERAR A IMAGEM RASTER EM NIVEIS DE CINZA A PARTIR DA IMAGEM
	// BUFERIZADA COLORIDA

	public void criarImagemCinza(BufferedImage imagem, boolean setarOriginal) {
		int x, y, r, g, b;
		Raster imagemRasterEntrada;
		char valorSaida;

		// DIMENSOES DA MATRIZ CINZA
		nColImagem = imagem.getWidth(null);
		nLinImagem = imagem.getHeight(null);
		imagemCinza = new char[nColImagem][nLinImagem];
		if (setarOriginal) {
			imagemCinzaOriginal = new char[nColImagem][nLinImagem];
		}

		// DEFININDO IMAGENS INTERMEDIARIAS
		imagemRasterEntrada = imagem.getRaster();

		// CRIANDO IMAGEM CINZA
		for (y = 0; y < nLinImagem; y++) {
			for (x = 0; x < nColImagem; x++) {

				// LENDA DADOS DAS BANDAS DA IMAGEM DADA
				r = imagemRasterEntrada.getSample(x, y, 0); // le dado da banda
															// 0 da imagem de
															// entrada

				try {
					g = imagemRasterEntrada.getSample(x, y, 1); // le dado da
																// banda 1 da
																// imagem de
																// entrada
					b = imagemRasterEntrada.getSample(x, y, 2); // le dado da
																// banda 2 da
																// imagem de
																// entrada
				} catch (ArrayIndexOutOfBoundsException e) {
					g = r;
					b = r;
				}

				// GERANDO NIVEL DE CINZA
				valorSaida = (char) ((r + g + b) / 3);
				imagemCinza[x][y] = valorSaida;
				if (setarOriginal) {
					imagemCinzaOriginal[x][y] = valorSaida;
				}
			}
		}
	}

	// ******************************************************************************************
	public char[][] copiarImagem(char[][] imagemC, int nLinImg, int nColImg) {
		int x, y;
		char[][] imagemR;

		imagemR = new char[nColImg][nLinImg];

		for (x = 0; x < nColImg; x++)
			for (y = 0; y < nLinImg; y++)
				imagemR[x][y] = imagemC[x][y];

		return (imagemR);
	}

	// *******************************************************************************************
	public BufferedImage lerImagem(String nomeArquivo) {
		File arquivoImagem;
		BufferedImage imagem;

		// INICIALIZANDO VARIAVEIS
		imagem = null;
		arquivoImagem = new File(nomeArquivo);

		// LEITURA DA IMAGEM
		try {
			imagem = ImageIO.read(arquivoImagem);
		} catch (IOException e) {
			System.out.println("imagem " + nomeArquivo + " nao existe");
		}

		return (imagem);
	}

	// *******************************************************************************************
	// MOSTRAR IMAGEM BUFERIZADA

	public void mostrarImagemBuffer(BufferedImage imagem, Graphics desenho) {
		int imageWidth, imageHeight, x, sx, y, sy, cell, dx, dy;
		int cells[] = { 0, 1, 2, 3 };

		imageWidth = imagem.getWidth(null);
		imageHeight = imagem.getHeight(null);

		for (x = 0; x < 2; x++) {
			sx = x * imageWidth;
			for (y = 0; y < 2; y++) {
				sy = y * imageHeight;
				cell = cells[x * 2 + y];
				dx = (cell / 2) * imageWidth;
				dy = (cell % 2) * imageHeight;
				desenho.drawImage(imagem, dx, dy, x + imageWidth, dy + imageHeight, sx, sy, sx + imageWidth,
						sy + imageHeight, null);
			}
		}
	}

	// *******************************************************************************************
	// MOSTRAR IMAGEM DO TIPO MATRIZ DE BYTES

	public void mostrarImagemMatriz(char[][] imagemM, int nLin, int nCol, Graphics desenho) {
		BufferedImage imagemB;

		imagemB = transformarMatriz2Buffer(imagemM, nLin, nCol);
		desenho.drawImage(imagemB, 0, 0, nCol, nLin, null);
	}

	// *******************************************************************************************
	public BufferedImage transformarMatriz2Buffer(char[][] imagemM, int nLin, int nCol) {
		int x, y;
		char valorSaida;
		WritableRaster imagemRasterSaida;
		BufferedImage imagemB;

		imagemB = new BufferedImage(nCol, nLin, BufferedImage.TYPE_BYTE_GRAY);
		imagemRasterSaida = imagemB.getRaster();

		for (y = 0; y < nLin; y++) {
			for (x = 0; x < nCol; x++) {
				valorSaida = imagemM[x][y];
				imagemRasterSaida.setSample(x, y, 0, valorSaida);
			}
		}

		return (imagemB);
	}

	// *******************************************************************************************
	public void gravarImagem(String nomeArquivo, char[][] imagemM, int nLin, int nCol, BufferedImage imagem) {
		File arquivoImagem;
		BufferedImage imagemB;

		if (imagemM == null) {
			imagemB = imagem;
		} else {
			imagemB = transformarMatriz2Buffer(imagemM, nLin, nCol);
		}

		// INICIALIZANDO VARIAVEIS
		arquivoImagem = new File(nomeArquivo + ".jpg");

		// LEITURA DA IMAGEM
		try {
			ImageIO.write(imagemB, "jpg", arquivoImagem);
		} catch (IOException e) {
			System.out.println("imagem " + nomeArquivo + " nao existe");
		}
	}

	// *******************************************************************************************
	public char[][] getImagemCinza() {
		return (imagemCinza);
	}

	// *******************************************************************************************
	public int getNLin() {
		return (nLinImagem);
	}

	// *******************************************************************************************
	public int getNCol() {
		return (nColImagem);
	}

	// *******************************************************************************************
	public char[][] int2char(int[][] imagem, int nLin, int nCol) {
		char[][] img = new char[nCol][nLin];

		for (int i = 0; i < nLin; i++) {
			for (int j = 0; j < nCol; j++) {
				img[j][i] = (char) (imagem[j][i]);
			}
		}
		return img;
	}

	// *******************************************************************************************
	public int[][] char2int(char[][] imagem, int nLin, int nCol) {
		int[][] img = new int[nCol][nLin];

		for (int i = 0; i < nLin; i++) {
			for (int j = 0; j < nCol; j++) {
				img[j][i] = (imagem[j][i]);
			}
		}
		return img;
	}
}
