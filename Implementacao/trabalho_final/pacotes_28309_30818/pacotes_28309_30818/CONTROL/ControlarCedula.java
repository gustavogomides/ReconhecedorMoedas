package pacotes_28309_30818.CONTROL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import pacotes_28309_30818.MODEL.Cedula;

public class ControlarCedula {

	private boolean opcao = false;

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

			gerarDialogOpcoes(valor, quantidadeCedulas, quantidadeCedulasNova, controleImagem);
		} else {
			gerarDialog(valor, quantidadeCedulas, controleImagem);
		}

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

	private void gerarDialogOpcoes(int valor, ArrayList<Cedula> cedulas, ArrayList<Cedula> cedulasNovas,
			ControlarImagem controleImagem) {

		JFrame frame = new JFrame("Cédulas que vai receber:");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new BorderLayout());

		JPanel panelValor = new JPanel(new GridLayout(1, 2));
		panelValor.setSize(new Dimension(500, 70));
		JLabel labelValor = new JLabel("Quantidade a ser recebida: R$" + valor);
		labelValor.setFont(new Font("SansSerif", Font.BOLD, 28));
		panelValor.add(labelValor);
		panelValor.setBackground(Color.GRAY);

		JButton btIr = new JButton("IR");
		btIr.setBackground(Color.DARK_GRAY);
		btIr.setForeground(Color.WHITE);
		btIr.setFont(new Font("SansSerif", Font.BOLD, 28));
		btIr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				if (opcao) {
					gerarDialog(valor, cedulas, controleImagem);
				} else {
					gerarDialog(valor, cedulasNovas, controleImagem);
				}
			}
		});
		panelValor.add(btIr);
		panel.add(panelValor, BorderLayout.NORTH);

		JPanel panelOpcoes = new JPanel(new GridBagLayout());

		JPanel panelCedulas = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 20, 50);

		JLabel labelOpcao = new JLabel("OPÇÃO 1");
		labelOpcao.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelCedulas.add(labelOpcao, gbc);

		gbc.gridy++;

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
		gbc.gridy++;
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton radioButton = new JRadioButton("OPÇÃO 1");
		radioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				opcao = true;
			}
		});
		buttonGroup.add(radioButton);
		panelCedulas.add(radioButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelOpcoes.add(panelCedulas, gbc);

		gbc.insets = new Insets(0, 0, 20, 50);
		JPanel panelCedulas2 = new JPanel(new GridBagLayout());
		JLabel labelOpcao2 = new JLabel("OPÇÃO 2");
		labelOpcao2.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelCedulas2.add(labelOpcao2, gbc);

		gbc.gridy++;

		for (Cedula cedula : cedulasNovas) {
			if (cedula.getQuantidade() != 0) {
				gbc.gridx = 0;
				JLabel labelQuantidade = new JLabel(cedula.getQuantidade() + " X ");
				labelQuantidade.setFont(new Font("SansSerif", Font.BOLD, 20));
				panelCedulas2.add(labelQuantidade, gbc);

				gbc.gridx = 1;
				JLabel labelImagem = new JLabel(new ImageIcon(cedula.getImagem()));
				panelCedulas2.add(labelImagem, gbc);
			}
			gbc.gridy++;
		}

		gbc.gridy++;
		JRadioButton radioButton2 = new JRadioButton("OPÇÃO 2");
		radioButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				opcao = false;
			}
		});
		buttonGroup.add(radioButton2);
		panelCedulas2.add(radioButton2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		panelOpcoes.add(panelCedulas2, gbc);

		panel.add(panelOpcoes, BorderLayout.CENTER);
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

	private ArrayList<Cedula> quantidadeCeludasNova(ArrayList<Cedula> quantidadeOriginal, int valor) {
		int qtde100 = quantidadeOriginal.get(0).getQuantidade();
		int qtde50 = quantidadeOriginal.get(1).getQuantidade();
		int qtde20 = quantidadeOriginal.get(2).getQuantidade();
		int qtde10 = quantidadeOriginal.get(3).getQuantidade();
		int qtde5 = quantidadeOriginal.get(4).getQuantidade();
		int qtde2 = quantidadeOriginal.get(5).getQuantidade();

		String[] alt100 = { "2x50", "5x20", "10x10", "20x5", "50x2" };
		if (qtde100 > 1) {
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
		if (qtde50 > 1) {
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
		if (qtde20 > 1) {
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
		if (qtde10 > 1) {
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

		System.out.println("-------\nR$100: " + qtde100);
		System.out.println("R$50: " + qtde50);
		System.out.println("R$20: " + qtde20);
		System.out.println("R$10: " + qtde10);
		System.out.println("R$5: " + qtde5);
		System.out.println("R$2: " + qtde2);

		return quantidade;
	}
}
