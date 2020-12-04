package edu.br.sd.cp.projeto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

//Criador de threads para os ervidor seguindo a lista de usuarios conectados
public class ServerThread extends Thread {
	private Socket s;
	private int id;
	private DataInputStream in;
	private DataOutputStream ou;
	private Users lista;
	private boolean sair;
	private String message, nome;

	public ServerThread(Socket s, Users list, int id) {
		this.s = s;
		this.id = id;
		this.lista = list;
	}

	public ServerThread() {

	}

	// metodo para executar, receber e verificar entradas do cliente
	public void run() {
		try {
			in = new DataInputStream(s.getInputStream());
			ou = new DataOutputStream(s.getOutputStream());
			sair = false;
			nome = in.readUTF();
			sendMessage(nome + " Está conectado!");
		} catch (IOException io1) {
			System.out.println("Erro na thread do servidor");
		}
		while (!sair) {
			try {
				message = in.readUTF();
				if (message.equalsIgnoreCase("exit")) {
					sair = true;
					sendMessage("- " + nome + " desconectado.");
					removeClient();
				} else {
					sendMessage("- " + nome + " diz: " + message);
				}

			} catch (IOException io2) {
				System.out.println("Erro na thread do servidor");
				io2.printStackTrace();
			}
		}
	}

	private void sendMessage(String message) {
		try {
			Iterator it = lista.getUsers().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				ou = new DataOutputStream(((Socket) pair.getValue()).getOutputStream());
				ou.writeUTF(message);
			}
		} catch (IOException io) {
			System.out.println("Erro ao enviar mensagem");
		}
	}

	private void removeClient() {
		lista.removeUser(id);
	}
}