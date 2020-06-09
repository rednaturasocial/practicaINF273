package EjerTCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServidorTCP {  
  public static final int PORT = 4444;
  /**
 * @param args
 * @throws IOException
 */
public static void main(String[] args) throws IOException {
    // Establece el puerto en el que escucha peticiones
    ServerSocket socketServidor = null;
    try {
      socketServidor = new ServerSocket(PORT);
    } catch (IOException e) {
      System.out.println("No puede escuchar en el puerto: " + PORT);
      System.exit(-1);
    }

    Socket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salida = null;

    System.out.println("Escuchando: " + socketServidor);
    try {
	    while(true) {
	      // abriendo un socket para el cliente.
	      socketCliente = socketServidor.accept();
	      System.out.println("Connexión acceptada: "+ socketCliente);
	      // Establece canal de entrada.
	      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	      // Establece canal de salida.
	      salida = new PrintWriter(new BufferedWriter(new 
		  OutputStreamWriter(socketCliente.getOutputStream())),true);
	      boolean sw=true;
	      // Hace eco de lo que le proporciona el cliente, hasta que recibe "Salir".
	      while (sw) {  
	        String mensaje = entrada.readLine();
	        String respuesta ="";
	        if(mensaje.equals("Salir")){
	        	sw=false;
	        }else {
	        	if(mensaje.equals("1")) respuesta="Papel";
	        	else {
	        		if(mensaje.equals("2")) respuesta="Piedra";
	        		else {
	        			if(mensaje.equals("3")) respuesta="Tijeras";
	        		}
	        	}
	        	System.out.println("Cliente dice: " + mensaje);
	        	salida.println(respuesta);
	        }
	      }
	    }
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }  
    salida.close();
    entrada.close();
    socketCliente.close();
    socketServidor.close();
  }
}
