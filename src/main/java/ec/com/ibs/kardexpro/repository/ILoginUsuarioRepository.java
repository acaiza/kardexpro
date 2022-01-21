package ec.com.ibs.kardexpro.repository;

import ec.com.ibs.kardexpro.model.UsuarioEntity;
import ec.com.ibs.kardexpro.model.UsuarioRolEntity;

import java.util.List;

/**
 * Clase para controlar la persistencia del login de usuario.
 * @author Andres
 *
 */

public interface ILoginUsuarioRepository {
	
	UsuarioEntity obtenerUsuarioPorNombre(String nombreUSuario);
	List<UsuarioRolEntity> obtenerRolesUsuario(Integer userId);
}
