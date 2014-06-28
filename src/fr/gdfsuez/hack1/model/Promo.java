package fr.gdfsuez.hack1.model;

import java.io.Serializable;

public class Promo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1754965172368876909L;
	
	private String	deviceName;
	private String	brand;
	private String	model;
	private String	devicePrice;
	private String	imgUrl;
	private int		nbBought;
	private int		type;
	private String	energyLabel;
	private double	powerComsumption;
	
	
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDevicePrice() {
		return devicePrice;
	}
	public void setDevicePrice(String devicePrice) {
		this.devicePrice = devicePrice;
	}
	public int getNbBought() {
		return nbBought;
	}
	public void setNbBought(int nbBought) {
		this.nbBought = nbBought;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getEnergyLabel() {
		return energyLabel;
	}
	public void setEnergyLabel(String energyLabel) {
		this.energyLabel = energyLabel;
	}
	public double getPowerComsumption() {
		return powerComsumption;
	}
	public void setPowerComsumption(double powerComsumption) {
		this.powerComsumption = powerComsumption;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
