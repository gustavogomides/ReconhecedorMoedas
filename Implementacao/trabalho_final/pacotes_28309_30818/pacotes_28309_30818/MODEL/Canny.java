package pacotes_28309_30818.MODEL;

public class Canny {

	private float limiteInferior;
	private float limiteSuperior;
	private float desvioPadrao;

	public Canny(float limiteInferior, float limiteSuperior, float desvioPadrao) {
		super();
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
		this.desvioPadrao = desvioPadrao;
	}

	public float getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(float limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public float getLimiteSuperior() {
		return limiteSuperior;
	}

	public void setLimiteSuperior(float limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}

	public float getDesvioPadrao() {
		return desvioPadrao;
	}

	public void setDesvioPadrao(float desvioPadrao) {
		this.desvioPadrao = desvioPadrao;
	}

	
}
