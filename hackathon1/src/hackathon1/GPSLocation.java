package hackathon1;

public class GPSLocation {
	
	String id;
	Double x;
	Double y;
	
	public GPSLocation(String id, Double x,Double y) {
		this.id=id;
		this.x=x;
		this.y=y;
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	

}
