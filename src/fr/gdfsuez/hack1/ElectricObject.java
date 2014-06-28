package fr.gdfsuez.hack1;

/**
 * Objet à consommation electrique
 * 
 * @author remi
 * 
 */
public class ElectricObject implements Comparable<ElectricObject> {

	// Statuts
	static final public int STATUS_OFF = 0;
	static final public int STATUS_ON = 1;
	static final public int STATUS_SLEEP = 2;

	// Nom de l'objet
	private String name;

	// Consommation électrique
	private int theoricalconsumption;

	// Consommation réelle (watts)
	private int estimatedconsumption;

	// Status OFF / ON / Veille -> 0 = OFF / 1 = ON / 2 = VEILLE
	private int status;

	/**
	 * Constructeur
	 */
	public ElectricObject(String name, int estimatedconsumption, int realconsumption, boolean status) {
		this(name, estimatedconsumption, realconsumption, status ? STATUS_ON : STATUS_OFF);
	}

	/**
	 * Constructeur
	 */
	public ElectricObject(String name, int estimatedconsumption, int realconsumption, int status) {
		super();
		this.name = name;
		this.theoricalconsumption = estimatedconsumption;
		this.estimatedconsumption = realconsumption;
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
		return this.name + "  " + theoricalconsumption + "W " + estimatedconsumption + "W "
				+ getStatusString();
	}

	/* *** GETTERS AND SETTERS *** */

	public String getName() {
		return name;
	}

	public int getEstimatedConsumption() {
		return theoricalconsumption;
	}

	public boolean isOFF() {
		return status == STATUS_OFF;
	}

	public boolean isON() {
		return status == 1;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusString() {
		if (status <= STATUS_OFF) {
			return "OFF";
		} else if (status == STATUS_ON) {
			return "ON";
		}
		return "Veille";
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTheoricalConsumption() {
		return theoricalconsumption;
	}

	public void setTheoricalConsumption(int theoricalconsumption) {
		this.theoricalconsumption = theoricalconsumption;
	}

	public void setEstimatedConsumption(int estimatedconsumption) {
		this.estimatedconsumption = estimatedconsumption;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
