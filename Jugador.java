
public class Jugador {
	private String nombre;
	private boolean primero;
	private String simbolo;
	//constructor de la clase jugador
	public Jugador() {
		primero = false;
	}
	//este metodo no devuelve valor
	//Requiere un parametro de entrada un string
	//este metodo sirve para cambiar el contenido del atributo nombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//metodo que no requiere parametros
	//devuelve string
	//este metodo te devuelve el valor del atributo nombre
	public String getNombre() {
		return nombre;
	}
	//metodo que no devuelve valor
	//requiere de un parametro boolean
	//este parametro sirve para cambiar el valor del atributo primero
	public void setPrimero(boolean primero) {
		this.primero = primero;
		setSimbolo(primero);
	}
	//metodo que devuelve boolean
	//este metodo no requiere de parametros de entrada
	//este metodo devuelve el valor del atributo primero
	public boolean getPrimero() {
		return primero;
	}	
	//metodo que no requiere parametros
	//devuelve string
	//este metodo te devuelve el valor del atributo simbolo
	public String getSimbolo() {
		return simbolo;
	}
	//metodo que no devuelve valor
	//requiere de un parametro boolean
	//este parametro sirve para cambiar el valor del atributo simbolo segun el valor del boolean a introducir
	private void setSimbolo(boolean primero) {
		if(primero) {
			simbolo = "X";
		} else {
			simbolo = "O";
		}
	}
	
	
}
