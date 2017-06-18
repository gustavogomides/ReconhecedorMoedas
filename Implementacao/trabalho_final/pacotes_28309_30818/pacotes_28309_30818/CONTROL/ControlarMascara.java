package pacotes_28309_30818.CONTROL;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import pacotes_28309_30818.MODEL.Mascara;

public class ControlarMascara {

	public ArrayList<Mascara> mascaras;

	public ArrayList<Mascara> getMascaras() {
		return mascaras;
	}

	public ControlarMascara() {
		mascaras = new ArrayList<>();
		Scanner sc = null;
		ArrayList<String> arquivos = new ArrayList<>();

		arquivos.add("/pacotes_28309_30818/imagens/mascaras/mascaras_1.txt");
		arquivos.add("/pacotes_28309_30818/imagens/mascaras/mascaras_2.txt");
		arquivos.add("/pacotes_28309_30818/imagens/mascaras/mascaras_3.txt");
		arquivos.add("/pacotes_28309_30818/imagens/mascaras/mascaras_4.txt");
		arquivos.add("/pacotes_28309_30818/imagens/mascaras/mascaras_5.txt");

		for (int i = 0; i < arquivos.size(); i++) {
			InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(arquivos.get(i)));

			sc = new Scanner(reader);

			while (sc.hasNextInt()) {
				int nCol = sc.nextInt();
				int nLin = sc.nextInt();

				double valor = sc.nextDouble();
				char[][] aux = new char[nCol][nLin];
				for (int cont = 0; cont < nCol; cont++) {
					for (int j = 0; j < nLin; j++) {
						aux[cont][j] = (char) (sc.nextInt());
					}
				}
				Mascara mascara = new Mascara(aux, valor, nLin, nCol);
				mascaras.add(mascara);

			}

		}

	}

}
