package compilador;

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
		
	public String run() {
		return "Compila��o de programas ainda n�o foi implementada";
	}
	
	public String getTeam() { 
		return "Bianca Krug de Jesus, Gabrielle Alice Adriano";
	}
}
