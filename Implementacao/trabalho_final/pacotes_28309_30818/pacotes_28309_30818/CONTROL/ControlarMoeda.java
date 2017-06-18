package pacotes_28309_30818.CONTROL;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import pacotes_28309_30818.MODEL.Mascara;
import pacotes_28309_30818.MODEL.Moeda;
import pacotes_28309_30818.VIEW.MontarPainelInicial;

public class ControlarMoeda {

	ArrayList<Point> pontos, proximos;
	Boolean[][] visitados;
	ArrayList<Moeda> arrMoedas;

	FileWriter writer;
	ControlarRotacao rotacao = new ControlarRotacao();
	int nCol, nLin, maX, meX, maY, meY, moedas;
	char[][] imagem;
	ControlarImagem controleImagem;
	MontarPainelInicial pnCen;
	ControlarMascara mascaraControl;
	Graphics g;
	int k;
	int aux = 0;

	public ControlarMoeda(char[][] imagem, int nLin, int nCol, Graphics g, ControlarImagem controleImagem,
			MontarPainelInicial pnCen, ControlarMascara m) throws IOException {

		File file = new File("mascaras.txt");

		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			writer = new FileWriter(file);
		} catch (IOException e) {

		}
		arrMoedas = new ArrayList<>();
		this.mascaraControl = m;
		this.pnCen = pnCen;
		this.controleImagem = controleImagem;
		this.g = g;
		this.nLin = nLin;
		this.nCol = nCol;
		this.imagem = imagem;

		visitados = new Boolean[nCol][nLin];
		pontos = new ArrayList<>();
		for (int i = 0; i < nCol; i++) {
			for (int j = 0; j < nLin; j++) {
				if (imagem[i][j] == 255) {
					pontos.add(new Point(i, j));
				}
				visitados[i][j] = false;
			}
		}

		moedas = 0;

