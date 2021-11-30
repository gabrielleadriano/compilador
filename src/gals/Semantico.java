package gals;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import compilador.Constants;

public class Semantico implements Constants {
	
	private List<String> operadores = new ArrayList<>();
	private List<String> codigo = new ArrayList<>();
	private Stack<TipoEnum> pilhaTipos = new Stack<>();
	
    public void executeAction(int action, Token token)	throws SemanticError {
        System.out.println("Ação #"+action+", Token: "+token);
        
        switch (action) {
		case 5:
			acao5(token);
			break;
		case 6:
			acao6(token);
			break;
		case 14:
			acao14();
			break;
		case 15:
			acao15();
			break;
		case 16:
			acao16();
			break;

		default:
			break;
		}
    }
    
	private void acao5(Token token) {
		pilhaTipos.push(TipoEnum.int64);
		codigo.add("ldc.i8" + token.getLexeme());
		codigo.add("conv.r8");
	}
	
	private void acao6(Token token) {
		pilhaTipos.push(TipoEnum.float64);
		codigo.add("ldc.r8" + token.getLexeme());
		
	}
	
	private void acao14() {
		TipoEnum tipo = pilhaTipos.pop();
		if(tipo == TipoEnum.int64) {
			codigo.add("conv.i8");
		}
		codigo.add("call void [mscorlib]System.Console::Write("+ tipo +")");
	}
	
	private void acao15() {
		codigo.add("ret } }");
	}

	private void acao16() {
		codigo.add("ret } }");
	}
	
}
