package cl.aiep.java.cft.repository;

import java.sql.ResultSet; //importacion
import java.sql.SQLException; //importacion
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate; //importacion de JdbcTemplate
import org.springframework.stereotype.Repository; //@Repository

import cl.aiep.java.cft.modelo.Alumno;
import cl.aiep.java.cft.modelo.Carrera;

@Repository //Se debe importar la anotacion de Repository
public class AlumnoRepositoryImp implements AlumnoRepository{

	@Autowired //con Autowired inyectamos la dependencia JdbcTemplate
	private JdbcTemplate jdbcTemplate; // se debe importar JdbcTemplate 
	
	@Autowired
	private CarreraRepository carreraRepository; 
	
	//Desde la interface de AlumnoRepository, estamos importando los metodos*********************************** 
	
	private Alumno makeObject(ResultSet rs, int filaNum) throws SQLException{ //se debe importar ResultSet y SQLException
		int id 						= rs.getInt("id");
		String nombre 				= rs.getString("nombre");
		LocalDate fechaNacimiento 	= rs.getObject("fecha_nacimiento", LocalDate.class);
		int carreraId	 			= rs.getInt("carrera_id");
		Carrera carrera = carreraRepository.findById(carreraId);
		return new Alumno(id, nombre, fechaNacimiento, carrera);
	}
	
	
	
	@Override
	public List<Alumno> findAll() {
		return jdbcTemplate.query("SELECT * FROM alumnos", this::makeObject);
	}

	@Override
	public Alumno findById(int id) {
		String sql = "SELECT * FROM alumnos WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, this::makeObject, id);
	}

	@Override
	public void create(Alumno alumno) {
		String sql = "INSERT INTO alumnos(nombre, fecha_nacimiento, carrera_id) VALUES(?,?,?)";
		jdbcTemplate.update(sql
				, alumno.getNombre()
				, alumno.getFechaNacimiento()
				, alumno.getCarrera().getId()
				);
	}

	
	//metodo create que se importa al inyectar 
	@Override
	public void edit(Alumno alumno) {
		String sql = "UPDATE alumnos SET nombre = ?, fecha_nacimiento = ?, carrera_id = ? WHERE id = ?";
		jdbcTemplate.update(
				sql,
				alumno.getNombre(),
				alumno.getFechaNacimiento(),
				alumno.getCarrera().getId(),
				alumno.getId()	
		);
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM alumnos WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

}
