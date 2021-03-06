package com.douzone.jblog.vo;

public class CategoryVO {
	private Long no;
	private String name;
	private String desc;
	private String blogId;
	private long postcount;
	
	public long getPostcount() {
		return postcount;
	}
	public void setPostcount(long postcount) {
		this.postcount = postcount;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	
	@Override
	public String toString() {
		return "CategoryVO [no=" + no + ", name=" + name + ", desc=" + desc + ", blogId=" + blogId + ", postcount="
				+ postcount + "]";
	}
	
	
	
	
}
