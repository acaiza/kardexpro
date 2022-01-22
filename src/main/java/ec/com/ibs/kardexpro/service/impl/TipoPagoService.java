package ec.com.ibs.kardexpro.service.impl;

import ec.com.ibs.kardexpro.model.TipoPagoEntity;
import ec.com.ibs.kardexpro.repository.ITipoPagoRepository;
import ec.com.ibs.kardexpro.service.ITipoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagoService implements ITipoPagoService {

    @Autowired
    private ITipoPagoRepository tipoPagoRepository;

    @Override
    public List<TipoPagoEntity> obtenerTiposDePago() {
        return tipoPagoRepository.obtenerTiposDePago();
    }
}
