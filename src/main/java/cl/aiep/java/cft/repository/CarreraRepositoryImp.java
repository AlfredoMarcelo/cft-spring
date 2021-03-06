package cl.aiep.java.cft.repository;

import java.sql.ResultSet; //importacion
import java.sql.SQLException; //importacion
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate; //importacion de JdbcTemplate
import org.springframework.stereotype.Repository; //@Repository

import cl.aiep.java.cft.modelo.Carrera;

@Repository //Se debe importar la anotacion de Repository
public class CarreraRepositoryImp implements CarreraRepository{

	@Autowired //con Autowired inyectamos la dependencia JdbcTemplate
	private JdbcTemplate jdbcTemplate; // se debe importar JdbcTemplate 
	
	

	//Preguntar que hace este metodo
	private Carrera makeObject(ResultSet rs, int filaNum) throws SQLException{ //se debe importar ResultSet y SQLException
		int id 						= rs.getInt("id");
		String nombre 				= rs.getString("nombre");
		String descripcion 			= rs.getString("descripcion");
		return new Carrera(id, nombre, descripcion);
	}
	
	//metodo para listar todas las carreras
	
	@Override
	public List<Carrera> findAll() {
		return jdbcTemplate.query("SELECT * FROM carreras", this::makeObject);
	}
	
	//metodo que lista una carrera por su id
	@Override
	public Carrera findById(int id) {
		String sql = "SELECT * FROM carreras WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, this::makeObject, id);
	}
	
	//Metodo para crear una nueva carrera
	@Override
	public void create(Carrera carrera) {
		String sql = "INSERT INTO carreras(nombre, descripcion) VALUES(?,?)";
		jdbcTemplate.update(sql, carrera.getNombre(), carrera.getDescripcion());
	}
	//Metodo para editar una carrera
	@Override
	public void edit(Carrera carrera) {
		String sql = "UPDATE carreras SET nombre = ?, descripcion = ? WHERE id = ?";
		jdbcTemplate.update(
				sql,
				carrera.getNombre(),
				carrera.getDescripcion(),
				carrera.getId()	
		);
		
	}

	//metodo para eliminar una carrera
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM carreras WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

}
