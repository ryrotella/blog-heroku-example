package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techtalentsouth.TechTalentBlog.Tag.*;

@Controller
public class BlogPostController {
	
	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	private static List<BlogPost> posts = new ArrayList<>();
	
	private BlogPostService blogPostService;
	
	@GetMapping(value="/")
	public String index(BlogPost blogPost, Model model) {
		model.addAttribute("posts", posts);
		return "blogpost/index";
	}
	
	@GetMapping(value="/blog_posts/new")
	public String newBlog(BlogPost blogPost) {
		return "blogpost/new";
	}
	
	@GetMapping(value = "/blog_posts/{tag}")
	public String getPostsByTag(@PathVariable(value="tag") String tag, Model model) {
	    List<BlogPost> blogPosts = findAllWithTag(tag);
	    model.addAttribute("postList", blogPosts);
	    model.addAttribute("tag", tag);
	    return "blogpost/taggedPosts";
	}
	
	public List<BlogPost> findAllWithTag(String tag){
	    List<BlogPost> blogPosts = blogPostRepository.findByTags_PhraseOrderByCreatedAtDesc(tag);
	    return blogPosts;
	}
	
	private BlogPost blogPost;
	
	@PostMapping(value="/blog_posts/new")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		BlogPost newPost = new BlogPost(
				blogPost.getTitle(),
				blogPost.getAuthor(),
				blogPost.getBlogEntry(),
				blogPost.getTagEntry(),
				blogPost.getGenre() 
				);
		
		handleTags(newPost);
		blogPostRepository.save(newPost);
		posts.add(newPost);
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		model.addAttribute("tagEntry", blogPost.getTagEntry());
		model.addAttribute("genre", blogPost.getGenre());
		return "blogpost/result";
	}
	
	private void handleTags(BlogPost blogPost) {
        List<Tag> tags = new ArrayList<Tag>(); 
        String[] toBeTags = blogPost.getTagEntry().split(",");
        
       for (String phrase : toBeTags) {
    	   Tag tag = tagRepository.findByPhrase(phrase);
    	   if (tag == null) {
    		   tag = new Tag();
    		   tag.setPhrase(phrase);
    		   tagRepository.save(tag);
    	   }
    	   tags.add(tag);
       }
        	
        blogPost.setTags(tags);
    }
	
	
	/*
	@PostMapping(value= "/blog_posts/edit/{id}")
	public String edit(@PathVariable Long id, BlogPost blogPost) {
		BlogPost editPost = (BlogPost) blogPostRepository.findById(id);
		System.out.println(blogPost.toString());
		return "blogpost/index";
	}
	
    @RequestMapping(value = "/blog_posts/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id,
                                   BlogPost blogPost) {

        blogPostRepository.deleteById(id);
        return "blogpost/index";

    }
    */
	

}
