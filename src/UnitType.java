package src;
public enum UnitType {
	LENGTH("Lenght", Length.values()),
	AREA("Area", Area.values()),
	WEIGHT("Weight", Weight.values()),
	VOLUME("Volume" , Volume.values());
	public final String name;
	public final Unit[] unitArr;
	private UnitType(String name , Unit[] unitArr){
		this.name = name;
		this.unitArr = unitArr;
	}
	public Unit[] getUnit(){
		return unitArr;
	}
	@Override
	public String toString(){
		return name;
	}
}
