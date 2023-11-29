package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MysqlOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/orador/nuevo")
public class NuevoOradorController extends HttpServlet{
	
	
	protected void doGet(
			HttpServletRequest request, // viene lo que manda el usuario
			HttpServletResponse response /* manda el backend al frontend*/) throws ServletException, IOException {
		
		// capturo los parametros enviados por el front
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String mail = request.getParameter("mail");
		String tema = request.getParameter("tema");
		
		// creo el orador con sus parametros
		Orador nuevo = new Orador(nombre ,apellido, mail, tema, LocalDate.now() );
		
		// ahora por medio del repository guarda enla db
		OradorRepository repository = new MysqlOradorRepository();
		
		repository.save(nuevo);
		
		//ahora respondo al front
		response.getWriter().print("OK"); //JSON
		
	}
}