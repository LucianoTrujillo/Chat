package Modelo;

public class ConversacionGrupal extends Conversacion{

	public ConversacionGrupal() {
		super();
	}
	
	public void agregarMensaje(String grupo, String emisor, String texto, boolean enviado) {
		mensajes.add(new Mensaje(grupo, emisor, texto, enviado));
	}
	
	
	
}
