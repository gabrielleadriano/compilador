package gals;

public class SyntaticError extends AnalysisError {
	
	public SyntaticError(String msg, int position) {
		super(msg, position);
	}

	public SyntaticError(String msg) {
		super(msg);
	}

	public SyntaticError(String msg, String erro) {
		super(msg, erro);
	}
	
	public SyntaticError(String msg, String erro, int position) {
		super(msg, erro, position);
	}
}
