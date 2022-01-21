package ec.com.ibs.kardexpro.exception;

/**
 * Clase de tipo excepcion personalizada de la aplicacion.
 */
public class KardexproException extends RuntimeException{

    private static final long serialVersionUID = 2934393506783288257L;
    public KardexproException(){
        super();
    }

    /**
     * @param cause
     *            Causa del Error.
     */
    public KardexproException(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param mensaje
     *          Mensaje de error.
     */
    public KardexproException (String mensaje){
        super(mensaje);
    }

    /**
     * @param message
     *            Mensaje de error.
     * @param cause
     *            Causa del error.
     */
    public KardexproException(String message, Throwable cause) {
        super(message, cause);
    }

}
