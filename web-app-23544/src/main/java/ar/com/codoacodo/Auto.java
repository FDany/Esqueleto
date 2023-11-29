package ar.com.codoacodo;

// esto viene en la jvm
import java.time.LocalDate;

public class Auto {
	/* atributos */
	String marca;
	String modelo;
	String color;
	Integer anio;
	Float velocidad;
	Float velocidadMaxima;
	Boolean encendido;
	String dominio;
	LocalDate fechaCreacion;
	
	/* Constructor */
	Auto(String marca, String modelo, Integer anio, String color, String dominio, Float velocidadMaxima){
		
		this.velocidad = 0f;
		this.encendido = Boolean.FALSE;
		this.fechaCreacion = LocalDate.now();
		
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.color = color;
		this.dominio = dominio;
		this.velocidadMaxima = velocidadMaxima;
		
	}
	
	/* metodos */
	public void encender() {
		if (!this.encendido){
			this.encendido = Boolean.TRUE;
		}else {
			System.out.println("Ya esta encendido.");
		}
	}
	public void apagar() {
		if (this.encendido) {
			this.encendido = Boolean.FALSE;
			this.velocidad = 0f;
		}else {
			System.out.println("Ya estaba apagado");
		}
	}
	public void acelerar() {
		if (this.encendido) {
			if (this.velocidad < this.velocidadMaxima) {
				velocidad++;
			}else {
				System.out.println("Velocidad Maxima"+this.velocidad+" alcanzada.");
			}
		}else {
			System.out.println("Primero debe encender el auto para acelerar.");
		}
	}
	public void frenar() {
		if(this.encendido) {
			if(this.velocidad>0) {
				this.velocidad--;
			}else {
				System.out.println("La velocidad llego a 0.");
			}
		}else {
			System.out.println("Frenando con el auto apagado");
		}
	}
	
}
