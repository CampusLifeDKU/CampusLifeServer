package dto;

import java.sql.Timestamp;

public class PaperView {
	
	private String userCode;
	private String id;
	private String paperCode;
	private String lat;
	private String lng;
	private String region;
	private String title;
	private String content;
	private Timestamp p_time;
	
	public PaperView(){}

	public PaperView(String userCode, String id, String paperCode, String lat, String lng, String region, String title,
			String content, Timestamp p_time) {
		super();
		this.userCode = userCode;
		this.id = id;
		this.paperCode = paperCode;
		this.lat = lat;
		this.lng = lng;
		this.region = region;
		this.title = title;
		this.content = content;
		this.p_time = p_time;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaperCode() {
		return paperCode;
	}

	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getP_time() {
		return p_time;
	}

	public void setP_time(Timestamp p_time) {
		this.p_time = p_time;
	}

	@Override
	public String toString() {
		return "PaperView [userCode=" + userCode + ", id=" + id + ", paperCode=" + paperCode + ", lat=" + lat + ", lng="
				+ lng + ", region=" + region + ", title=" + title + ", content=" + content + ", p_time=" + p_time + "]";
	}
	
}
