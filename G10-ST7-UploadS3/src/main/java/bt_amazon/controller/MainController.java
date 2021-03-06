package bt_amazon.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bt_amazon.storage.StorageService;
import bt_amazon.model.Post;
import bt_amazon.service.PostService;
import bt_amazon.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

@Controller
public class MainController {

	@Autowired
	private PostService postService;
	
	private final StorageService storageService;
	
	@Autowired
	public MainController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "index";
	}

	@GetMapping("/all-post")
	public String allPost(HttpServletRequest request) {
		request.setAttribute("lstPost", postService.findAll());
		request.setAttribute("mode", "MODE_ALL");
		return "index";
	}

	@GetMapping("/add-post")
	public String addPost(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_ADD");
		return "index";
	}

	@PostMapping("/save-post")
	public String savePost(@RequestParam("file") MultipartFile file, @ModelAttribute Post post,
			BindingResult bindingResult, HttpServletRequest request) {
		try {
			if (file != null) {
				
				Path path = Paths.get(request.getRealPath("/")+"upload-dir"+File.separator+file.getOriginalFilename());
				String fileName = storageService.store(path,file);
				String pathFile = request.getRealPath("/")+"upload-dir"+ File.separator+fileName;

				UploadService service = new UploadService();
				String url = service.upload(pathFile);
				
				post.setFile(url);
			}
		} catch (Exception e) {
			System.out.println("Save file error: " + e);
			Post _postOld = postService.findPost(post.getId());
			if (_postOld != null) {
				post.setFile(_postOld.getFile());
			}
		}
		postService.save(post);
		request.setAttribute("lstPost", postService.findAll());
		request.setAttribute("mode", "MODE_ALL");
		return "redirect:/all-post";

	}
	
	@GetMapping("/update-post")
	public String updatePost(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("post", postService.findPost(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "index";
	}

	@GetMapping("/delete-post")
	public String deletePost(@RequestParam int id, HttpServletRequest request) {
		try
		{
			Post post = postService.findPost(id);
			if(post.getFile() != null && !post.getFile().isEmpty())
				(new UploadService()).deleteFile(post.getFile()); //Xóa file lưu trên server
		}
		catch (Exception e) {
		}
				
		postService.delete(id);
		request.setAttribute("lstPost", postService.findAll());
		request.setAttribute("mode", "MODE_ALL");
		return "redirect:/all-post";
	}
}
