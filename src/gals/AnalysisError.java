package gals;

public class AnalysisError extends Exception {
	private int position;
	private String erro;

	public AnalysisError(String msg, int position) {
		super(msg);
		this.position = position;
	}

	public AnalysisError(String msg) {
		super(msg);
		this.position = -1;
	}

	public AnalysisError(String msg, String erro) {
		super(msg);
		this.setErro(erro);
	}

	public AnalysisError(String msg, String erro, int position) {
		super(msg);
		this.position = position;
		this.setErro(erro);
	}

	public int getPosition() {
		return position;
	}

	public String toString() {
		return super.toString() + ", @ " + position;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
}
