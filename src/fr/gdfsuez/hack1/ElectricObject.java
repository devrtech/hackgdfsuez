package fr.gdfsuez.hack1;

/**
 * Objet à consommation electrique
 * 
 * @author remi
 * 
 */
public class ElectricObject implements Comparable<ElectricObject> {

	// Nom de l'objet
	private String name;

	// Consommation électrique
	private int estimatedconsumption;

	// Consommation réelle (watts)
	private int realconsumption;

	// Status OFF / ON / Veille -> 0 = OFF / 1 = ON / 2 = VEILLE
	private int status;

	/**
	 * Constructeur
	 */
	public ElectricObject(String name, int estimatedconsumption, int realconsumption, boolean status) {
		this(name, estimatedconsumption, realconsumption, status ? 1 : 0);
	}

	/**
	 * Constructeur
	 */
	public ElectricObject(String name, int estimatedconsumption, int realconsumption, int status) {
		super();
		this.name = name;
		this.estimatedconsumption = estimatedconsumption;
		this.realconsumption = realconsumption;
		this.status = status;
	}

	@Override
	public int compareTo(ElectricObject another) {
		if (another == null || another.name == null) {
			return 1;
		}
		return this.name.compareTo(another.name);
	}

	@Override
	public String toString() {
		return this.name + "  " + estimatedconsumption + "W " + realconsumption + "W "
				+ (status == 0 ? "OFF" : (status == 1 ? "ON" : "Veille"));
	}

	/* *** GETTERS AND SETTERS *** */

	public String getName() {
		return name;
	}

	public int getEstimatedconsumption() {
		return estimatedconsumption;
	}

	public int getRealconsumption() {
		return realconsumption;
	}

	public boolean isOFF() {
		return status == 0;
	}

	public boolean isON() {
		return status == 1;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEstimatedconsumption(int estimatedconsumption) {
		this.estimatedconsumption = estimatedconsumption;
	}

	public void setRealconsumption(int realconsumption) {
		this.realconsumption = realconsumption;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
