package programa;

import java.util.Random;

public class Barbeiro implements Runnable {

	
	private int cadeiraDeEspera;//define qtas pessoas podem esperar atendimento
	boolean cadeiraOcupada=false;// true= ocupada false=vazia
	int [] clientes;//numero aleatório de clientes
	boolean barbeiroDormindo=false;// true = dorme false =acorda
	private String nome;//nome da thread criada
	private int cliNovo;//gera random até o máximo de clintes
	int numClientes=0;
	
	Barbeiro(String nome, int cadeiraDeEspera, int clientes) {
		cliNovo= clientes;
		this.nome= nome;
		this.cadeiraDeEspera = cadeiraDeEspera;
		System.out.println("O salão abriu");
				
	}
	
	public void Clientes() {
		Random rand = new Random();
		numClientes = rand.nextInt(cliNovo);
		clientes= new int[numClientes];
		
		for(int i = 0; numClientes<clientes.length; i++) {
			clientes[i] = i;
		}
	}
	
	
	private void BarbeiroDorme() throws InterruptedException {

		System.out.println("Não tem clientes");
		System.out.println("\nO barbeiro se aconchega na cadeira e dorme");
		Thread.sleep(3500);
		Clientes();
	}
	
	public void barbeiroAtende() throws InterruptedException {
		if(numClientes !=0 ) {//checa se existe cliente
			if(numClientes> 1 && cadeiraOcupada == false) {//se tem cliente a cadeira não está vazia
				
				System.out.println("\n"+numClientes +" novos entraram no salão");
			}else {//se tem cliente e a cadeira está ocupada
				System.out.println("Existem "+numClientes+" para serem atendidos");
			}
			
			//um cliente pode ser atendido
			
			System.out.println("\nUm cliente ocupou a cadeira do barbeiro "+nome+" e está sendo atendido");
			numClientes--;//decrementa clientes
			cadeiraOcupada=true;//cadeira está ocupada
			
			Thread.sleep(1500);//tempo do corte
			
			if(numClientes >cadeiraDeEspera) {//se o numero de clientes gerados é maior q o número de cadeiras disponíveis
				int cli = numClientes - cadeiraDeEspera;//verifica qtos tem  q ir embora
				numClientes = numClientes - cli;//atualiza qtos esperam
				
				//enquanto o contador for menor q o numero de clientes, o vetor é zerado
				for (int i = 0; i < clientes.length-1; i++) {
					clientes[i]=0;
				}
				//adiciona e atualiza o total de clientes
				for (int i = 0; i < numClientes; i++) {
					clientes[i]=i+1;
				}
				
				//mostra qtos clientes tiveram q ir embora
				System.out.println(cli+" clientes tiveram que ir embora");
				Thread.sleep(2000);
				System.out.println(numClientes+" estão aguardando");
			}
			System.out.println("#############################");
			System.out.println("\nCliente já foi atendido");
			
		}else if(numClientes==1) {
			System.out.println("O barbeiro foi acordado e agora irá trabalhar");
			Thread.sleep(1500);//tempo do corte
			numClientes--;
			System.out.println("\n\nTodos foram atendidos e "+nome+" repousa novamente");
		}else {
			//avisa q o barbeiro está dormindo
			System.out.println("O Barbeiro "+nome+" está dormindo");
			//seta a cadeira para vazia pelo cliente
			cadeiraOcupada=false;
			Thread.sleep(3500);
			
		}
		
	}
	

	
	@Override
	public void run() {//método que executa a thread
		
		while (true) {//fica verificando o programa
			if(numClientes<=0) {//se não tiver clientes
				try {
					BarbeiroDorme();//barbeiro dorme
				}catch(InterruptedException e) {
					System.out.println(e);
				}
			}else {
				try {
					barbeiroAtende();//barbeiro atende
					}catch(InterruptedException e) {
						System.out.println(e);
					}
				}
			}
			
		}
}
	
