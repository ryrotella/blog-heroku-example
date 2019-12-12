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

@Controller
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	private static List<BlogPost> posts = new ArrayList<>();
	
	@GetMapping(value="/")
	public String index(BlogPost blogPost, Model model) {
		model.addAttribute("posts", posts);
		return "blogpost/index";
	}
	
	@GetMapping(value="/blog_posts/new")
	public String newBlog(BlogPost blogPost) {
		return "blogpost/new";
	}
	
	private BlogPost blogPost;
	
	@PostMapping(value="/blog_posts/new")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		BlogPost newPost = new BlogPost(
				blogPost.getTitle(),
				blogPost.getAuthor(),
				blogPost.getBlogEntry(),
				blogPost.getGenre()
				);
		
		blogPostRepository.save(newPost);
		posts.add(newPost);
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		model.addAttribute("genre", blogPost.getGenre());
		return "blogpost/result";
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
