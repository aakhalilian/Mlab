package co.miraclelab.mainweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.miraclelab.webframe.controller.MainControl;
import co.miraclelab.webframe.layoutservice.LayoutService;
import co.miraclelab.webframe.model.User;
import co.miraclelab.webframe.model.UserInfo;
import co.miraclelab.webframe.utilities.LogService;
import co.miraclelab.webframe.utilities.AppProperties;
import co.miraclelab.webframe.utilities.Email;
import co.miraclelab.webframe.utilities.EmailService;
import co.miraclelab.webframe.utilities.EncryptService;
import co.miraclelab.webframe.utilities.ServiceAccessor;
import co.miraclelab.webframe.utilities.XMLService;

@Controller
public class Control extends MainControl {
	public Control(AppProperties properties, ServletContext servletContext, LogService logService,
			EncryptService encryptService, XMLService xmlService, EmailService mailService, MongoTemplate mongoTemplate,
			LayoutService layoutService, VelocityEngine templateEngine, HttpServletRequest request,
			HttpServletResponse response) {
		super(properties, servletContext, logService, encryptService, xmlService, mailService, mongoTemplate, layoutService,
				templateEngine, request, response);
		
		mailService.initMailService();
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET) 
	public String redirectToMain() throws IOException {
		return "redirect:/main";
	}
	
	@RequestMapping(value = { "/main" }, method = RequestMethod.GET)
	public String welcomePage(Model model) throws IOException {
		ServiceAccessor.modelDispatch(model);
		UserInfo userInfo=new UserInfo();
		userInfo.setEmail("a@q.com");
		User user=new User();
		user.setUsername("kentaki2");
		user.setUserInfo(userInfo);
		mongoTemplate.save(user);
		model.addAttribute("pageTitle","Miracle Lab Main"); 
		return "main";
		
	}
	
	@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
	public String aboutPage(Model model) throws IOException {
		ServiceAccessor.modelDispatch(model);
		model.addAttribute("pageTitle","About Miracle Lab"); 
		return "about";
	}
	
	@RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
	public String contactPage(Model model) throws IOException {
		ServiceAccessor.modelDispatch(model);
		model.addAttribute("pageTitle","About Miracle Lab"); 
		return "contact";
	}
	
	@RequestMapping(value = { "/contact" }, method = RequestMethod.POST)
	@ResponseBody
	public String contactPageResponse() throws IOException {
		Logger log=logService.getLog();
		try{
			Email email=new Email();
			ArrayList<String> rec=new ArrayList<String>();
			rec.add(request.getParameter("email"));
			email.setReciepients(rec);
			
			email.setSubject(request.getParameter("subject"));
			email.setTemplate("contactRespond.vm");
			VelocityContext context=new VelocityContext();
			context.put("message", request.getParameter("body"));
			context.put("email", request.getParameter("email"));
			
			email.setContext(context);
			email.renderEmail();
			
			mailService.sendMail(email);
			return "success";
		} catch (Throwable e) {
			log.error("error send email", e);
			return "error";
		}
	}
	
}
