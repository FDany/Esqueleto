package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.utils.DateUtils;

public class MysqlOradorRepository implements OradorRepository{


	public void save(Orador orador) {
		// 1 obtengo la conexion
		//Connection con = AdministradorDeConexiones.getConnection();
		
		// 2 preparo sql
		String sql = "insert into oradores (nombre, apellido, tema, mail, fecha_alta) values (?,?,?,?,?)";
		
		try( Connection con = AdministradorDeConexiones.getConnection() ) {
			PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1,orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getTema());
			statement.setString(4, orador.getMail());
			
			//statement.setDate(5, new java.sql.Date(System.currentTimeMillis() ));		// TAREA PASAR DE LOCALDATE A java.sql.Date
			
			//statement.setDate(5, java.sql.Date.valueOf(orador.getFechaAlta()) );
			statement.setDate(5, new java.sql.Date(DateUtils.asDate(orador.getFechaAlta()).getTime()));
			
			
			statement.executeUpdate(); // INSERT / UPDATE / DELETE
			
			ResultSet res = statement.getGeneratedKeys();
			
			if( res.next() ) {
				Long id = res.getLong(1);	// aca esta el id
				orador.setId(id);
			}
			
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo crear el orador: ",e);
		}
	}

	
	public Orador getById(Long id) {
		// 1 obtengo la conexion
		Connection con = AdministradorDeConexiones.getConnection();
		
		// 2 preparo sql
		String sql = "select id_orador, nombre, apellido, tema, mail, fecha_alta from oradores where id_orador = ?";
		
		Orador orador = null;
		
		try {
			PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setLong(1, id);
			
			ResultSet res = statement.executeQuery();  // SELECT
			
			if( res.next() ) {
				Long dbId = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String tema = res.getString(4);
				String email = res.getString(5);
				Date fechaAlta = res.getDate(6);
				
				/*
				SimpleDateFormat aux = new SimpleDateFormat("dd/MM/yyyy");
				aux.format(fechaAlta);
				System.out.println(aux);
				*/
				/*
				// CASTEO DE DATE->LOCALDATE
				LocalDate aux = fechaAlta.toLocalDate();
				
				orador = new Orador(dbId, nombre, apellido, email, tema, aux );
				*/
				orador = new Orador(dbId, nombre, apellido, email, tema,DateUtils.asLocalDate(fechaAlta));
			}
			
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo encontrar el orador: ",e);
		}
		return orador;
	}

	
	public void update(Orador orador) {
		
		//Connection con = AdministradorDeConexiones.getConnection();
		
		/*
		String sql = "update oradores set nombre='"+orador.getNombre()+
				"', apellido='"+orador.getApellido()+
				"', tema='"+orador.getTema()+
				"', mail='"+orador.getMail()+
				"' where id_orador="+orador.getId()+";";
		*/
		String sql = "update oradores set nombre=?, apellido=?, tema=?, mail=? where id_orador=?;";
		
		try( Connection con = AdministradorDeConexiones.getConnection() ) {
			PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getTema());
			statement.setString(4, orador.getMail());
			statement.setLong(5, orador.getId());
			
			statement.executeUpdate(); // INSERT / UPDATE / DELETE
			
			
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo modificar el orador: ",e);
		}
		
	}

	
	public void delete(Long id) {
		//Connection con = AdministradorDeConexiones.getConnection();
		/*
		String sql = "delete from oradores where id_orador="+id+";";
		
		try( Connection con = AdministradorDeConexiones.getConnection() ) {
			PreparedStatement statement = con.prepareStatement(sql);
			
			statement.executeUpdate(); //  DELETE
		*/
		String sql = "delete from oradores where id_orador = ?";
		
		//try with resources
		try(Connection con = AdministradorDeConexiones.getConnection()) {
			
			PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setLong(1, id);
			
			statement.executeUpdate();
		
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo modificar el orador: ",e);
		}
		
	}

	
	public List<Orador> findAll() {
		//Connection con = AdministradorDeConexiones.getConnection();
		
		String sql = "select * from oradores";
		
		List<Orador> lista = new ArrayList<>();
		
		try( Connection con = AdministradorDeConexiones.getConnection() ) {
			PreparedStatement statement = con.prepareStatement(sql);
			
			ResultSet res = statement.executeQuery();  // SELECT
			
			while( res.next() ) {
				Long dbId = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String email = res.getString(4);
				String tema = res.getString(5);
				
				
				LocalDate fechaAlta = DateUtils.asLocalDate(res.getDate(6));  
				/*
				Date fechaAlta = res.getDate(6);
				
				// CASTEO DE DATE->LOCALDATE
				LocalDate aux = fechaAlta.toLocalDate();
				
				Orador orador = new Orador(dbId, nombre, apellido, email, tema, aux );
				
				
				lista.add(orador);
				*/
				lista.add(new Orador(dbId, nombre, apellido, email, tema,fechaAlta));
			}
			
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo encontrar a los oradores: ",e);
		}
		
		return lista;
	}

	
}
