package Modelo;

import java.util.ArrayList;

public class Conversacion {

	protected ArrayList<Mensaje> mensajes;
	
	public Conversacion() {
		mensajes = new ArrayList<Mensaje>();
	}
	
	public int cantidadMensajesRecibidosDe(String contacto) {
		int cantidad = 0;
		for(Mensaje msj: mensajes) {
			if(msj.getEmisor() == contacto) {
				cantidad += 1;
			} 
		}
		return cantidad;
	}
	
	public int cantidadMensajesTotal() {
		return mensajes.size();
	}
	
	public int cantidadMensajesRecibidos() {
		int cantidad = 0;
		for(Mensaje msj: mensajes) {
			if(!msj.getEnviado()) {
				cantidad += 1;
			} 
		}
		return cantidad;
	}
	
	public int cantidadMensajesEnviados() {
		int cantidad = 0;
		for(Mensaje msj: mensajes) {
			if(msj.getEnviado()) {
				cantidad += 1;
			} 

			//System.out.println(msj.getEnviado());
		}
		return cantidad;
	}
	
	public String get(int index) {
		return mensajes.get(index).getMensaje();
	}
	
}
