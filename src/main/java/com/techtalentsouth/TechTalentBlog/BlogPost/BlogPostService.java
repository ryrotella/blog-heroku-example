package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtalentsouth.TechTalentBlog.Tag.*;

@Service
public class BlogPostService {
	
	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
//	protected void handleTags(BlogPost blogPost) {
//        List<Tag> tags = new ArrayList<Tag>(); 
//        String[] toBeTags = blogPost.getTagEntry().split(",");
//        
//       for (String phrase : toBeTags) {
//    	   Tag tag = tagRepository.findByPhrase(phrase);
//    	   if (tag == null) {
//    		   tag = new Tag();
//    		   tag.setPhrase(phrase);
//    		   tagRepository.save(tag);
//    	   }
//    	   tags.add(tag);
//       }
//        	
//        blogPost.setTags(tags);
//    }
	
//	public List<BlogPost> findAllWithTag(String tag){
//	    List<BlogPost> blogPosts = blogPostRepository.findByTags_PhraseOrderByCreatedAtDesc(tag);
//	    return blogPosts;
//	}
	
	

}
