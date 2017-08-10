package exceptions;


public class InvalidPieceException extends Exception{
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
