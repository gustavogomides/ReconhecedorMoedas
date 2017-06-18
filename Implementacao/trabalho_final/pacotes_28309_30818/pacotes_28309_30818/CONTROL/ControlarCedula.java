package pacotes_28309_30818.CONTROL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import pacotes_28309_30818.MODEL.Cedula;

public class ControlarCedula {

	public ControlarCedula(int valor, ControlarImagem controleImagem) {
		ArrayList<Cedula> quantidadeCedulas = quantidadeCedulas(valor);

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

		gerarDialog(valor, quantidadeCedulas, controleImagem);
	}

	private void gerarDialog(int valor, ArrayList<Cedula> cedulas, ControlarImagem controleImagem) {
		JFrame frame = new JFrame("Cédulas que vai receber:");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new BorderLayout());

		JPanel panelValor = new JPanel();
		panelValor.setSize(new Dimension(500, 70));
		JLabel labelValor = new JLabel("Quantidade a ser recebida: R$" + valor);
		labelValor.setFont(new Font("SansSerif", Font.BOLD, 28));
		panelValor.add(labelValor);
		panel.add(panelValor, BorderLayout.NORTH);

		JPanel panelCedulas = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;

		gbc.insets = new Insets(0, 0, 20, 50);

		for (Cedula cedula : cedulas) {
			if (cedula.getQuantidade() != 0) {
				gbc.gridx = 0;
				JLabel labelQuantidade = new JLabel(cedula.getQuantidade() + " X ");
				labelQuantidade.setFont(new Font("SansSerif", Font.BOLD, 20));
				panelCedulas.add(labelQuantidade, gbc);

				gbc.gridx = 1;
				JLabel labelImagem = new JLabel(new ImageIcon(cedula.getImagem()));
				panelCedulas.add(labelImagem, gbc);
			}
			gbc.gridy++;
		}
		panel.add(panelCedulas, BorderLayout.CENTER);
		JScrollPane painelScroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		frame.add(painelScroll);
		frame.setVisible(true);
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

		System.out.println("R$100: " + qtde100);
		System.out.println("R$50: " + qtde50);
		System.out.println("R$20: " + qtde20);
		System.out.println("R$10: " + qtde10);
		System.out.println("R$5: " + qtde5);
		System.out.println("R$2: " + qtde2);

		return quantidade;
	}
}
