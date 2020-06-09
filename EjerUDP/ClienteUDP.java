package EjerUDP;

import java.net.*;
import java.io.*;

public class ClienteUDP {

  // Los argumentos proporcionan el mensaje y el nombre del servidor
  public static void main(String args[]) {
	  System.out.println("----CLIENTE----");
	  try {
		DatagramSocket socketUDP = new DatagramSocket();
		int puerto = 8888;
		InetAddress host = InetAddress.getByName("localhost");
		
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		String cad;
		while(true) {
			cad = sc.readLine();
			if(cad.equals("0"))break;
			byte[] mensaj = cad.getBytes();
			DatagramPacket peticion = new DatagramPacket(mensaj, cad.length(), host, puerto);
			socketUDP.send(peticion);
			
			byte[] buffer = new byte[10000];
			DatagramPacket mensaje = new DatagramPacket(buffer, buffer.length);
			socketUDP.receive(mensaje);
			System.out.println("la respuesta del server: "+new String(mensaje.getData()));
		}
		
	  } catch (Exception e) {
		
	  }
  }
}
