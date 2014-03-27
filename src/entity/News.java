package entity;

public class News {

	private int id;
	private String titleStr;
	private int timeLength;
	
	public News() {}
	
	public News(int id, String titleStr, int timeLength) {
		this.id = id;
		this.titleStr = titleStr;
		this.timeLength = timeLength;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitleStr() {
		return titleStr;
	}

	public void setTitleStr(String titleStr) {
		this.titleStr = titleStr;
	}

	public int getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}
	

}
