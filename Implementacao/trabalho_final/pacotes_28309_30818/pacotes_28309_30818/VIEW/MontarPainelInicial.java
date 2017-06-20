package pacotes_28309_30818.VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import pacotes_28309_30818.CONTROL.ControlarAplicativo;

public class MontarPainelInicial {

	private JFrame baseFrame;
	private JPanel basePanel;
	private JPanel outputPanel, outputPanelEsq, outputPanelCen, outputPanelDir;
	public JPanel controlePanelCanny;
	public JPanel controlePanelCompressao;

	private JButton btCanny;
	public JButton btEncontrarObjetos;
	private JButton btCompressao;
	private JButton btSalva;
	public JButton btTudo;
	public JButton btEncontrarCedulas;
	public JButton btReset;

	private Graphics desenhoCen;
	private Graphics desenhoDir;

	public JTextField textField1;
	public JTextField textField2;
	public JTextField textField3;

	// *******************************************************************************************
	public MontarPainelInicial(ControlarAplicativo controlePrograma) {
		JPanel buttonPanel;
		JPanel titlePanel;
		JPanel acao3Panel;

		// LAYOUT
		baseFrame = new JFrame();
		baseFrame.setLayout(new BoxLayout(baseFrame.getContentPane(), BoxLayout.Y_AXIS));

		baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // FITS PANEL TO THE
															// ACTUAL MONITOR
															// SIZE
		baseFrame.setUndecorated(true); // TURN OFF ALL THE PANEL BORDERS

		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());

