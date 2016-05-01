package src;

public enum Volume implements Unit{
	LITER("Liter",1.0),
	MILLILITER("Milliliter",1000),
	CUBICMETER("Cubic Meters",0.001),
	CUP("Cup",4.226753),
	PINTS("Pints",2.113376),
	THANG("Thang",20.0);
	public final String name;
	public final double value;
	private Volume(String name , double value){
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