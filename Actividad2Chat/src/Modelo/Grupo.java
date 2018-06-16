package Modelo;

import java.util.ArrayList;

public class Grupo {

	private String nombre;
	private ArrayList<Contacto> contactos;
	
	public Grupo(String nombre) {
		this.nombre = nombre;
		contactos = new ArrayList<Contacto>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void agregarContacto(Contacto contacto) {
		contactos.add(contacto);
	}
	
	public int cantidadContactos() {
		return contactos.size();
	}
}
