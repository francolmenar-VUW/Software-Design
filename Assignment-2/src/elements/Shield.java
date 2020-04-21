package elements;


public class Shield implements Symbol{
	String shape;

	public Shield(String shape) {
		setShape(shape);
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
}
