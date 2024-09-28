package Floresta;

public abstract class Local {
	private int custoMovimento = 1;
	private int[] posicao;
	
	public Local() {
		
	}
	
	public int getCusto() {
		return this.custoMovimento;
	}
	
	protected void setCusto(int custo) {
		this.custoMovimento = custo;
	}
	
	public int[] getPosicao() {
		return this.posicao;
	}
}
