import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class Juego {
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	private int turno;
	private Random random;
	private Scanner scaner;
	//constructor de la clase juego
	public Juego() {
		tablero = new Tablero();
		jugador1 = new Jugador();
		jugador2 = new Jugador();
		turno = 1;
		random = new Random();
		scaner = new Scanner(System.in);
	}
	//metodo que no devuelve valor ni requiere de parametros de entrada
	//este metodo sirve para iniciar la partida creando los jugadores y decidiendo quien va primero
	public void inicio() throws IOException  {
		crearJugador(1);
		crearJugador(2);
		Log.crearLog("Los jugadores son " + jugador1.getNombre() + " y " + jugador2.getNombre());
		dadoDeInicio();
		partida();
	}
	//metodo que no devuelve valor
	//este metodo requiere un parametro de entrada int
	//este metodo sirve para crear un jugador
	private void crearJugador(int numero) {
		String otro = "";
		String nombre;
		if (numero == 2) {
			otro = "otro ";
		}
		System.out.println("Introduce el nombre del " + otro + "jugador");
		nombre = scaner.nextLine();
		if (numero == 1) {
			jugador1.setNombre(nombre);
		} else {
			jugador2.setNombre(nombre);
		}
	}
	//metodo que no devuelve valor ni requiere de parametros de entrada
	//este metodo decide quien empieza la partida tirando un dado
	//quien saque el numero mas alto empieza si ambos sacan el mismo numero se repite el lanzamiento
	private void dadoDeInicio() throws IOException {
		int j1;
		int j2;
		j1=  random.nextInt(6) + 1;
		System.out.println(jugador1.getNombre() + " ha sacado un"+ j1);
		Log.crearLog(jugador1.getNombre() + " ha sacado un "+ j1);
		j2=  random.nextInt(6) + 1;
		System.out.println(jugador2.getNombre() + " ha sacado un"+ j2);
		Log.crearLog(jugador2.getNombre() + " ha sacado un "+ j2);
		if(j1 > j2) {
			jugador1.setPrimero(true);
			jugador2.setPrimero(false);
			System.out.println("Empieza " +  jugador1.getNombre());
			Log.crearLog("Empieza " +  jugador1.getNombre());
		} else if (j2> j1) {
			jugador1.setPrimero(false);
			jugador2.setPrimero(true);
			System.out.println("Empieza " +  jugador2.getNombre());
			Log.crearLog("Empieza " +  jugador2.getNombre());
		} else {
			System.out.println("Ambos jugadores han sacado el mismo numero se repetirá la tirada");
			 dadoDeInicio();
		}	
	}
	//metodo devuelve boolean
	//este metodo requiere de un parametro de entrada string
	//este metodo comprueba que las coordenadas introducidas sean validas
	private boolean comprobarSintaxisCoordenada(String coordenadas) {
		boolean resultado = true;
		coordenadas = coordenadas.trim();
		coordenadas = coordenadas.toUpperCase();
		String coordenada1 = coordenadas.substring(0,1);
		String coordenada2 = coordenadas.substring(1,2);
		int coordenadaInt = Integer.parseInt(coordenada2);
		if (coordenadas.length() > 2) {
			resultado = false;
			System.out.println("Las coordenadas tienen solo 2 valores");
		} else if (!(coordenada1.equals("A") || coordenada1.equals("B") || coordenada1.equals("C"))) {
			resultado = false;
			System.out.println("La primera coordenada debe tener de valor una letra que se este en el tablero");
		} else if (!(coordenadaInt == 1 || coordenadaInt == 2 || coordenadaInt == 3 )) {
			resultado = false;
			System.out.println("La segunda coordenada debe tener el valor de un numero que se este en el tablero");
		}
		return resultado;
	}
	//este metodo devuelve un valor tipo Jugador
	//este metodo no requiere de parametros de entrada
	//este metodo decide de que jugador es el turno dependiendo de que turno sea y quien fue el primer jugador
	private Jugador turno() {
		Jugador jugador = jugador1;
		switch (turno % 2) {
			case 0: 
				if (jugador1.getPrimero()) {
					jugador = jugador2;
				} else {
					jugador = jugador1;
				}
				break;
			case 1:
				if(jugador1.getPrimero()) {
					jugador = jugador1;
				} else {
					jugador = jugador2;
				}
		}
		return jugador;
	}
	//este metodo no devuelve valor ni requiere de parametros de entrada
	//este metodo realiza la partida
	private void partida() throws IOException {
		boolean bucle = true;
		String coordenada;
		Jugador jugador = turno();
		System.out.println("Es el turno de "+ jugador.getNombre());
		tablero.imprimirTablero();
		System.out.println("Introduce las coordenadas");
		while(bucle) {
			coordenada = scaner.nextLine();
			if(comprobarSintaxisCoordenada(coordenada)) {
				if (tablero.comprobarCasilla(coordenada)) {
					tablero.IntroducirValor(coordenada, jugador.getSimbolo());
					bucle = false;
				}
			}
			if(bucle) {
				System.out.println("Introduzca la coordenada de nuevo");
			} else {
				Log.crearLog(jugador.getNombre() + " ha introducido " + jugador.getSimbolo() + " en la coordenada " + coordenada);
			}
		}
		
		if(tablero.comprobarGanar(jugador.getSimbolo())) {
			tablero.imprimirTablero();
			System.out.println("El jugador "+ jugador.getNombre() + " ha ganado");
			Log.crearLog("El jugador "+ jugador.getNombre() + " ha ganado");
		} else {
			if (turno == 9) {
				tablero.imprimirTablero();
				System.out.println("La partida ha acabado en empate");
				Log.crearLog("La partida ha acabado en empate");
			} else { 
				turno++;
				partida();
			}
		}
			
	}
}
