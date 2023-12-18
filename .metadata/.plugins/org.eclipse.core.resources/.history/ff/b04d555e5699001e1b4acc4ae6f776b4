package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MysqlOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/orador")
public class NuevoOradorController extends HttpServlet{
	private OradorRepository repository = new MysqlOradorRepository();
	
	protected void doPost(
			HttpServletRequest request, // viene lo que manda el usuario
			HttpServletResponse response /* manda el backend al frontend*/) throws ServletException, IOException {
		
		String json = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));
		/*
		// capturo los parametros enviados por el front
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String mail = request.getParameter("mail");
		String tema = request.getParameter("tema");
		*/
		// Convierto de json String a Objeto Java usando libreria de jackson2
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		/*
		// creo el orador con sus parametros
		Orador nuevo = new Orador(nombre ,apellido, mail, tema, LocalDate.now() );
		
		// ahora por medio del repository guarda enla db
		OradorRepository repository = new MysqlOradorRepository();
		
		repository.save(nuevo);
		
		//ahora respondo al front
		response.getWriter().print("OK"); //JSON
		*/
		OradorRequest oradorRequest = mapper.readValue(json, OradorRequest.class);
		
		//creo mi orador con esos parametros
		Orador nuevo = new Orador(
				oradorRequest.getNombre(), 
				oradorRequest.getApellido(),
				oradorRequest.getEmail(),
				oradorRequest.getTema(),
				LocalDate.now()
		);
		
		//ahora por medio del repository guarda en la db
		//OradorRepository repository = new MysqlOradorRepository();
		
		this.repository.save(nuevo);
		
		//ahora respondo al front: json, Convirtiendo el nuevo Orador a json
		String jsonParaEnviarALFrontend = mapper.writeValueAsString(nuevo);
		
		response.getWriter().print(jsonParaEnviarALFrontend);
	}
	/*
	// Preflight
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin","*");
	    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD");
	    response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	}
	*/
	
	
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		//ahora por medio del repository guarda en la db
		//OradorRepository repository = new MysqlOradorRepository();
		List<Orador> listado = this.repository.findAll();
		
		//convierto Objecto java a json string
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);		
		
		//ahora respondo al front: json, Convirtiendo el nuevo Orador a json
		String jsonParaEnviarALFrontend = mapper.writeValueAsString(listado);
			
		response.getWriter().print(jsonParaEnviarALFrontend);
	}
	
	protected void doDelete(
		HttpServletRequest request, 
		HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		//OradorRepository repository = new MysqlOradorRepository();
		this.repository.delete(Long.parseLong(id));
		
		response.setStatus(HttpServletResponse.SC_OK);//200
		
	}
	
	protected void doPut(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		// ahora quiero los datos que vienen en el body del modal
		String json = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		OradorRequest oradorRequest = mapper.readValue(json, OradorRequest.class);
		
		
		// BUSCO EL ORADOR EN LA DB
		Orador orador = this.repository.getById(Long.parseLong(id));
		
		orador.setNombre(oradorRequest.getNombre());
		orador.setApellido(oradorRequest.getApellido());
		orador.setMail(oradorRequest.getEmail());
		orador.setTema(oradorRequest.getTema());
		
		// AHORA, ACTUALIZO EN LA DB!
		this.repository.update(orador);
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
