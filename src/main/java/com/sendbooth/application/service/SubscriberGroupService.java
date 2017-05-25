package com.sendbooth.application.service;

import java.util.List;

import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.SubscriberGroup;
import com.sendbooth.application.model.User;

public interface SubscriberGroupService {

	public List<SubscriberGroup> getSubscriberGroupByUser(User user);

	public void saveSubscriberGroup(SubscriberGroup subscriberGroup);
	
	
}
