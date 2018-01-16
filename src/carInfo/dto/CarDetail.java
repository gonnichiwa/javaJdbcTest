package carInfo.dto;

public class CarDetail extends CarInfo{
	private int cdcc;
	private String cdEngineType;
	private int cdWheelInch;
	private String cdInputDate;
	
	public int getCdcc() {
		return cdcc;
	}
	public void setCdcc(int cdcc) {
		this.cdcc = cdcc;
	}
	public String getCdEngineType() {
		return cdEngineType;
	}
	public void setCdEngineType(String cdEngineType) {
		this.cdEngineType = cdEngineType;
	}
	public int getCdWheelInch() {
		return cdWheelInch;
	}
	public void setCdWheelInch(int cdWheelInch) {
		this.cdWheelInch = cdWheelInch;
	}
	public String getCdInputDate() {
		return cdInputDate;
	}
	public void setCdInputDate(String cdInputDate) {
		this.cdInputDate = cdInputDate;
	}
	
	@Override
	public String toString() {
		return super.getCiNum() + "\t"
				+ super.getCiName() + "\t"
				+ super.getCiMaker() + "\t"
				+ this.getCdcc() + "\t"
				+ this.getCdEngineType() + "\t"
				+ this.getCdWheelInch() + "\t"
				+ super.getCiPrice() + "\t"
				+ this.getCdInputDate();
	}
	
}