		for (int cont = 0; cont < pontos.size(); cont++) {
			Moeda aux;
			if (!visitados[pontos.get(cont).x][pontos.get(cont).y]) {
				moedas++;
				proximos = new ArrayList<>();
				proximos.add(pontos.get(cont));

				maX = meX = proximos.get(0).x;
				maY = meY = proximos.get(0).y;
				while (proximos.size() > 0) {
					verifica(proximos.get(0));
					proximos.remove(0);
				}

				for (int x = meX; x <= maX; x++) {
					for (int y = meY; y <= maY; y++) {
						if (imagem[x][y] == 255) {
							double r = ((maX - meX) / 2 + (maY - meY) / 2) / 2;
							double dx = Math.pow(x - (meX + (maX - meX) / 2), 2);
							double dy = Math.pow(y - (meY + (maY - meY) / 2), 2);
							if ((Math.sqrt(dx + dy) <= r)) {
								visitados[x][y] = true; //
							}
						}
					}
				}

				// g.setColor(Color.GREEN);
				// g.drawLine(maX, maY, maX, meY);
				// g.drawLine(meX, maY, meX, meY);
				// g.drawLine(meX, meY, maX, meY);
				// g.drawLine(meX, maY, maX, maY);

				aux = new Moeda();

				k = Math.min((maY - meY), (maX - meX));
				char moeda[][] = new char[k][k];
				aux.setMoedas(moeda);

				aux.setnLin(k);
				aux.setnCol(k);

				int cont1 = 0, cont2 = 0;
				for (int i = meX; i < meX + k; i++) {
					for (int j = meY; j < meY + k; j++) {
						moeda[cont1][cont2] = imagem[i][j];
						cont2++;
					}
					cont2 = 0;
					cont1++;

				}
				contaPixel();
				arrMoedas.add(aux);
			}
		}

	}

	public double calcularValor() throws IOException {

		ArrayList<Mascara> mascaras = mascaraControl.getMascaras();

		JFrame frame = new JFrame("Contando Moedas!");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(450, 100);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		Graphics g2 = frame.getGraphics();
		g2.setFont(new Font("Dialog", Font.BOLD, 16));
		g2.drawString("Contando moedas! Por favor aguarde!", 10, 50);

		for (int cont = 0; cont < arrMoedas.size(); cont++) {
			Moeda moeda = arrMoedas.get(cont);
			double prob = 0.0;
			int maxAng = 0;
			char[][] matMoeda = moeda.getMoedas();

			// criaMascara(moeda);

			for (int ang = 0; ang < 360; ang++) {
				int[][] aux = rotacao.main(moeda.getnCol(), moeda.getnLin(), ang,
						controleImagem.char2int(matMoeda, moeda.getnLin(), moeda.getnCol()));
				for (Mascara mascara : mascaras) {
					int nPixels = 0;
					int nMatches = 0;
					char[][] matMascara = mascara.getMascara();

					char matMoeda2[][] = controleImagem.int2char(aux, moeda.getnLin(), moeda.getnCol());

					int nCol = mascara.getnCol();
					int nLin = mascara.getnLin();
					int nColMoeda = moeda.getnCol();
					for (int i = 0; i < nCol; i++) {
						for (int j = 0; j < nLin; j++) {

							if ((matMascara[i][j] == 300) || (i >= nColMoeda) || (j >= nColMoeda)) {
								continue;
							}
							nPixels++;
							if (matMascara[i][j] == matMoeda2[i][j]) {
								nMatches++;
							}

						}
					}
					double k = (double) nMatches / (double) nPixels;
					if (k > prob) {
						prob = k;
						maxAng = ang;
						moeda.setValor(mascara.getValor());
					}
				}
			}

			int[][] aux = rotacao.main(moeda.getnCol(), moeda.getnLin(), maxAng,
					controleImagem.char2int(matMoeda, moeda.getnLin(), moeda.getnCol()));
			matMoeda = controleImagem.int2char(aux, moeda.getnLin(), moeda.getnCol());
		}
		frame.dispose();

		double total = 0.0;

		for (Moeda moeda : arrMoedas) {
			total += moeda.getValor();
			// System.out.println(moeda.getValor());
		}

		if (Math.abs(Math.ceil(total) - total) <= 0.05 && Math.abs(Math.ceil(total) - total) >= 0) {
			total = Math.ceil(total);
		} else if (Math.abs(total - Math.floor(total)) <= 0.05 && Math.abs(total - Math.floor(total)) >= 0) {
			total = Math.floor(total);
		}

		System.out.println("Total encontrado: " + total);

		try {
			writer.flush();
			writer.close();
		} catch (Exception ex) {

		}

		return total;
	}

	public void criaMascara(Moeda m) throws IOException {
		int x = m.getnCol() / 2;
		int y = m.getnLin() / 2;
		int offset = (int) (x * 0.7);

		int tam = m.getnCol();
		char imagem2[][] = new char[tam][tam];

		for (int i = 0; i < tam; i++) {
			for (int j = 0; j < tam; j++) {
				imagem2[i][j] = (char) 300;
			}
		}

		char[][] aux2 = m.getMoedas();
		for (int i = x - offset; i <= (x + 0.7 * offset); i++) {
			for (int j = (int) (y - offset * 1.2); j <= (y + 0.32 * offset); j++) {
				imagem2[i][j] = aux2[i][j];
			}
		}

		FileWriter arq;
		try {
			arq = new FileWriter("28920/pacote2892028989/imagens/mascaras/maskTres.txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.println(m.getnLin() + " " + m.getnCol() + " 3,0\n\n");
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < k; j++) {

					int temp = imagem2[i][j];

					if (temp == 0) {
						gravarArq.print("0 ");
					}
					if (temp == 300) {
						gravarArq.print("300 ");
					}
					if (temp == 255) {
						gravarArq.print("255 ");
					}
				}
			}

			arq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Moeda> getArrMoedas() {
		return arrMoedas;
	}

	private void contaPixel() {
		g.setColor(Color.RED);
		g.drawLine(maX, maY, maX, meY);
		g.drawLine(meX, maY, meX, meY);
		g.drawLine(meX, meY, maX, meY);
		g.drawLine(meX, maY, maX, maY);
	}

	private void verifica(Point p) {
		int xp = p.x;
		int yp = p.y;
		for (int i = -4; i <= 4; i++) {
			for (int j = -4; j <= 4; j++) {
				int x = xp + i;
				int y = yp + j;
				if ((x < 0) || (y < 0) || (x >= nCol) || (y >= nLin))
					continue;

				if ((imagem[x][y] == 255) && (!visitados[x][y])) {
					if (x > maX)
						maX = x;
					if (x < meX)
						meX = x;
					if (y < meY)
						meY = y;
					if (y > maY)
						maY = y;
					visitados[x][y] = true;
					g.setColor(Color.green);
					proximos.add(new Point(x, y));
				}
			}
		}
	}

	public boolean verificaValorInteiro(double valor) {
		return valor == (int) valor;
	}
}