		// TITLE PANEL
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(0, 50));
		titlePanel.setBackground(Color.gray);

		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout());

		outputPanelEsq = new JPanel();
		outputPanelEsq.setPreferredSize(new Dimension(130, 0));
		outputPanelEsq.setLayout(new BoxLayout(outputPanelEsq, BoxLayout.Y_AXIS));
		outputPanelEsq.setBackground(Color.lightGray);

		outputPanelCen = new JPanel();
		outputPanelCen.setBackground(new Color(220, 220, 210));
		outputPanelCen.setLayout(new BorderLayout());

		outputPanelDir = new JPanel();
		outputPanelDir.setBackground(new Color(210, 200, 200));
		outputPanelDir.setPreferredSize(new Dimension(580, 0));
		outputPanelDir.setLayout(new BorderLayout());

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 50));
		buttonPanel.setBackground(Color.gray);

		// PANEL TITLE
		JLabel titulo;
		titulo = new JLabel("Francis Ribeiro e Gustavo Gomides");
		titulo.setForeground(Color.black);
		titulo.setFont(new Font("Dialog", Font.BOLD, 25));
		titlePanel.add(titulo);

		// ADDING BUTTONS
		addAButton("New Image", "botaoImagem", buttonPanel, true, controlePrograma);
		btReset = addAButton("Reset", "botaoReset", buttonPanel, false, controlePrograma);
		btCanny = addAButton("Filtro de Canny", "filtroCanny", buttonPanel, false, controlePrograma);
		btEncontrarObjetos = addAButton("Encontrar Objetos", "encontrarObjetos", buttonPanel, false, controlePrograma);
		btEncontrarCedulas = addAButton("Encontrar Cédulas", "encontrarCedulas", buttonPanel, false, controlePrograma);
		btCompressao = addAButton("Compressão", "compressao", buttonPanel, false, controlePrograma);
		btTudo = addAButton("Receber Cédulas", "total", buttonPanel, false, controlePrograma);
		btSalva = addAButton("Save", "botaoSalva", buttonPanel, false, controlePrograma);
		addAButton("END", "botaoFim", buttonPanel, true, controlePrograma);

		// ADDING RADIO BUTTON PARA CONTROLE DA ACAO3
		controlePanelCanny = new JPanel();
		controlePanelCanny.setBackground(Color.lightGray);
		controlePanelCanny.setMaximumSize(new Dimension(130, 300));
		outputPanelEsq.add(controlePanelCanny);

		acao3Panel = new JPanel();

		JPanel panel = new JPanel(new GridLayout(7, 1));
		JLabel label1 = new JLabel("Desvio Padrão");
		panel.add(label1);
		textField1 = new JTextField(5);
		panel.add(textField1);

		JLabel label2 = new JLabel("Inferior");
		panel.add(label2);
		textField2 = new JTextField(5);
		panel.add(textField2);

		JLabel label3 = new JLabel("Superior");
		panel.add(label3);
		textField3 = new JTextField(5);
		panel.add(textField3);

		addAButton("OK", "filtrar", panel, true, controlePrograma);

		acao3Panel.add(panel);
		acao3Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Canny"));
		controlePanelCanny.add(acao3Panel);
		controlePanelCanny.setVisible(false);

		// ADDING RADIO BUTTON PARA CONTROLE DA ACAO 1
		controlePanelCompressao = new JPanel();
		controlePanelCompressao.setBackground(Color.lightGray);
		outputPanelEsq.add(controlePanelCompressao);

		JPanel panelCompressao = new JPanel(new GridLayout(5, 1));

		JRadioButton btAcao11 = new JRadioButton(" Muito alta ", false);
		JRadioButton btAcao12 = new JRadioButton(" Alta ", false);
		JRadioButton btAcao13 = new JRadioButton(" Média ", false);
		JRadioButton btAcao14 = new JRadioButton(" Baixa", false);
		JRadioButton btAcao15 = new JRadioButton(" Muito baixa", true);

		ButtonGroup btRdAcao1 = new ButtonGroup();
		btRdAcao1.add(btAcao11);
		btRdAcao1.add(btAcao12);
		btRdAcao1.add(btAcao13);
		btRdAcao1.add(btAcao14);
		btRdAcao1.add(btAcao15);

		btAcao11.setActionCommand("btAcao11");
		btAcao11.addActionListener(controlePrograma);
		btAcao12.setActionCommand("btAcao12");
		btAcao12.addActionListener(controlePrograma);
		btAcao13.setActionCommand("btAcao13");
		btAcao13.addActionListener(controlePrograma);
		btAcao14.setActionCommand("btAcao14");
		btAcao14.addActionListener(controlePrograma);
		btAcao15.setActionCommand("btAcao15");
		btAcao15.addActionListener(controlePrograma);

		panelCompressao.add(btAcao11);
		panelCompressao.add(btAcao12);
		panelCompressao.add(btAcao13);
		panelCompressao.add(btAcao14);
		panelCompressao.add(btAcao15);

		JPanel acao1Panel = new JPanel();
		acao1Panel.add(panelCompressao);
		acao1Panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Compressão"));
		controlePanelCompressao.add(acao1Panel);
		controlePanelCompressao.setVisible(false);

		// VISIBLE PANELS
		outputPanel.add(outputPanelEsq, BorderLayout.LINE_START);
		outputPanel.add(outputPanelCen, BorderLayout.CENTER);
		outputPanel.add(outputPanelDir, BorderLayout.LINE_END);

		basePanel.add(titlePanel, BorderLayout.PAGE_START);
		basePanel.add(outputPanel, BorderLayout.CENTER);
		basePanel.add(buttonPanel, BorderLayout.PAGE_END);

		baseFrame.add(basePanel);
		baseFrame.setVisible(true);
	}

	// *******************************************************************************************
	public void limpaPainelCen(Graphics desenho) {
		outputPanelCen.removeAll();
		outputPanelCen.update(desenho);
	}

	// *******************************************************************************************
	public void limpaPainelDir(Graphics desenho) {
		outputPanelDir.removeAll();
		outputPanelDir.update(desenho);
	}

	// *******************************************************************************************
	// METODO UTILIZADO PARA ADICIONAR UM BOTAO A UM CONTAINER DO PROGRAMA

	private JButton addAButton(String textoBotao, String textoControle, Container container, boolean estado,
			ControlarAplicativo controlePrograma) {
		JButton botao;

		botao = new JButton(textoBotao);
		botao.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(botao);

		botao.setEnabled(estado);

		botao.setActionCommand(textoControle);

		botao.addActionListener(controlePrograma);

		return (botao);
	}

	// *******************************************************************************************
	public void mudarBotoes() {
		btSalva.setEnabled(true);
		btReset.setEnabled(true);
	}

	// *******************************************************************************************
	// METODO PARA APRESENTAR O MENU DE ESCOLHA DE ARQUIVOS
	// 1 - PARA LEITURA
	// 2 - PARA GRAVACAO

	public String escolherArquivo(int operacao) {
		int retorno;
		String caminhoArquivo;
		JFileChooser arquivo;

		retorno = 0;
		arquivo = new JFileChooser(new File("."));

		// TIPO DE OPERACAO A SER REALIZADA
		switch (operacao) {
		case 1:
			retorno = arquivo.showOpenDialog(null);
			break;

		case 2:
			retorno = arquivo.showSaveDialog(null);
		}

		// OPERACAO
		caminhoArquivo = null;

		if (retorno == JFileChooser.APPROVE_OPTION) {
			try {
				caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("erro: " + e);
			}
		}

		return (caminhoArquivo);
	}

	// *******************************************************************************************
	// METODO PARA MOSTRAR O FRAME BASICO

	public void showPanel() {
		basePanel.setVisible(true);
	}

	// *******************************************************************************************
	public void iniciarGraphics() {
		desenhoCen = outputPanelCen.getGraphics();
		desenhoDir = outputPanelDir.getGraphics();
	}

	// *******************************************************************************************
	public Graphics getDesenhoC() {
		return (desenhoCen);
	}

	// *******************************************************************************************
	public Graphics getDesenhoD() {
		return (desenhoDir);
	}

	// ******************************************************************************************
	public void enableCanny(Boolean b) {
		btCanny.setEnabled(b);
	}

	// ******************************************************************************************
	public void enableEncontrarObjeto(Boolean b) {
		btEncontrarObjetos.setEnabled(b);
	}

	// ******************************************************************************************
	public void enableCompressao(Boolean b) {
		btCompressao.setEnabled(b);
	}

	// ******************************************************************************************
	public void enableEncontrarCedulas(Boolean b) {
		btEncontrarCedulas.setEnabled(b);
	}

	// *******************************************************************************************
	public void enableTudo(Boolean b) {
		btTudo.setEnabled(true);
	}
}
