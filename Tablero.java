
public class Tablero {
	private String [][] tablero;
	//constructor del tablero
	public Tablero() {
		tablero = new String[3][3];
		tableroInicial();
	}
	// metodo que no devuelve valor ni requiere de parametros de entrada 
	//Introduce simbolo "_" en el array que compone el tablero para mostrarlo vacio
	private void tableroInicial() {
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				tablero [i][j] = "_";
			}
		}
	}
	//metodo que no devuelve valor ni requiere de parametros de entrada
	//imprime por paantalla el tablero
	public void imprimirTablero() {
		String letra = "A";
		System.out.println("  | 1 | 2 | 3 |");
		for (int i = 0; i < 3 ; i++) {
			if(i == 1) {
				letra = "B";
			} else if (i == 2) {
				letra = "C";
			}
			System.out.println(letra + " | " + tablero[i][0] + " | " + tablero[i][1] + " | " + tablero[i][2] + " |");
		}
	}
	//metodo que devuelve un int
	//parametros de entrada necesita 2 string las coordenadas y el tipo de coordenas a convertir
	//Este metodo sirve para traduccir el string de las coordenadas en int para poder utilizarse en el array
	//Ha de llamamrse 2 veces una para las columnas y otra para las filas
	private int coordenadasAInt(String coordenadas, String tipo){
		coordenadas = coordenadas.trim();
		int coordenada = 5;
		if (tipo.equals("columna")) {
			String coordenada2 = coordenadas.substring(1,2);
			coordenada = Integer.parseInt(coordenada2);
			coordenada--;
		} else {
			String coordenada1 = coordenadas.substring(0,1).toUpperCase();	
			switch (coordenada1) {
				case "A":
					coordenada  = 0;
					break;
				case "B":
					coordenada = 1;
					break;
				case "C":
					coordenada = 2;
					break;
			}
		}
		return coordenada;
	}
	//metodo que devuelve un boolean
	//Requiere de un parametro de entrada  String que son las coordenadas que queremos saber
	//Este metodo sirve para saber si la casilla ha sido ocupada o no
	public boolean comprobarCasilla(String coordenadas) {
		boolean correcto = true;
		if (!(tablero[coordenadasAInt(coordenadas, "fila")][coordenadasAInt(coordenadas, "columna")].equals("_"))) {
			correcto = false;
			System.out.println("La casilla ya esta ocupada");
		}
		return correcto;
	}
	//metedo que no devuelve valor
	//requiere de 2 strings 1 para las coordenadas y otro para el simbolo a introducir
	//este metodo sirve para introducir valores en el array que forma el tablero
	public void IntroducirValor(String coordenadas, String simbolo) {
		int fila = coordenadasAInt(coordenadas, "fila");
		int columna = coordenadasAInt(coordenadas, "columna");
		tablero[fila][columna] = simbolo;
	}
	//metodo que devuelve boolean
	//requiere de string como parametro de entrada
	//este metodo sirve para saber si un jugador ha ganado comprobando con su simbolo las casillas
	public boolean comprobarGanar(String simbolo) {
		boolean gana = false;
		// filas
		for (int i = 0; i < 3; i++) {
			int tres = 0;
			for(int j = 0; j < 3; j++) {
				if (tablero [i][j].equals(simbolo)) {
					tres++;
					if(tres == 3) {
						gana = true;
					}
				}
			}
		}
		//columnas
		if(!(gana)) {
			for (int i = 0; i < 3; i++) {
				int tres = 0;
				for(int j = 0; j < 3; j++) {
					if (tablero [j][i].equals(simbolo)) {
						tres++;
						if(tres == 3) {
							gana = true;
						}
					}
				}
			}
		}
		//diagonal principal
		if(!(gana)) {
			if(tablero[0][0].equals(simbolo) && tablero[1][1].equals(simbolo) && tablero[2][2].equals(simbolo)) {
				gana = true;
			}
		}
		//diagonal invertida
		if(!(gana)) {
			if(tablero[0][2].equals(simbolo) && tablero[1][1].equals(simbolo) && tablero[2][0].equals(simbolo)) {
				gana = true;
			}
		}
		return gana;
	}
}
