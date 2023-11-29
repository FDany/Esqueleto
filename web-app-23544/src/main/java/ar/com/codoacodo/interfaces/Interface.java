package ar.com.codoacodo.interfaces;

public interface Interface {
	// SOLO PODEMOS DEFINIR CONSTANTES, NO VARIABLES, QUE SON SOLO PUBLICOS
	final String hola = "";
	
	
	// METODOS
	
	public void metod1();	// definicion de metodo sin cuerpo
	
	public default void metodo2() {  // con default podemos colocar un cuerpo al metodo
		System.out.println("Metodo dos");
	}
}
