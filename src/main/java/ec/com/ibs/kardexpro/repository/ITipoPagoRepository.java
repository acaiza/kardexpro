package ec.com.ibs.kardexpro.repository;

import ec.com.ibs.kardexpro.model.TipoPagoEntity;

import java.util.List;

public interface ITipoPagoRepository {
    List<TipoPagoEntity> obtenerTiposDePago();
}
