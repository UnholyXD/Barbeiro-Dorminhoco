 package programa;

public class Barbearia {
	public static void main(String[] args) {
		Barbeiro barb = new Barbeiro("Pedro", 4, 20);//cria um barbeiro, qtas cadeiras existem e qtos clientes podem ter
		
		Thread threadBarb = new Thread(barb);
		
		threadBarb.start();
	}
}