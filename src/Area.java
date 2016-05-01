package src;

public enum Area implements Unit{
	SQUAREMETER("Square Meters",1.0),
	SQUAREKILOMETER("Square Kilometers",0.000001),
	SQUARECENTIMETER("Square Centimeters",10000),
	SQUAREMILE("Square Miles",0.00000038610216),
	SQUAREFOOT("Square Feet",10.76391),
	SQUAREINCH("Square Inches",1550.003),
	SQUAREWA("Square Wa",0.25);
	public final String name;
	public final double value;
	private Area(String name , double value){
		this.name = name;
		this.value = value;
	}
	@Override
	public double getValue(){
		return value;
	}
	@Override
	public String toString(){
		return name;
	}
}