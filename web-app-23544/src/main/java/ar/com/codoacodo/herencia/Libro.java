package ar.com.codoacodo.herencia;

public class Libro extends Articulo {

	private String isbn;

	public Libro(String titulo, float precio, String img) {
		super(titulo, precio, img);		// primero debe nacer el padre
		
		// ahora instancio los atributos
		//this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return super.toString() + "Libro [isbn=" + isbn + "]";
	}
	
	
}
