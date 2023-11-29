package ar.com.codoacodo.herencia;

public class Main {

	public static void main(String[] args) {
		Articulo a1 = new Articulo("titulo",150.5f,"http://url.com/bla1.jpg");
		
		a1.setAutor("autor a1");
		System.out.println(a1);
		
		
		Musica m1 = new Musica("m1",250.3f,"http://url.com/bla1.jpg");
		
		m1.setAutor("autor ,1");
		
		String [] temas = new String[] {"t1","t2","t3"};
		m1.setTemas(temas);
		
		System.out.println(m1);
		
		Articulo p1 = new Pelicula("peli 1", 858f, "bla.jpg");
		Pelicula peli = (Pelicula)p1;
		
		peli.setFormato("Blue ray 3D");
		
		peli.setAutor("autor peli");
		
		peli.setProductora("Warner");
		System.out.println("----------------------------");
		System.out.println(p1);
		
		
		Articulo[] resultados = new Articulo[3];
		
		resultados[0] = a1;
		resultados[1] = m1;
		resultados[2] = p1;
		
		
		for(Articulo aux : resultados) {
			System.out.println(aux);
		}
	}

}
