package ru.vallball.forum02.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ru.vallball.forum02.model.Message;
import ru.vallball.forum02.model.Topic;
import ru.vallball.forum02.model.User;
import ru.vallball.forum02.service.MessageService;
import ru.vallball.forum02.service.TopicService;
import ru.vallball.forum02.service.UserService;

@Controller
@RequestMapping(value = { "/"})
public class HomeController {
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	@ModelAttribute("allTopics")
	public List<Topic> allTopics() {
		return topicService.list();
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@ModelAttribute("topic")
	public Topic newTopic() {
		return new Topic();
		
	}
	
	@ModelAttribute("message")
	public Message newMessage() {
		return new Message();
		
	}
	
	@GetMapping("/addTopic")
	public String addTopic() {
		return "addTopic";
	}
	
	@PostMapping("/addTopic")
    public String create(@ModelAttribute("topic") Topic topic, HttpServletRequest req,
    		@ModelAttribute("message") Message message){
		topic.setUser(userService.userByName(req.getRemoteUser()));
		topic.setDate(LocalDate.now());
		topic.setTime(LocalTime.now());
		topicService.save(topic);
		message.setUser(userService.userByName(req.getRemoteUser()));
		message.setDate(LocalDate.now());
		message.setTime(LocalTime.now());
		message.setTopic(topic);
		messageService.save(message);
		return "redirect:/";
	 }
	
	@GetMapping("/{id}")
	public String topic(@PathVariable("id") String id, ModelMap model,HttpServletRequest req) {
		model.addAttribute("topicMessages", messageService.list(id));
		model.addAttribute("theme", id);
		req.getSession().setAttribute("theme", id);
		req.getSession().setAttribute("id", id);
		return "topic";
	}
	
	
	
	@PostMapping("/addMessage")
    public String addMessage(HttpServletRequest req, ModelMap model,
    		@ModelAttribute("message") Message message){
		model.addAttribute("id", req.getSession().getAttribute("id"));
		message.setUser(userService.userByName(req.getRemoteUser()));
		message.setDate(LocalDate.now());
		message.setTime(LocalTime.now());
		message.setTopic(topicService.findTopicById((String)req.getSession().getAttribute("theme")));
		messageService.save(message);
		return "redirect:/{id}";
	 }
	
	@GetMapping("/addAvatar")
	public String addAvatar() {
		return "addAvatar";
	}
	@GetMapping("/fileUpload")
	 public String ileUpload() {
		return "index";
	}
	
	// Handling single file upload request
	   @PostMapping("/fileUpload")
	   public String singleFileUpload(@RequestParam("file") MultipartFile file, Model model,HttpServletRequest req)
	         throws IOException {
		    
		   
		   String fileName = file.getOriginalFilename();
		   Path path = Paths.get("C:\\Users\\val\\Desktop\\1\\java\\pictures\\"+fileName);
		   Path newName = path;
		   int i = 0;
		 while (newName.toFile().exists()) {
			  i++;
			   newName = Paths.get("C:\\Users\\val\\Desktop\\1\\java\\pictures\\"+
					   FilenameUtils.removeExtension(fileName)+i+"."+FilenameUtils.getExtension(fileName));
			   }
		   file.transferTo(newName);
		   User user = userService.userByName(req.getRemoteUser());
		   user.setPicture(newName.toString());
		   userService.save(user);
		   

	     

	      return "redirect:/";
	   }
    
}
