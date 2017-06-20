package pacotes_28309_30818.CONTROL;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import pacotes_28309_30818.VIEW.MontarPainelInicial;

public class ControlarAplicativo implements ActionListener {

	private MontarPainelInicial pnCenario;
	private Graphics desenhoCen, desenhoDir;
	private ControlarImagem controleImagem;
	private String nomeArquivoImagemDada;
	private char[][] imagemCinza;
	private char[][] imagemAtual;

	private int nLinImageAtual, nColImageAtual;
	private int nLinImageInic, nColImageInic;
	private boolean estadoDesenho;

	private ControlarMoeda controleMoeda;

	private int compressao = 10;

	// *******************************************************************************************
	public ControlarAplicativo() {
		pnCenario = new MontarPainelInicial(this);
		pnCenario.showPanel();
		estadoDesenho = false;
	}

	// *******************************************************************************************
	// METODO PARA CONTROLE DOS BOTOES DO APLICATIVO

	public void actionPerformed(ActionEvent e) {
		String comando, nomeArquivo;

		comando = e.getActionCommand();

		// DEFINE AMBIENTE GRAFICO
		if (!estadoDesenho) {
			pnCenario.iniciarGraphics();
			desenhoCen = pnCenario.getDesenhoC();
			desenhoDir = pnCenario.getDesenhoD();
		}

		// ENDS THE PROGRAM
		if (comando.equals("botaoFim")) {
			System.exit(0);
		}

		// INICIA O PROGRAMA
		if (comando.equals("botaoImagem")) {

			// LE IMAGEM SOLICITADA
			nomeArquivoImagemDada = pnCenario.escolherArquivo(1);
			if (nomeArquivoImagemDada != null) {
				controleImagem = new ControlarImagem(nomeArquivoImagemDada, desenhoCen);
				estadoDesenho = true;
				imagemCinza = controleImagem.getImagemCinza();
				nLinImageInic = controleImagem.getNLin();
				nColImageInic = controleImagem.getNCol();

				pnCenario.mudarBotoes();
				pnCenario.limpaPainelDir(desenhoDir);
				controleImagem.mostrarImagemMatriz(imagemCinza, nLinImageInic, nColImageInic, desenhoDir);

				nLinImageAtual = nLinImageInic;
				nColImageAtual = nColImageInic;
				imagemAtual = controleImagem.copiarImagem(imagemCinza, nLinImageInic, nColImageInic);

				pnCenario.enableCanny(true);
				pnCenario.enableTudo(true);
			}
		}

		if (comando.equals("filtroCanny")) {
			aplicarFiltroCanny((float) 1.0, true);
			pnCenario.controlePanelCanny.setVisible(true);
			pnCenario.controlePanelCompressao.setVisible(true);
			pnCenario.enableEncontrarObjeto(true);
			pnCenario.enableCompressao(true);
		}

		if (comando.equals("filtrar")) {
			float desvioPadrao, inferior, superior;
			desvioPadrao = setFloat(pnCenario.textField1.getText());
			inferior = setFloat(pnCenario.textField2.getText());
			superior = setFloat(pnCenario.textField3.getText());

			if (desvioPadrao == -1 || inferior == -1 || superior == -1) {
				JOptionPane.showMessageDialog(null, "Digite todos os parâmetros!");
			} else {
				if (desvioPadrao <= 0.1f) {
					JOptionPane.showMessageDialog(null, "Desvio Padrão tem que ser maior que 0.1!");
				} else if (inferior <= 0) {
					JOptionPane.showMessageDialog(null, "Inferior tem que ser maior que zero!");
				} else if (superior <= 0) {
					JOptionPane.showMessageDialog(null, "Superior tem que ser maior que zero!");
				} else {
					aplicarFiltroCanny(desvioPadrao, false);
				}
			}

		}

		if (comando.equals("encontrarObjetos")) {
			try {
				controleMoeda = new ControlarMoeda(imagemAtual, nLinImageAtual, nColImageAtual, desenhoDir,
						controleImagem, pnCenario, new ControlarMascara());
				pnCenario.enableEncontrarCedulas(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		if (comando.equals("encontrarCedulas")) {
			int total = valorMoedas(controleMoeda, false);
			if (total != -1) {
				new ControlarCedula(total, controleImagem);
			}
		}

		if (comando.equals("compressao")) {
			compressao(true, 0);
		}

		if (comando.equals("botaoSalva") && estadoDesenho) {
			nomeArquivo = pnCenario.escolherArquivo(2);
			controleImagem.gravarImagem(nomeArquivo, imagemAtual, nLinImageAtual, nColImageAtual);
		}

		if (comando.equals("total")) {
			try {
				aplicarFiltroCanny((float) 1.0, true);
				Thread.sleep(1000);
				ControlarMoeda moedaController = new ControlarMoeda(imagemAtual, nLinImageAtual, nColImageAtual,
						desenhoDir, controleImagem, pnCenario, new ControlarMascara());
				Thread.sleep(1000);
				int valor = valorMoedas(moedaController, true);
				if (valor != -1) {
					new ControlarCedula(valor, controleImagem);
					compressao(false, valor);
				}
				Thread.sleep(1000);
			} catch (InterruptedException | IOException e2) {
				e2.printStackTrace();
			}
		}

		if (comando.equals("botaoReset") && estadoDesenho) {
			pnCenario.limpaPainelCen(desenhoCen);
			controleImagem = new ControlarImagem(nomeArquivoImagemDada, desenhoCen);
			nLinImageAtual = nLinImageInic;
			nColImageAtual = nColImageInic;
			imagemAtual = controleImagem.copiarImagem(imagemCinza, nLinImageInic, nColImageInic);
			controleImagem.mostrarImagemMatriz(imagemAtual, nLinImageAtual, nColImageAtual, desenhoDir);
			pnCenario.limpaPainelDir(desenhoDir);
			pnCenario.limpaPainelCen(desenhoCen);
			pnCenario.enableCanny(false);
			pnCenario.enableCompressao(false);
			pnCenario.enableEncontrarObjeto(false);
			pnCenario.enableEncontrarCedulas(false);
			pnCenario.enableTudo(false);
		}

		if (comando.equals("btAcao11")) {
			compressao = 0;
		}
		if (comando.equals("btAcao12")) {
			compressao = 5;
		}
		if (comando.equals("btAcao13")) {
			compressao = 10;
		}
		if (comando.equals("btAcao14")) {
			compressao = 20;
		}
		if (comando.equals("btAcao15")) {
			compressao = 30;
		}
	}

	// *******************************************************************************************
	private float setFloat(String text) {
		if (text != null && !text.isEmpty()) {
			return Float.parseFloat(text);
		} else {
			return -1;
		}
	}

	// *******************************************************************************************
	private void aplicarFiltroCanny(float desvioPadrao, boolean copiarImagem) {

		ControlarCanny canny = new ControlarCanny(desvioPadrao);

		char[][] imgChar = controleImagem.imagemCinzaOriginal;

		BufferedImage image = controleImagem.transformarMatriz2Buffer(imgChar, nLinImageAtual, nColImageAtual);

		canny.setSourceImage(image);
		try {
			canny.process();
			Image img = canny.getEdgeImage();
			BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null),
					BufferedImage.TYPE_INT_ARGB);

			Graphics2D bGr = bimage.createGraphics();
			bGr.drawImage(img, 0, 0, null);
			bGr.dispose();

			controleImagem.criarImagemCinza(bimage, false);

			imagemCinza = controleImagem.getImagemCinza();
			nLinImageInic = controleImagem.getNLin();
			nColImageInic = controleImagem.getNCol();

			pnCenario.limpaPainelDir(desenhoDir);

			controleImagem.mostrarImagemMatriz(imagemCinza, nLinImageInic, nColImageInic, desenhoDir);

			if (copiarImagem) {
				nLinImageAtual = nLinImageInic;
				nColImageAtual = nColImageInic;
				imagemAtual = controleImagem.copiarImagem(imagemCinza, nLinImageInic, nColImageInic);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// *******************************************************************************************
	private int valorMoedas(ControlarMoeda controlarMoeda, boolean tudo) {
		try {
			double total = controlarMoeda.calcularValor();
			if (controlarMoeda.verificaValorInteiro(total)) {
				JOptionPane.showMessageDialog(null, "Montante de moedas: R$" + String.format("%.2f", total));
				if (!tudo) {
					pnCenario.enableEncontrarCedulas(true);
				}
				return (int) total;
			} else {
				int inferior = (int) Math.floor(total);
				int superior = (int) Math.ceil(total);
				double difInferior = Math.abs(total - inferior);
				double difSuperior = Math.abs(superior - total);
				String str = "Valor encontrado: R$" + total;
				str += "\nVocê deve retirar R$" + String.format("%.2f", difInferior) + " ou colocar mais R$ "
						+ String.format("%.2f", difSuperior) + "!";
				JOptionPane.showMessageDialog(null, str);
				return -1;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return -1;
		}
	}

	private void compressao(boolean opcao, int valor) {
		String arq = null;

		if (opcao) {
			JFileChooser fc = new JFileChooser();
			JOptionPane.showMessageDialog(null, "Selecione onde deseja salvar o arquivo no formato .jpg");

			FileNameExtensionFilter tipos = new FileNameExtensionFilter("JPEG (.jpg)", "jpg");
			fc.addChoosableFileFilter(tipos);
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnValue = fc.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				arq = fc.getSelectedFile().toString();
			}

		} else {
			String caminhoAtual = new File("").getAbsolutePath();

			String nomeArquivo = valor + "_";
			Date date = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
			nomeArquivo += formatador.format(date);
			formatador = new SimpleDateFormat("hh-mm");
			nomeArquivo += "_" + formatador.format(date);
			arq = caminhoAtual + "/pacotes_28309_30818/pacotes_28309_30818/imagens/compressao/" + nomeArquivo;
		}

		controleImagem.gravarImagem(arq, imagemAtual, nLinImageAtual, nColImageAtual);
		String arq2 = arq + "_comprimido.jpg";

		arq = arq + ".jpg";

		ControlarCompressao controleCompressao = new ControlarCompressao(arq, arq2, compressao);

		if (controleCompressao.comprimir()) {
			if (opcao) {
				JOptionPane.showMessageDialog(null, "Imagem Comprimida!");
			} else {
				System.out.println("Imagem Comprimida!");
			}

		} else {
			if (opcao) {
				JOptionPane.showMessageDialog(null, "Erro na Compressão!");
			} else {
				System.out.println("Imagem Comprimida!");
			}

		}
	}
}
