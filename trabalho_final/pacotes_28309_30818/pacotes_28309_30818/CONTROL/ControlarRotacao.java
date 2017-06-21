package pacotes_28309_30818.CONTROL;

public class ControlarRotacao {

	public int[][] main(int nCol, int nLin, double angle, int imagem[][]) {
		int saida[][];
		int i, j, x0, y0, x1, y1;
		double ci, cj, x, y, p, q, cs, sn;

		angle = angle * Math.PI / 180;
		saida = new int[nCol][nLin];

		ci = nCol / 2; // (ci, cj): centro da imagem
		cj = nLin / 2;
		cs = Math.cos(-angle);
		sn = Math.sin(-angle);

		for (i = 0; i < nCol; i++) { // (i, j): coord. na imagem rotacionada
			for (j = 0; j < nLin; j++) { // (x, y): coord. na imagem original
				x = (i - ci) * cs - (j - cj) * sn + ci;
				y = (i - ci) * sn + (j - cj) * cs + cj;

				// Fora da imagem original
				if (x < 0)
					continue;

				else if (x >= nCol - 1)
					continue;

				if (y < 0)
					continue;

				else if (y >= nLin - 1)
					continue;

				// Interpolação Bilinear
				x0 = (int) Math.floor(x);
				y0 = (int) Math.floor(y);
				x1 = x0 + 1;
				y1 = y0 + 1;
				p = (x1 - x) * imagem[x0][y0] + (x - x0) * imagem[x1][y0];
				q = (x1 - x) * imagem[x0][y1] + (x - x0) * imagem[x1][y1];
				saida[i][j] = (int) ((y1 - y) * p + (y - y0) * q);

			}
		}

		return saida;
	}

}