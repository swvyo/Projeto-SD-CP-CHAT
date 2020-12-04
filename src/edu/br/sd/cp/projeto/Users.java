package edu.br.sd.cp.projeto;


import java.net.Socket;
import java.util.HashMap;

//classe para armazenar e gerenciar os usuarios conectados
public class Users {
	private HashMap<Integer, Socket> list;

	public Users(HashMap<Integer, Socket> list) {
		this.list = list;
	}

	public Users() {
		list = new HashMap<Integer, Socket>();
	}

	public HashMap<Integer, Socket> getUsers() {
		return list;
	}

	// metodos para sincronização das threads dos usuarios

	public synchronized void addUser(int key, Socket s) {
		list.put(key, s);
	}

	public synchronized void removeUser(int key) {
		list.remove(key);
	}

	public synchronized int listSize() {
		return list.size();
	}
}