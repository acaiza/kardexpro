package ec.com.ibs.kardexpro.repository;

import ec.com.ibs.kardexpro.model.PersonaEntity;

public interface IPersonaRepository {

    void registrarPersona(PersonaEntity persona);

    Integer obtenerIdPersonaPorNombreUsuario(String nombreUsuario);
}
