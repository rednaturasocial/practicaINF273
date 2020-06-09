package EjerTCP;

import java.net.*;
import java.io.*;

public class ClienteTCP {
	  public static void main(String[] args)  throws IOException {
	    Socket socketCliente = null;
	    BufferedReader entrada = null;
	    PrintWriter salida = null;

	    // Creamos un socket en el lado cliente, enlazado con un
	    // servidor que está en la misma máquina que el cliente
	    // y que escucha en el puerto 4444
	    try {
	      socketCliente = new Socket("localhost", 4444);
	      // Obtenemos el canal de entrada
	      
	      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	      
	      // Obtenemos el canal de salida
	      salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
	    } catch (IOException e) {
		System.err.println("No puede establer canales de E/S para la conexión");
	        System.exit(-1);
	    }
	    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	    String linea;
	    
	    try {
	    	boolean sw = true;
	    	while (sw) {
		    	System.out.println("Menu \n opcion 1 \n opcion 2 \n opcion 3 \n Salir");
		    	System.out.print("Opcion: ");
		        linea = stdIn.readLine();
		        
		        salida.println(linea);
		        
		        if (linea.equals("Salir")) sw = false ;
		        else {
		        
		        linea = entrada.readLine();
		        
		        System.out.println("Respuesta servidor: " + linea);
		        // Si es "Adios" es que finaliza la comunicación
	        }
	        
	      }
	    } catch (IOException e) {
		System.out.println("IOException: " + e.getMessage());
	    }
	 
	    // Libera recursos
	    salida.close();
	    entrada.close();
	    stdIn.close();
	    socketCliente.close();
	  }
	}
