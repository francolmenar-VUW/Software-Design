package exceptions;


public class InvalidPieceException extends Exception{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public InvalidPieceException() {
		message = "Invalid object type of the piece";
	}

	public InvalidPieceException (String message){

		this.message = message;
	}

	public String getMessage(){

		return this.message;
	}
}
