package Modelo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Chat6toTIC {

	private Contacto usuario;
	private Hashtable<String, Grupo> grupos = new Hashtable<String, Grupo>(); //Creacion de hashTable con una columna de nombres de grupo y la otra de objectos grupo
	private Hashtable<String, Contacto> contactos = new Hashtable<String, Contacto>(); // Creacion de hashTable con una columna de nombres y la otra de objectos contacto
	private Hashtable<String, ConversacionIndividual> conversacionesIndividuales = new Hashtable<String, ConversacionIndividual>();
	private Enumeration convIndEnum;
	private Hashtable<String, ConversacionGrupal> conversacionesGrupales = new Hashtable<String, ConversacionGrupal>();
	private Enumeration convGrupalEnum;
   
	
	public Chat6toTIC(String nombre){
		usuario = new Contacto(nombre);
	}
	
	public void agregarContacto(String nombre) {
		contactos.put(nombre, new Contacto(nombre)); //Se Agregar nuevo contacto a la hasTable, con su respectivo nombre y objeto contacto.
	}
	
	public int cantidadDeContactos() {
		return contactos.size(); //Se devuelve la cantidad de filas de la hasTable, es decir, la cantidad de contactos
	}
	
	public boolean existeContacto(String contacto) {
		return contactos.get(contacto) != null;
	}
	
	public void crearGrupo(String nombre) {
		grupos.put(nombre, new Grupo(nombre)); //Se Agregar nuevo grupo a la hasTable, con su respectivo nombre y objeto grupo.
		grupos.get(nombre).agregarContacto(usuario); // Se agrega el usuario al grupo
	} 
	
	public int cantidadDeGrupos() {
		return grupos.size();
	}
	
	public void agregarContactoAGrupo(String contacto, String grupo) {
		grupos.get(grupo).agregarContacto(contactos.get(contacto)); //se agrega el contacto al grupo 
	}
	
	public int cantidadMiembrosEnGrupo(String grupo) {
		return grupos.get(grupo).cantidadContactos(); //se devuelve el Size de la lista de contactos del grupo
	}
	
	public boolean existeGrupo(String grupo) {
		return grupos.get(grupo) != null; //si no existe un grupo con ese nombre
	}
	
	public void recibirMensajeDe(String contacto, String texto) {
		if(existeContacto(contacto)) { 
			if(conversacionesIndividuales.get(contacto) == null) { //Si no existe la conver
				conversacionesIndividuales.put(contacto, new ConversacionIndividual()); //crear conver
				convIndEnum = conversacionesIndividuales.keys(); //actualizar el enum con el nuevo contacto
			}
			conversacionesIndividuales.get(contacto).agregarMensaje(contacto, texto, false); //agregar un mensaje en la conver correspondiente a ese contacto
			
		}
		else {
			System.out.println("error");
		}
	}
	
	public void enviarMensajeA(String contacto, String texto) {
		if(existeContacto(contacto)) {
			if(conversacionesIndividuales.get(contacto) == null) { //Si no existe la conver
				conversacionesIndividuales.put(contacto, new ConversacionIndividual()); //crear conver
			}
			conversacionesIndividuales.get(contacto).agregarMensaje("Yo", texto, true); //agregar un mensaje en la conver correspondiente a ese contacto
			
		}
		else {
			System.out.println("error");
		}
		
	}
	
	public int cantidadTotalMensajesRecibidos() {
		int cantidadMensajes = 0;
		convIndEnum = conversacionesIndividuales.keys();
		while(convIndEnum.hasMoreElements()) { //recorrer la hastTable de conversacionesIndividuales con el Enum
			cantidadMensajes += (int)conversacionesIndividuales.get(convIndEnum.nextElement()).cantidadMensajesRecibidos(); //sumar en una variable la cantidad de mensajes de cada Conversacion
			
		}
		convGrupalEnum = conversacionesGrupales.keys();
		while(convGrupalEnum.hasMoreElements()) { //recorrer la hastTable de conversacionesIndividuales con el Enum
			cantidadMensajes += (int)conversacionesGrupales.get(convGrupalEnum.nextElement()).cantidadMensajesRecibidos(); //sumar en una variable la cantidad de mensajes de cada Conversacion
		}
		return cantidadMensajes;
	}
	
	public int cantidadTotalMensajesEnviados() {
		int cantidadMensajes = 0;
		convIndEnum = conversacionesIndividuales.keys();
		while(convIndEnum.hasMoreElements()) { //recorrer la hastTable de conversacionesIndividuales con el Enum
			System.out.println((String)convIndEnum.nextElement());
			cantidadMensajes += (int)conversacionesIndividuales.get(convIndEnum.nextElement()).cantidadMensajesEnviados(); //sumar en una variable la cantidad de mensajes de cada Conversacion
			
		}
		while(convGrupalEnum.hasMoreElements()) { //recorrer la hastTable de conversacionesIndividuales con el Enum
			cantidadMensajes += (int)conversacionesGrupales.get(convGrupalEnum.nextElement()).cantidadMensajesEnviados(); //sumar en una variable la cantidad de mensajes de cada Conversacion
		}	
		return cantidadMensajes;
	}
	
	public int cantidadMensajesDe(String contacto) {
		int cantidad = 0;
		convIndEnum = conversacionesIndividuales.keys();
		while(convIndEnum.hasMoreElements()) { //recorrer la hastTable de conversacionesIndividuales con el Enum
			cantidad += (int)conversacionesIndividuales.get(convIndEnum.nextElement()).cantidadMensajesRecibidosDe(contacto); //sumar en una variable la cantidad de mensajes de cada Conversacion
		}
		convGrupalEnum =  conversacionesGrupales.keys();
		while(convGrupalEnum.hasMoreElements()) { //recorrer la hastTable de conversacionesIndividuales con el Enum
			cantidad += (int)conversacionesGrupales.get(convGrupalEnum.nextElement()).cantidadMensajesRecibidosDe(contacto); //sumar en una variable la cantidad de mensajes de cada Conversacion
		}	
		return cantidad;
	}
	
	public int cantidadMensajesEnviadosA(String contacto) {
		return conversacionesIndividuales.get(contacto).cantidadMensajesEnviados();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	}
	
	public ConversacionIndividual obtenerConversacionCon(String contacto) {
		return conversacionesIndividuales.get(contacto);
	}
	
	public void recibirMensajeDeGrupo(String grupo, String contacto, String texto) {
		if(existeGrupo(grupo)) {
			if(existeContacto(contacto)) {
				if(conversacionesGrupales.get(grupo) == null) {
					conversacionesGrupales.put(grupo, new ConversacionGrupal());
					convGrupalEnum = conversacionesGrupales.keys(); //actualizar el enum con el nuevo contacto
				}
				conversacionesGrupales.get(grupo).agregarMensaje(grupo, contacto, texto, false); //agregar un mensaje en la conver correspondiente a ese contacto
			}
		}
	}
	
	public void enviarMensajeAGrupo(String grupo, String texto) {
		if(existeGrupo(grupo)) {
			if(conversacionesGrupales.get(grupo) == null) {
				conversacionesGrupales.put(grupo, new ConversacionGrupal());
			}
			conversacionesGrupales.get(grupo).agregarMensaje(grupo, "Yo: ", texto, true); //agregar un mensaje en la conver correspondiente a ese contacto
		}
	}
	
	public int cantidadMensajesRecibidosDelGrupo(String grupo) {
		
		return conversacionesGrupales.get(grupo).cantidadMensajesRecibidos();
	}
	
	public int cantidadMensajesEnviadosAlGrupo(String grupo) {
		return conversacionesGrupales.get(grupo).cantidadMensajesEnviados();
	}
	
	public ConversacionGrupal obtenerConversacionConGrupo(String grupo) {
		return conversacionesGrupales.get(grupo);
	}
	
	public String nombreUsuario() {
		return usuario.getNombre();
	}
	
}
