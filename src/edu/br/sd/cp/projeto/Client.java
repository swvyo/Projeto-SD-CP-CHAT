package edu.br.sd.cp.projeto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	// Declaração das variaveis
	private static DataOutputStream ou;
	private static BufferedReader in;
	private static Socket s;
	private static String message, nome;
	private static boolean end;
	private static ClientThread cl;

	// metodo principal
	public static void main(String[] args) {
		initialize();// metodo para para iniciar e enviar as strings para o socket
		try {
			System.out.print("Insira seu nome: ");
			nome = in.readLine();
			cl = new ClientThread(s, nome);
			cl.start();
			ou.writeUTF(nome);
		} catch (IOException io1) {
			System.out.println("Erro ao adicionar nome");
		}

		// Loop para verificar erros de entrada incorreta
		while (!end) {
			try {
				message = in.readLine();
				ou.writeUTF(message);
				if (message.equalsIgnoreCase("sair")) {
					end = true;
				}
			} catch (IOException io2) {
				System.out.println("Um erro no loop da aplicação foi encontrado!");
			}
		}
	}

	// definição da porta e criação do socket cliente
	private static void initialize() {
		try {
			s = new Socket("localhost", 12345);
			ou = new DataOutputStream(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(System.in));
			end = false;
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}