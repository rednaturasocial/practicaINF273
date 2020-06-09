package EjerUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {


	public static void main(String[] args) {
		System.out.println("----SERVIDOR----");
		try {
			DatagramSocket socketUDP = new DatagramSocket(8888);
			
			
			while(true) {
				byte[] buffer = new byte[10000];
				DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(peticion);
				
				//Divide un String en partes mediante espacios
				String cad =(new String(peticion.getData()));
				String men=contarPalabras(cad)+" palabras.";
				
				byte[] mensaj = men.getBytes();
				DatagramPacket mensaje = new DatagramPacket(mensaj, men.length(), peticion.getAddress(), peticion.getPort());
				socketUDP.send(mensaje);
				System.out.println("Datos: "+new String(peticion.getData()));
			}
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
	}
	
	public static int contarPalabras(String cad) {
		int cont=0;
		
		String[] vec=cad.split(" ");
		
		int b=vec.length;
		cad = cad.substring(0,b);
		vec=cad.split(" ");
		for(int i=0; i<b; i++) {
			System.out.println("["+i+"]: "+"*"+vec[i]+"*");
			
			if(vec[i].compareTo("")!=0 && vec[i].compareTo(" ")!=0) 
				cont++;
		}
		
		return cont;
	}


}
