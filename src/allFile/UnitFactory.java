package allFile;
/**
 * 
 * @author Nuttapatprom CHongamorkulprapa
 * 
 *	object UnitFactory
 */
public class UnitFactory {
	private static UnitFactory unitFactory = null;
	private UnitType[] unitType;
	
	
	private UnitFactory(){
		this.unitType = UnitType.values();
	}
	public static UnitFactory getInstance(){
		if(unitFactory == null) unitFactory = new UnitFactory();
		return unitFactory;
	}
	public UnitType[] getUnitTypes(){
		return unitType;
	}
	
}
