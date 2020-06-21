package beans.form;


/**
 * Clase que utilizo para retornar es estado de una operacion 
 * que afectar al aun Formulario.
 * 
 * @author @kalua66
 *
 */
public class FormStatus 
{
	/**
	 * Si la operacion fue correcta.
	 */
	private boolean success;
	
	/**
	 * Recojo el error aqui en caso de que lo hubiera.
	 */
	private String error; /*-?|dolivera3-review|carlosl.sanchez|c1|?*/
	
	/**
	 *  Constructor de la clase sin parametrtos, significara, que todo fue OK y no registro errores.
	 */
	public FormStatus() 
	{
		this.success = true;
		this.error = null;
	}
	
	/**
	 * Constructor de la clase pasandole si la operacion fue con exito o no.
	 * @param success Estado de la operacion.
	 */
	public FormStatus(boolean success) 
	{
		this.success = success;
		this.error = null;
	}
	
	/**
	 * Constructor de la clase pasandole directamente un string con el error, con lo cual asumimos que el estado de la operacion no fue bueno.
	 * @param error El error
	 */
	public FormStatus(String error) 
	{
		this.success = false;
		this.error = error;
	}

	/**
	 * @return El error.
	 */
	public String getError() 
	{
		return error;
	}

	/**
	 * @param error Establezco el error.
	 */
	public void setError(String error) 
	{
		this.error = error;
	}

	/**
	 * @return Si la operacion fue correcta.
	 */
	public boolean isSuccess() 
	{
		return success;
	}
}