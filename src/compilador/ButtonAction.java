package compilador;

import gals.LexicalError;
import gals.Lexico;
import gals.Token;

public class ButtonAction {

	private String copiedText = "";
	
	public ButtonAction() {
	}
	
	public void copy(String copiedText) {
		this.copiedText = copiedText;
	}
	
	public String paste() {
		return copiedText;
	}
	
	public String getTeam() { 
		return "Bianca Krug de Jesus, Gabrielle Alice Adriano";
	}
}
