package Pruebas;
import Modelo.Chat6toTIC;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Chat6toTICTest {

    @Test
    public void test01NuevoClienteChat() {

        Chat6toTIC chat6to = new Chat6toTIC("Dario");

        assertEquals(0, chat6to.cantidadDeChatsIndividuales());
        assertEquals(0, chat6to.cantidadDeContactos());
        assertEquals(0, chat6to.cantidadDeChatsGrupales());
        assertEquals(0, chat6to.cantidadDeGrupos());
        assertEquals(0, chat6to.cantidadDeMensajesEnviados());
        assertEquals("Dario", chat6to.nombreUsuario());
    }

    @Test
    public void test02AgregarContactos() {

        Chat6toTIC chat6to = new Chat6toTIC("Dario");

        chat6to.agregarContacto("Ruben");
        chat6to.agregarContacto("Andy");
        chat6to.agregarContacto("DaniVena");
        chat6to.agregarContacto("Roberto");

        assertEquals(4, chat6to.cantidadDeContactos());
        assertTrue(chat6to.existeContacto("Andy"));
    }
    
    
    @Test
    public void test03RecibirMensaje() {

        Chat6toTIC chat6to = new Chat6toTIC("Dario");

        chat6to.agregarContacto("Ruben");
        chat6to.recibirMensajeDe("Ruben", "Estas en ORT?");

        assertEquals(1, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(1, chat6to.cantidadMensajesDe("Ruben"));

        chat6to.agregarContacto("Andy");
        chat6to.recibirMensajeDe("Andy", "Tengo 6toB en el primer bloque");
        chat6to.recibirMensajeDe("Andy", "Avisame si vas a estar");

        assertEquals(3, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(1, chat6to.cantidadMensajesDe("Ruben"));
        assertEquals(2, chat6to.cantidadMensajesDe("Andy"));
    }
    
    
    @Test
    public void test04Conversar() {

        Chat6toTIC chat6to = new Chat6toTIC("Dario");

        chat6to.agregarContacto("Ruben");
        chat6to.recibirMensajeDe("Ruben", "Estas en ORT?");
        chat6to.enviarMensajeA("Ruben", "Hoy no.");

        assertEquals(1, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(1, chat6to.cantidadMensajesDe("Ruben"));
        assertEquals(1, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(1, chat6to.cantidadMensajesEnviadosA("Ruben"));

        chat6to.agregarContacto("Andy");
        chat6to.recibirMensajeDe("Andy", "Llego antes, a las 7");
        chat6to.enviarMensajeA("Andy", "OK");
        chat6to.recibirMensajeDe("Andy", "Avisame si vas a estar");
        chat6to.enviarMensajeA("Andy", "Seguro");

        assertEquals(3, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(2, chat6to.cantidadMensajesDe("Andy"));
        assertEquals(3, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(2, chat6to.cantidadMensajesEnviadosA("Andy"));

        assertEquals("Yo: OK", chat6to.obtenerConversacionCon("Andy").get(1));
        assertEquals("Andy: Avisame si vas a estar", chat6to.obtenerConversacionCon("Andy").get(2));
        assertEquals("Yo: Seguro", chat6to.obtenerConversacionCon("Andy").get(3));
    }
    
    @Test
    public void test05ArmarGrupo() {

        Chat6toTIC chat6to = new Chat6toTIC("Dario");

        chat6to.agregarContacto("Ruben");
        chat6to.agregarContacto("Andy");
        chat6to.agregarContacto("DaniVena");
        chat6to.agregarContacto("Roberto");

        chat6to.crearGrupo("6toA");
        chat6to.agregarContactoAGrupo("Ruben", "6toA");
        chat6to.agregarContactoAGrupo("DaniVena", "6toA");

        assertEquals(3, chat6to.cantidadMiembrosEnGrupo("6toA"));
        assertTrue(chat6to.existeGrupo("6toA"));

        chat6to.crearGrupo("Todos");
        chat6to.agregarContactoAGrupo("Ruben", "Todos");
        chat6to.agregarContactoAGrupo("DaniVena", "Todos");
        chat6to.agregarContactoAGrupo("Andy", "Todos");
        chat6to.agregarContactoAGrupo("Roberto", "Todos");

        assertEquals(5, chat6to.cantidadMiembrosEnGrupo("Todos"));
        assertTrue(chat6to.existeGrupo("Todos"));
    }

    @Test
    public void test06ConversarEnGrupo() {

        Chat6toTIC chat6to = new Chat6toTIC("Dario");

        chat6to.agregarContacto("Ruben");
        chat6to.agregarContacto("Andy");
        chat6to.agregarContacto("DaniVena");
        chat6to.agregarContacto("Roberto");

        chat6to.crearGrupo("6toC");
        chat6to.agregarContactoAGrupo("Ruben", "6toC");
        chat6to.agregarContactoAGrupo("DaniVena", "6toC");

        chat6to.crearGrupo("Todos");
        chat6to.agregarContactoAGrupo("Ruben", "Todos");
        chat6to.agregarContactoAGrupo("DaniVena", "Todos");
        chat6to.agregarContactoAGrupo("Andy", "Todos");
        chat6to.agregarContactoAGrupo("Roberto", "Todos");

        chat6to.recibirMensajeDeGrupo("6toC", "Ruben", "Nos juntamos el lunes?");
        chat6to.enviarMensajeAGrupo("6toC", "Yo puedo");
        chat6to.recibirMensajeDeGrupo("6toC", "DaniVena", "Yo tambien");
        chat6to.enviarMensajeAGrupo("6toC", "bien, yo llevo las medialunas...");

        assertEquals(2, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(1, chat6to.cantidadMensajesDe("Ruben"));
        assertEquals(2, chat6to.cantidadMensajesRecibidosDelGrupo("6toC"));
        assertEquals(2, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(2, chat6to.cantidadMensajesEnviadosAlGrupo("6toC"));

        assertEquals("Yo: bien, yo llevo las medialunas...", chat6to.obtenerConversacionConGrupo("6toC").get(1));
        assertEquals("DaniVena: Yo tambien", chat6to.obtenerConversacionConGrupo("6toC").get(2));
        assertEquals("Ruben: Nos juntamos el lunes?", chat6to.obtenerConversacionConGrupo("6toC").get(4));

        chat6to.recibirMensajeDeGrupo("Todos", "Ruben", "Alguien aviso a Matias?");
        chat6to.enviarMensajeAGrupo("Todos", "Yo no");
        chat6to.recibirMensajeDeGrupo("Todos", "Andy", "Yo tampoco");
        chat6to.recibirMensajeDeGrupo("Todos", "Roberto", "Ni yo");
        chat6to.enviarMensajeAGrupo("Todos", "sonamos...");

        assertEquals(5, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(2, chat6to.cantidadMensajesDe("Ruben"));
        assertEquals(3, chat6to.cantidadMensajesRecibidosDelGrupo("Todos"));
        assertEquals(4, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(2, chat6to.cantidadMensajesEnviadosAlGrupo("6toC"));

        assertEquals("Yo: sonamos...", chat6to.obtenerConversacionConGrupo("Todos").get(1));
        assertEquals("Roberto: Ni yo", chat6to.obtenerConversacionConGrupo("Todos").get(2));
        assertEquals("Andy: Yo tampoco", chat6to.obtenerConversacionConGrupo("Todos").get(3));

    }

    @Test
    public void test07OperarSobreConversaciones() {

        Chat6toTIC chat6to = new Chat6toTIC("Dario");

        chat6to.agregarContacto("Ruben");
        chat6to.recibirMensajeDe("Ruben", "Estas en tu casa?");
        chat6to.enviarMensajeA("Ruben", "hoy no");

        assertEquals(1, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(1, chat6to.cantidadMensajesDe("Ruben"));
        assertEquals(1, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(1, chat6to.cantidadMensajesEnviadosA("Ruben"));

        chat6to.agregarContacto("Andy");
        chat6to.recibirMensajeDe("Andy", "Llego a las 8");
        chat6to.enviarMensajeA("Andy", "OK");
        chat6to.recibirMensajeDe("Andy", "Los chicos ya saben que tienen que hacer igual");
        chat6to.enviarMensajeA("Andy", "Buenisimo");

        assertEquals(3, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(2, chat6to.cantidadMensajesDe("Andy"));
        assertEquals(3, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(2, chat6to.cantidadMensajesEnviadosA("Andy"));

        chat6to.borrarMensajesDelContacto("Ruben");

        assertEquals(2, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(2, chat6to.cantidadMensajesDe("Andy"));
        assertEquals(0, chat6to.cantidadMensajesDe("Ruben"));
        assertEquals(2, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(2, chat6to.cantidadMensajesEnviadosA("Andy"));
        assertEquals(0, chat6to.cantidadMensajesEnviadosA("Ruben"));
    }
    

    @Test
    public void test08OperarSobreGrupos() {

        Chat6toTIC chat6to = new Chat6toTIC("Dario");

        chat6to.agregarContacto("Ruben");
        chat6to.agregarContacto("Andy");
        chat6to.agregarContacto("DaniVena");
        chat6to.agregarContacto("Roberto");

        chat6to.crearGrupo("6tos");
        chat6to.agregarContactoAGrupo("Ruben", "6tos");
        chat6to.agregarContactoAGrupo("DaniVena", "6tos");

        chat6to.crearGrupo("Todos");
        chat6to.agregarContactoAGrupo("Ruben", "Todos");
        chat6to.agregarContactoAGrupo("DaniVena", "Todos");
        chat6to.agregarContactoAGrupo("Andy", "Todos");
        chat6to.agregarContactoAGrupo("Roberto", "Todos");

        chat6to.recibirMensajeDeGrupo("6tos", "Ruben", "Nos juntamos el lunes?");
        chat6to.enviarMensajeAGrupo("6tos", "Yo puedo");
        chat6to.recibirMensajeDeGrupo("6tos", "DaniVena", "Me too");
        chat6to.enviarMensajeAGrupo("6tos", "bien, yo llevo las medialunas...");

        assertEquals(2, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(1, chat6to.cantidadMensajesDe("Ruben"));
        assertEquals(2, chat6to.cantidadMensajesRecibidosDelGrupo("6tos"));
        assertEquals(2, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(2, chat6to.cantidadMensajesEnviadosAlGrupo("6tos"));

        chat6to.recibirMensajeDeGrupo("Todos", "Ruben", "Alguien aviso a Matias?");
        chat6to.enviarMensajeAGrupo("Todos", "Yo no");
        chat6to.recibirMensajeDeGrupo("Todos", "Andy", "Yo tampoco");
        chat6to.recibirMensajeDeGrupo("Todos", "Roberto", "Ni yo");
        chat6to.enviarMensajeAGrupo("Todos", "sonamos...");

        assertEquals(5, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(2, chat6to.cantidadMensajesDe("Ruben"));
        assertEquals(3, chat6to.cantidadMensajesRecibidosDelGrupo("Todos"));
        assertEquals(4, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(2, chat6to.cantidadMensajesEnviadosAlGrupo("6tos"));

        chat6to.borrarMensajesDelGrupo("Todos");

        assertEquals(2, chat6to.cantidadTotalMensajesRecibidos());
        assertEquals(1, chat6to.cantidadMensajesDe("Ruben"));
        assertEquals(0, chat6to.cantidadMensajesRecibidosDelGrupo("Todos"));
        assertEquals(2, chat6to.cantidadTotalMensajesEnviados());
        assertEquals(2, chat6to.cantidadMensajesEnviadosAlGrupo("6tos"));
    }

}
