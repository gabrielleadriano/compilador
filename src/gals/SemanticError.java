package gals;

public class SemanticError extends AnalysisError
{
    public SemanticError(String msg, int position)
	 {
        super(msg, position);
    }

    public SemanticError(String msg)
    {
        super(msg);
    }

	public SemanticError(String msg, String erro, int position) {
		super(msg, erro, position);
	}
}
