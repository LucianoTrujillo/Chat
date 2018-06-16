package Modelo;

public class Mensaje {
	private String emisor;
	private String texto;
	private boolean enviado;
	
	public Mensaje(String emisor,String texto, boolean enviado) {
		this.texto = emisor + ": " + texto;
		this.enviado = enviado;
		this.emisor = emisor;
	}
	
	public Mensaje(String grupo, String emisor,String texto, boolean enviado) {
		this.texto = emisor + ": " + texto;
		this.enviado = enviado;
		this.emisor = emisor;
		//System.out.println(this.enviado);
	}
	
	public boolean getEnviado() {
		return enviado;
	}
	
	public String getMensaje() {
		return texto;
	}
	
	public String getEmisor() {
		return emisor;
	}
}
