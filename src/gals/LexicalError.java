package gals;

public class LexicalError extends AnalysisError {

	public LexicalError(String msg, int position) {
		super(msg, position);
	}

	public LexicalError(String msg) {
		super(msg);
	}

	public LexicalError(String msg, String erro) {
		super(msg, erro);
	}
	
	public LexicalError(String msg, String erro, int position) {
		super(msg, erro, position);
	}
}
