package com.douzone.jblog.vo;

public class PostVO {
	private Long no;
	private String title;
	private String contents;
	private String regDate;
	private Long categoryNo;
	Long getNo() {
		return no;
	}
	void setNo(Long no) {
		this.no = no;
	}
	String getTitle() {
		return title;
	}
	void setTitle(String title) {
		this.title = title;
	}
	String getContents() {
		return contents;
	}
	void setContents(String contents) {
		this.contents = contents;
	}
	String getRegDate() {
		return regDate;
	}
	void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	Long getCategoryNo() {
		return categoryNo;
	}
	void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	@Override
	public String toString() {
		return "postVO [no=" + no + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", categoryNo=" + categoryNo + "]";
	}
	
	
}
