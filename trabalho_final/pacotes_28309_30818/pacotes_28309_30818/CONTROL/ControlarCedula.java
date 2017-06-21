package pacotes_28309_30818.CONTROL;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import pacotes_28309_30818.MODEL.Cedula;
import pacotes_28309_30818.VIEW.ViewCedulas;

public class ControlarCedula {

	public ControlarCedula(int valor, ControlarImagem controleImagem) {
		ArrayList<Cedula> quantidadeCedulas = quantidadeCedulas(valor);
		ArrayList<Cedula> quantidadeCedulasNova = quantidadeCeludasNova(quantidadeCedulas, valor);

		for (int i = 0; i < quantidadeCedulas.size(); i++) {
			if (quantidadeCedulas.get(i).getQuantidade() != 0) {
				String path = "/pacotes_28309_30818/imagens/cedulas/cedula_" + quantidadeCedulas.get(i).getValor()
						+ ".png";

				URL resource = getClass().getResource(path);

				try {
					// System.out.println(resource.toURI());
					File file = new File(resource.toURI());
					quantidadeCedulas.get(i).setImagem(ImageIO.read(file));
				} catch (IOException | URISyntaxException e) {
					System.out.println("imagem nao existe");
				}

			}
		}

		boolean opcoes = false;
		for (int i = 0; i < quantidadeCedulas.size(); i++) {
			Cedula c = quantidadeCedulas.get(i);
			if (c.getQuantidade() != quantidadeCedulasNova.get(i).getQuantidade()) {
				opcoes = true;
				break;
			}
		}
		if (opcoes) {
			for (int i = 0; i < quantidadeCedulasNova.size(); i++) {
				if (quantidadeCedulasNova.get(i).getQuantidade() != 0) {
					String path = "/pacotes_28309_30818/imagens/cedulas/cedula_"
							+ quantidadeCedulasNova.get(i).getValor() + ".png";

					URL resource = getClass().getResource(path);

					try {
						// System.out.println(resource.toURI());
						File file = new File(resource.toURI());
						quantidadeCedulasNova.get(i).setImagem(ImageIO.read(file));
					} catch (IOException | URISyntaxException e) {
						System.out.println("imagem nao existe");
					}

				}
			}

			new ViewCedulas().gerarDialogOpcoes(valor, quantidadeCedulas, quantidadeCedulasNova, controleImagem);
		} else {
			new ViewCedulas().gerarDialog(valor, quantidadeCedulas, controleImagem);
		}
	}

	private ArrayList<Cedula> quantidadeCedulas(int valor) {
		int qtde100 = 0;
		int qtde50 = 0;
		int qtde20 = 0;
		int qtde10 = 0;
		int qtde5 = 0;
		int qtde2 = 0;

		ArrayList<Cedula> quantidade = new ArrayList<>();

		while (valor >= 100) {
			valor -= 100;
			++qtde100;
		}
		while (valor >= 50) {
			valor -= 50;
			++qtde50;
		}
		while (valor >= 20) {
			valor -= 20;
			++qtde20;
		}
		while (valor >= 10) {
			valor -= 10;
			++qtde10;
		}
		while (valor >= 5) {
			valor -= 5;
			++qtde5;
		}
		while (valor >= 2) {
			valor -= 2;
			++qtde2;
		}

		quantidade.add(new Cedula(100, qtde100));
		quantidade.add(new Cedula(50, qtde50));
		quantidade.add(new Cedula(20, qtde20));
		quantidade.add(new Cedula(10, qtde10));
		quantidade.add(new Cedula(5, qtde5));
		quantidade.add(new Cedula(2, qtde2));

		// System.out.println("R$100: " + qtde100);
		// System.out.println("R$50: " + qtde50);
		// System.out.println("R$20: " + qtde20);
		// System.out.println("R$10: " + qtde10);
		// System.out.println("R$5: " + qtde5);
		// System.out.println("R$2: " + qtde2);

		return quantidade;
	}

	private ArrayList<Cedula> quantidadeCeludasNova(ArrayList<Cedula> quantidadeOriginal, int valor) {
		int qtde100 = quantidadeOriginal.get(0).getQuantidade();
		int qtde50 = quantidadeOriginal.get(1).getQuantidade();
		int qtde20 = quantidadeOriginal.get(2).getQuantidade();
		int qtde10 = quantidadeOriginal.get(3).getQuantidade();
		int qtde5 = quantidadeOriginal.get(4).getQuantidade();
		int qtde2 = quantidadeOriginal.get(5).getQuantidade();

		String[] alt100 = { "2x50", "5x20", "10x10", "20x5", "50x2" };
		if (qtde100 >= 1) {
			qtde100--;
			int posicao = ThreadLocalRandom.current().nextInt(0, alt100.length - 1);
			if (posicao == 0) {
				qtde50 += 2;
			} else if (posicao == 1) {
				qtde20 += 5;
			} else if (posicao == 2) {
				qtde10 += 10;
			} else if (posicao == 3) {
				qtde5 += 20;
			} else {
				qtde2 += 50;
			}
		}

		String[] alt50 = { "5x10", "10x5", "25x2" };
		if (qtde50 >= 1) {
			qtde50--;
			int posicao = ThreadLocalRandom.current().nextInt(0, alt50.length - 1);
			if (posicao == 0) {
				qtde10 += 5;
			} else if (posicao == 1) {
				qtde5 += 10;
			} else {
				qtde2 += 25;
			}
		}

		String[] alt20 = { "2x10", "4x5", "10x2" };
		if (qtde20 >= 1) {
			qtde20--;
			int posicao = ThreadLocalRandom.current().nextInt(0, alt20.length - 1);
			if (posicao == 0) {
				qtde10 += 2;
			} else if (posicao == 1) {
				qtde5 += 4;
			} else {
				qtde2 += 10;
			}
		}

		String[] alt10 = { "2x5", "5x2" };
		if (qtde10 >= 1) {
			qtde10--;
			int posicao = ThreadLocalRandom.current().nextInt(0, alt10.length - 1);
			if (posicao == 0) {
				qtde5 += 2;
			} else {
				qtde2 += 5;
			}
		}

		if (qtde5 % 10 == 0) {
			if (qtde5 >= 20) {
				qtde5 -= 10;
				qtde2 += qtde5 / 2;
			}
		}

		if (valor % 3 == 0) {
			qtde100 = 0;
			qtde50 = 0;
			qtde20 = 0;
			qtde10 = 0;
			qtde5 = valor / 5;
			qtde2 = 0;
		}

		ArrayList<Cedula> quantidade = new ArrayList<>();

		quantidade.add(new Cedula(100, qtde100));
		quantidade.add(new Cedula(50, qtde50));
		quantidade.add(new Cedula(20, qtde20));
		quantidade.add(new Cedula(10, qtde10));
		quantidade.add(new Cedula(5, qtde5));
		quantidade.add(new Cedula(2, qtde2));

		// System.out.println("-------\nR$100: " + qtde100);
		// System.out.println("R$50: " + qtde50);
		// System.out.println("R$20: " + qtde20);
		// System.out.println("R$10: " + qtde10);
		// System.out.println("R$5: " + qtde5);
		// System.out.println("R$2: " + qtde2);

		return quantidade;
	}
}
