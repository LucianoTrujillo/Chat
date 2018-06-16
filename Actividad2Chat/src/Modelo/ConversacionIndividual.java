package Modelo;

public class ConversacionIndividual extends Conversacion{

	public ConversacionIndividual() {
		super();
	}
	
	public void agregarMensaje(String emisor, String texto, boolean enviado) {
		mensajes.add(new Mensaje(emisor, texto, enviado));
	}
	
	
}
