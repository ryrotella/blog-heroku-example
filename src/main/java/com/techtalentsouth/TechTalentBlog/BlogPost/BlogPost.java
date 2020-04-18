package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.techtalentsouth.TechTalentBlog.Tag.Tag;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BlogPost {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String author; 
	private String blogEntry;
	private String tagEntry;
	private String genre;
	
	@CreationTimestamp 
	private Date createdAt;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"),
	    inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;
	
	public BlogPost(String title, String author, String blogEntry, String tagEntry, String genre){
		this.title = title;
		this.author = author;
		this.blogEntry = blogEntry;
		this.tagEntry = tagEntry;
		this.genre = genre;
	}

//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(String author) {
//		this.author = author;
//	}
//
//	public String getBlogEntry() {
//		return blogEntry;
//	}
//
//	public void setBlogEntry(String blogEntry) {
//		this.blogEntry = blogEntry;
//	}
//	
//	public String getGenre() {
//		return genre;
//	}
//	
//	public void setGenre(String genre) {
//		this.genre = genre;
//	}
//	@Override
//	public String toString() {
//		return "BlogPost [id=" + id + ", title=" + title + ", author=" + author + ", blogEntry=" + blogEntry + ", genre=" + genre+ "]";
//	}
	
	

}
