package ar.com.codoacodo.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.entity.Orador;

public class MainRepository {

	public static void main(String[] args) {

		OradorRepository repository = new MysqlOradorRepository();
		/*
		// obtener un orador desde la db
		Orador orador = repository.getById(3L);
		
		System.out.println(orador);
		*/
		
		/*
		Orador o = new Orador("Mariano", "Pages", "ejemplo@gmail.com","react",LocalDate.now());
		repository.save(o);
		
		System.out.println(o);
		*/
		
		/*
		Orador o = new Orador(12L,"Fabricio", "Pages", "pages@gmail.com","java",LocalDate.now());
		repository.update(o);
		*/
		
		//repository.delete(12L);
		
		
		List<Orador> aux = new ArrayList<>();
		aux=repository.findAll();
		
		for(Orador orador : aux) {
			System.out.println(orador);
		}
		
	}

}
