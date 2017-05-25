package com.sendbooth.application.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sendbooth.application.model.AWSDetails;
import com.sendbooth.application.model.EmailMarketingCampaign;
import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.SubscriberGroup;
import com.sendbooth.application.model.User;
import com.sendbooth.application.service.AWSDetailsService;
import com.sendbooth.application.service.EmailMarketingCampaignService;
import com.sendbooth.application.service.SubscriberGroupService;
import com.sendbooth.application.service.SubscriberService;
import com.sendbooth.application.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private SubscriberService subscriberService;

	@Autowired
	private SubscriberGroupService subscriberGroupService;

	@Autowired
	private AWSDetailsService awsDetailsService;

	@Autowired
	private EmailMarketingCampaignService emailMarketingCampaignService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Subscriber> subscriberList = subscriberService.getSubscriberByUser(user);
		List<SubscriberGroup> subscriberGroupList = subscriberGroupService.getSubscriberGroupByUser(user);
		int subscriberCount = null != subscriberList ? subscriberList.size() : 0;
		int subscriberGroupCount = null != subscriberGroupList ? subscriberGroupList.size() : 0;
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.addObject("user", user);
		modelAndView.addObject("subscriberCount", subscriberCount);
		modelAndView.addObject("subscriberGroupCount", subscriberGroupCount);
		AWSDetails awsDetails = awsDetailsService.findByUser(user);
		modelAndView.addObject("awsDetails", null != awsDetails ? awsDetails : new AWSDetails());
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping(value = "/saveAwsSettings", method = RequestMethod.POST)
	public ModelAndView saveAwsSettings(@Valid AWSDetails awsDetails, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		awsDetails.setUser(user);
		awsDetailsService.save(awsDetails);
		modelAndView.addObject("awsDetails", awsDetails);
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping(value = "/emailMarketingCampaign", method = RequestMethod.GET)
	public ModelAndView emailMarketingCampaign() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("user", user);
		List<SubscriberGroup> subscribersGroupList = subscriberGroupService.getSubscriberGroupByUser(user);
		List<EmailMarketingCampaign> emailMarketingCampaignList = emailMarketingCampaignService
				.findEmailMarketingCampaignByUser(user);
		modelAndView.addObject("emailMarketingCampaignList", emailMarketingCampaignList);
		modelAndView.addObject("subscribersGroupList", subscribersGroupList);
		modelAndView.setViewName("admin/emailMarketingCampaign");
		return modelAndView;
	}

	@RequestMapping(value = "/createEmailMarketingCampaign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createEmailMarketingCampaign(
			@RequestParam("emailMarketingCampaignName") String emailMarketingCampaignName,
			@RequestParam("subscriberGroupId") String subscriberGroupId) {
		System.out.println(subscriberGroupId);
		EmailMarketingCampaign emailMarketingCampaign = new EmailMarketingCampaign();
		emailMarketingCampaign.setEmailMarketingCampaignName(emailMarketingCampaignName);
		SubscriberGroup subscriberGroup = new SubscriberGroup();
		subscriberGroup.setSubscriberGroupId(Integer.parseInt(subscriberGroupId));
		emailMarketingCampaign.setSubscriberGroup(subscriberGroup);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		emailMarketingCampaign.setUser(user);
		emailMarketingCampaignService.saveEmailMarketingCampaign(emailMarketingCampaign);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@RequestMapping(value = "/startEmailMarketingCampaign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

	@ResponseBody
	public ResponseEntity<?> startEmailMarketingCampaign(
			@RequestParam("emailMarketingCampaignId") String emailMarketingCampaignId) {

		EmailMarketingCampaign emailMarketingCampaign = emailMarketingCampaignService
				.findEmailMarketingCampaignByEmailMarketingCampaignid(Integer.parseInt(emailMarketingCampaignId));
		List<Subscriber> subscriberList = subscriberService
				.findSubscriberBySubscriberGroup(emailMarketingCampaign.getSubscriberGroup());
		System.out.println(emailMarketingCampaign.getEmailMarketingCampaignName());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		emailMarketingCampaignService.startEmailMarketingCampaignService(subscriberList, user);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@RequestMapping(value = "/subscribersGroups", method = RequestMethod.GET)
	public ModelAndView subscribersGroups() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("user", user);
		SubscriberGroup subscriberGroup = new SubscriberGroup();
		List<SubscriberGroup> subscribersList = subscriberGroupService.getSubscriberGroupByUser(user);
		List<Subscriber> subscriberList = subscriberService.getSubscriberByUser(user);
		modelAndView.addObject("subscribersGroupList", subscribersList);
		modelAndView.addObject("subscriberList", subscriberList);
		modelAndView.addObject("SubscriberGroup", subscriberGroup);
		modelAndView.setViewName("admin/subscribersGroups");
		return modelAndView;
	}

	@RequestMapping(value = "/getSubscriberNotInSelectedGroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Subscriber>> getSubscriberNotInSelectedGroup(
			@RequestParam("subscriberGroupId") String subscriberGroupId) {
		System.out.println(subscriberGroupId);
		SubscriberGroup subscriberGroup = new SubscriberGroup();
		subscriberGroup.setSubscriberGroupId(Integer.parseInt(subscriberGroupId));
		List<Subscriber> subscriberList = subscriberService.findSubscriberNotInSubscriberGroup(subscriberGroup);
		return new ResponseEntity<List<Subscriber>>(subscriberList, HttpStatus.OK);

	}

	@RequestMapping(value = "/addSubscriberGroup", method = RequestMethod.POST)
	public ModelAndView addSubscriberGroup(@RequestParam("subscriber-group-name") String subscriberGroupName,
			@RequestParam("subscribersToAddInGroup") String[] subscribersToAddInGroup) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		SubscriberGroup subscriberGroup = new SubscriberGroup();
		subscriberGroup.setSubscriberGroupName(subscriberGroupName);
		subscriberGroup.setUser(user);
		subscriberGroupService.saveSubscriberGroup(subscriberGroup);
		for (String subscriberId : subscribersToAddInGroup) {
			Subscriber subscriber = subscriberService.findSubscriberBySubscriberId(Integer.parseInt(subscriberId));
			subscriber.setSubscriberGroup(subscriberGroup);
			subscriberService.saveSubscriber(subscriber);
		}
		List<SubscriberGroup> subscribersList = subscriberGroupService.getSubscriberGroupByUser(user);
		List<Subscriber> subscriberList = subscriberService.getSubscriberByUser(user);
		modelAndView.addObject("subscribersGroupList", subscribersList);
		modelAndView.addObject("subscriberList", subscriberList);
		modelAndView.addObject("SubscriberGroup", new SubscriberGroup());
		modelAndView.setViewName("admin/subscribersGroups");
		return modelAndView;
	}

	@RequestMapping(value = "/subscribers", method = RequestMethod.GET)
	public ModelAndView subscribers() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("user", user);
		Subscriber subscriber = new Subscriber();
		List<Subscriber> subscribersList = subscriberService.getSubscriberByUser(user);
		modelAndView.addObject("subscribersList", subscribersList);
		modelAndView.addObject("subscriber", subscriber);
		modelAndView.setViewName("admin/subscribers");
		return modelAndView;
	}

	@RequestMapping(value = "/addSubscribers", method = RequestMethod.POST)
	public ModelAndView addSubscribers(@Valid Subscriber subscriber, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		subscriber.setUser(user);

		List<Subscriber> subscriberList = subscriberService.findSubscriberBySubscriberEmail(subscriber);
		if (null == subscriberList || subscriberList.size() == 0) {
			subscriberService.saveSubscriber(subscriber);
			modelAndView.addObject("successMessage", "Subscriber added successfully");
		} else {
			modelAndView.addObject("successMessage", "Subscriber already exists");
		}
		List<Subscriber> subscribersList = subscriberService.getSubscriberByUser(user);
		modelAndView.addObject("subscribersList", subscribersList);
		modelAndView.addObject("subscriber", new Subscriber());
		modelAndView.setViewName("admin/subscribers");
		return modelAndView;
	}

	@RequestMapping(value = "/addSubscribersByCSV", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("subscribers-file") MultipartFile file,
			@RequestParam("subscriber-group-name") String subscriberGroupName, RedirectAttributes redirectAttributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		SubscriberGroup subscriberGroup = new SubscriberGroup();
		subscriberGroup.setSubscriberGroupName(subscriberGroupName);
		subscriberGroup.setUser(user);
		subscriberGroupService.saveSubscriberGroup(subscriberGroup);
		List<Subscriber> subscribersInCsv = new ArrayList<Subscriber>();
		try {
			byte[] byteArr = file.getBytes();
			InputStream inputStream = new ByteArrayInputStream(byteArr);
			InputStreamReader r = new InputStreamReader(inputStream);
			BufferedReader reader = new BufferedReader(r);
			List<String> lines = new ArrayList<>();
			String line = null;
			int count = 0;
			while ((line = reader.readLine()) != null) {

				if (count == 0) {
					// skipping the header of csv
				} else {
					lines.add(line);
					String[] columns = line.split(",");
					if (columns.length > 1 && null != columns[0] && null != columns[1]) {
						Subscriber subscriber = new Subscriber();
						subscriber.setSubscriberName(columns[1]);
						subscriber.setSubscriberEmail(columns[0]);
						subscriber.setUser(user);
						subscriber.setSubscriberGroup(subscriberGroup);
						subscribersInCsv.add(subscriber);

					}
				}
				count++;
			}
			if (!subscribersInCsv.isEmpty()) {
				subscriberService.saveSubScriberList(subscribersInCsv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/subscribers";
	}

}
