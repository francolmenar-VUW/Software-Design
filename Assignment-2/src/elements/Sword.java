package elements;


public class Sword implements Symbol{
		String shape;

		public Sword(String shape) {
			setShape(shape);
		}

		public String getShape() {
			return shape;
		}

		public void setShape(String shape) {
			this.shape = shape;
		}

}
