package gals;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import compilador.TipoEnum;

public class Semantico implements Constants {

	private List<String> listaIdentificadores = new ArrayList<>();
	private List<String> codigo = new ArrayList<>();
	private Stack<TipoEnum> pilhaTipos = new Stack<>();

	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação #" + action + ", Token: " + token);

		switch (action) {
		case 1:
			acao1(token);
			break;
		case 2:
			acao2(token);
			break;
		case 3:
			acao3(token);
			break;
		case 4:
			acao4(token);
			break;
		case 5:
			acao5(token);
			break;
		case 6:
			acao6(token);
			break;
		case 7:
			acao7(token);
			break;
		case 8:
			acao8(token);
			break;
		case 9:
			acao9(token);
			break;
		case 11:
			acao11();
			break;
		case 12:
			acao12();
			break;
		case 13:
			acao13(token);
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
		case 17:
			acao17(token);
			break;
		case 18:
			acao18();
			break;
		case 19:
			acao19();
			break;
		case 20:
			acao20();
			break;
		case 21:
			acao21(token);
			break;
		case 22:
			acao22(token);
			break;
		case 34:
			acao34(token);
			break;

		default:
			break;
		}

		System.out.println(codigo.toString());
	}

	private void acao1(Token token) throws SemanticError {
		TipoEnum tipo1 = pilhaTipos.pop();
		TipoEnum tipo2 = pilhaTipos.pop();

		if ((tipo1 == TipoEnum.float64 || tipo1 == TipoEnum.int64)
				&& (tipo2 == TipoEnum.float64 || tipo2 == TipoEnum.int64)) {
			throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética: ", token.getPosition());
		}

		if (tipo1 == TipoEnum.float64 || tipo2 == TipoEnum.float64) {
			pilhaTipos.push(TipoEnum.float64);
		} else {
			pilhaTipos.push(TipoEnum.int64);
		}
		codigo.add("add ");
	}

	private void acao2(Token token) throws SemanticError {
		TipoEnum tipo1 = pilhaTipos.pop();
		TipoEnum tipo2 = pilhaTipos.pop();

		if ((tipo1 == TipoEnum.float64 || tipo1 == TipoEnum.int64)
				&& (tipo2 == TipoEnum.float64 || tipo2 == TipoEnum.int64)) {
			throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética: ", token.getPosition());
		}

		if (tipo1 == TipoEnum.float64 || tipo2 == TipoEnum.float64) {
			pilhaTipos.push(TipoEnum.float64);
		} else {
			pilhaTipos.push(TipoEnum.int64);
		}
		codigo.add("sub ");
	}

	private void acao3(Token token) throws SemanticError {
		TipoEnum tipo1 = pilhaTipos.pop();
		TipoEnum tipo2 = pilhaTipos.pop();

		if ((tipo1 == TipoEnum.float64 || tipo1 == TipoEnum.int64)
				&& (tipo2 == TipoEnum.float64 || tipo2 == TipoEnum.int64)) {
			throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética: ", token.getPosition());
		}

		if (tipo1 == TipoEnum.float64 || tipo2 == TipoEnum.float64) {
			pilhaTipos.push(TipoEnum.float64);
		} else {
			pilhaTipos.push(TipoEnum.int64);
		}
		codigo.add("mul ");
	}

	private void acao4(Token token) throws SemanticError {
		TipoEnum tipo1 = pilhaTipos.pop();
		TipoEnum tipo2 = pilhaTipos.pop();
		if (tipo1 == tipo2) {
			pilhaTipos.push(tipo1);
		} else {
			throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética: ", token.getPosition());
		}
		codigo.add("div ");
	}

	private void acao5(Token token) {
		pilhaTipos.push(TipoEnum.int64);
		codigo.add("ldc.i8 " + token.getLexeme());
		codigo.add("conv.r8 ");
	}

	private void acao6(Token token) {
		pilhaTipos.push(TipoEnum.float64);
		codigo.add("ldc.r8 " + token.getLexeme());
	}

	private void acao7(Token token) throws SemanticError {
		TipoEnum tipo = pilhaTipos.pop();
		if (tipo == TipoEnum.float64 || tipo == TipoEnum.int64) {
			pilhaTipos.push(tipo);
		} else {
			throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética: ", token.getPosition());
		}
	}

	private void acao8(Token token) throws SemanticError {
		TipoEnum tipo = pilhaTipos.pop();
		if (tipo == TipoEnum.float64 || tipo == TipoEnum.int64) {
			pilhaTipos.push(tipo);
		} else {
			throw new SemanticError("tipo(s) incompatível(is) em expressão aritmética: ", token.getPosition());
		}
		codigo.add("ldc.i8 -1 ");
		codigo.add("conv.r8 ");
		codigo.add("mul ");
	}

	private void acao9(Token token) {
		listaIdentificadores.add(token.getLexeme());
	}

	private void acao11() {
		pilhaTipos.push(TipoEnum.bool);
		codigo.add("ldc.i4.1 ");
	}

	private void acao12() {
		pilhaTipos.push(TipoEnum.bool);
		codigo.add("ldc.i4.0 ");
	}

	private void acao13(Token token) throws SemanticError {
		TipoEnum tipo = pilhaTipos.pop();
		if (tipo == TipoEnum.bool) {
			pilhaTipos.push(TipoEnum.bool);
		} else {
			throw new SemanticError("tipo(s) incompatível(is) em expressão lógica: ", token.getPosition());
		}
		codigo.add("ldc.i4.1 ");
		codigo.add("xor ");
	}

	private void acao14() {
		TipoEnum tipo = pilhaTipos.pop();
		if (tipo == TipoEnum.int64) {
			codigo.add("conv.i8 ");
		}
		codigo.add("call void [mscorlib]System.Console::Write(" + tipo + ") ");
	}

	private void acao15() {
		codigo.add(".assembly extern mscorlib {}\r\n" + ".assembly _codigo_objeto{}\r\n"
				+ ".module _codigo_objeto.exe\r\n" + "\r\n" + ".class public _UNICA{\r\n"
				+ ".method static public void _principal() {\r\n" + "	.entrypoint ");
	}

	private void acao16() {
		codigo.add("	ret\r\n	}\r\n }");
	}

	private void acao17(Token token) {
		pilhaTipos.push(TipoEnum.string);
		codigo.add("ldstr " + token.getLexeme());
	}

	private void acao18() {
		pilhaTipos.push(TipoEnum.string);
		codigo.add("ldstr \"\\n\"");
	}

	private void acao19() {
		pilhaTipos.push(TipoEnum.string);
		codigo.add("ldstr \" \"");
	}

	private void acao20() {
		pilhaTipos.push(TipoEnum.string);
		codigo.add("ldstr \"\\t\"");
	}

	private void acao21(Token token) throws SemanticError {
		TipoEnum tipo1 = pilhaTipos.pop();
		TipoEnum tipo2 = pilhaTipos.pop();

		if (tipo1 == TipoEnum.bool || tipo2 == TipoEnum.bool) {
			pilhaTipos.push(TipoEnum.bool);
			codigo.add("and ");
		} else {
			throw new SemanticError("tipo(s) incompatível(is) em expressão lógica: ", token.getPosition());
		}
	}

	private void acao22(Token token) throws SemanticError {
		TipoEnum tipo1 = pilhaTipos.pop();
		TipoEnum tipo2 = pilhaTipos.pop();

		if (tipo1 == TipoEnum.bool && tipo2 == TipoEnum.bool) {
			pilhaTipos.push(TipoEnum.bool);
			codigo.add("or ");
		} else {
			throw new SemanticError("tipo(s) incompatível(is) em expressão lógica: ", token.getPosition());
		}
	}

	private void acao34(Token token) {
		String identificador = token.getLexeme();
		codigo.add("ldloc " + identificador);

		if (identificador.startsWith("I")) {
			pilhaTipos.push(TipoEnum.int64);
			codigo.add("conv.r8");
		} else if (identificador.startsWith("F")) {
			pilhaTipos.push(TipoEnum.float64);
		} else if (identificador.startsWith("S")) {
			pilhaTipos.push(TipoEnum.string);
		} else if (identificador.startsWith("B")) {
			pilhaTipos.push(TipoEnum.bool);
		}
	}
}
