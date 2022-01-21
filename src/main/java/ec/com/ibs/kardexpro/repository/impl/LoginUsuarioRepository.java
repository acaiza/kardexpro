package ec.com.ibs.kardexpro.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import ec.com.ibs.kardexpro.model.*;
import ec.com.ibs.kardexpro.repository.ILoginUsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LoginUsuarioRepository extends BaseModel implements ILoginUsuarioRepository {

	@Override
	public UsuarioEntity obtenerUsuarioPorNombre(String nombreUsuario) {
		QUsuarioEntity qUsuarioEntity = QUsuarioEntity.usuarioEntity;
		JPAQuery<UsuarioEntity> query = new JPAQuery<>(ema);
		return query.from(qUsuarioEntity)
				.where(qUsuarioEntity.nombreUsuario.eq(nombreUsuario))
				.fetchOne();
	}

	@Override
	public List<UsuarioRolEntity> obtenerRolesUsuario(Integer userId) {
		QUsuarioRolEntity qUsuarioRolEntity = QUsuarioRolEntity.usuarioRolEntity;
		QRolEntity qRolEntity = QRolEntity.rolEntity;
		JPAQuery<UsuarioRolEntity> query = new JPAQuery<>(ema);
		return query.from(qUsuarioRolEntity)
				.innerJoin(qUsuarioRolEntity.rol, qRolEntity)
				.where(qUsuarioRolEntity.idUsuario.eq(userId),
						qUsuarioRolEntity.estado.eq(Boolean.TRUE))
				.select(Projections.bean(UsuarioRolEntity.class,
						qUsuarioRolEntity.idUsuarioRol,
						qUsuarioRolEntity.idUsuario,
						qUsuarioRolEntity.idRol,
						Projections.bean(RolEntity.class,
										qRolEntity.idRol, qRolEntity.nombreRol)
								.as(qUsuarioRolEntity.rol)))
				.fetch();
	}

}
