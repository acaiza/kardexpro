package ec.com.ibs.kardexpro.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseModel {
	@PersistenceContext
    protected EntityManager ema;
}
