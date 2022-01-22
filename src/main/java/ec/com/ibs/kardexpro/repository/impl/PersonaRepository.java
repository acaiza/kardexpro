package ec.com.ibs.kardexpro.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import ec.com.ibs.kardexpro.model.BaseModel;
import ec.com.ibs.kardexpro.model.PersonaEntity;
import ec.com.ibs.kardexpro.model.QPersonaEntity;
import ec.com.ibs.kardexpro.model.QUsuarioEntity;
import ec.com.ibs.kardexpro.repository.IPersonaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepository extends BaseModel implements IPersonaRepository {

    @Override
    public void registrarPersona(PersonaEntity persona) {
        ema.persist(persona);
    }

    @Override
    public Integer obtenerIdPersonaPorNombreUsuario(String nombreUsuario) {
        QPersonaEntity qPersonaEntity = QPersonaEntity.personaEntity;
        QUsuarioEntity qUsuarioEntity = QUsuarioEntity.usuarioEntity;
        JPAQuery<Integer> query = new JPAQuery<>(ema);
        return query.from(qPersonaEntity)
                .innerJoin(qPersonaEntity.usuario, qUsuarioEntity)
                .where(qUsuarioEntity.nombreUsuario.eq(nombreUsuario))
                .select(qPersonaEntity.idPersona)
                .fetchOne();
    }
}
