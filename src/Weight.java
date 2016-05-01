package src;
pa

public enum Weight implements Unit{
	GRAM("Gram",1.0),
	MILLIGRAM("Milligrams",1000),
	KILOGRAMS("Kilograms",0.001),
	CARAT("Carats",5),
	HAP("Hap",60000),
	CHANG("Chang",1200);
	public final String name;
	public final double value;
	private Weight(String name , double value){
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