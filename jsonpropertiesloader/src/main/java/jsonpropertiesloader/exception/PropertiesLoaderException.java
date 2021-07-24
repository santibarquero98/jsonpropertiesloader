package jsonpropertiesloader.exception;

public class PropertiesLoaderException extends Exception {


	private static final long serialVersionUID = 1L;
	
	private Exception exception;
	
	public PropertiesLoaderException(Exception excepcion) {
		this.exception = excepcion;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	

}
