package com.project.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;;

import com.project.beans.*;
import com.project.repos.*;

@Entity
@Table(name = "images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long imageid;
	
	@JoinColumn (name ="userid")
	private long creatorid;
	
	@Column(nullable  = false)
	private String src = null;
	
	@Column()
	private String author = "N/A";

//	Constructors
	public Image(long creatorid, String src, String author) {
			super();
			this.creatorid = creatorid;
			this.src = src;
			this.author = author;
		}
//	Getters and Setters
	public long getImageid() {
		return imageid;
	}

	public void setImageid(long imageid) {
		this.imageid = imageid;
	}

	public long getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(long creatorid) {
		this.creatorid = creatorid;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

//	ToString
	@Override
	public String toString() {
		return "Image [src=" + src + ", author=" + author + "]";
	}
	
	
	

	
}
