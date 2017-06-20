package pacotes_28309_30818.VIEW;

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
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import pacotes_28309_30818.CONTROL.ControlarImagem;
import pacotes_28309_30818.MODEL.Cedula;

public class ViewCedulas {

	private boolean opcao = false;

	public void gerarDialog(int valor, ArrayList<Cedula> cedulas, ControlarImagem controleImagem) {
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

	public void gerarDialogOpcoes(int valor, ArrayList<Cedula> cedulas, ArrayList<Cedula> cedulasNovas,
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
}
