package carInfo.dto;

public class CarPriceList {
	private int plNum;
	private String plName;
	private int plPrice;
	
	public int getPlNum() {
		return plNum;
	}
	public void setPlNum(int plNum) {
		this.plNum = plNum;
	}
	public String getPlName() {
		return plName;
	}
	public void setPlName(String plName) {
		this.plName = plName;
	}
	public int getPlPrice() {
		return plPrice;
	}
	public void setPlPrice(int plPrice) {
		this.plPrice = plPrice;
	}
	
	@Override
	public String toString() {
		return this.plNum + "\t" + this.plName + "\t" + this.plPrice;
	}
	
	
}
