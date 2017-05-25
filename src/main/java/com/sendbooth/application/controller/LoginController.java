package com.sendbooth.application.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.sendbooth.application.model.User;
import com.sendbooth.application.service.AmazonSESService;
import com.sendbooth.application.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	/*
	 * @Autowired private AmazonSESService amazonSESService;
	 * 
	 * @Autowired private AWSCredentials awsCredentials;
	 * 
	 * private static final String FROM = "hayssam.1988@gmail.com"; private
	 * static final String TO = "hayssam.1988@gmail.com"; private static final
	 * String BODY =
	 * "This is an email by java from Amazon Simple Email Service!"; private
	 * static final String SUBJECT = "A trying mail from Amazon SES by Java";
	 */

	/*
	 * @RequestMapping(value = { "/", "/registration" }, method =
	 * RequestMethod.GET) public ModelAndView login() { ModelAndView
	 * modelAndView = new ModelAndView();
	 * modelAndView.setViewName("registration"); return modelAndView; }
	 */

	@RequestMapping(value = { "/", "/registration" }, method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		/*
		 * // amazonSESService.sendMail();
		 * 
		 * //AmazonSimpleEmailService ses = new
		 * AmazonSimpleEmailServiceClient(awsCredentials); try {
		 * //amazonSESService.sendVerificationMail(ses, FROM, TO, SUBJECT,
		 * BODY); } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

}
