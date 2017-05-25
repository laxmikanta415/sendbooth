package com.sendbooth.application.service;

import java.util.List;

import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.SubscriberGroup;
import com.sendbooth.application.model.User;

public interface SubscriberService {

	public List<Subscriber> getSubscriberByUser(User user);

	public void saveSubscriber(Subscriber subscriber);

	public List<Subscriber> findSubscriberBySubscriberEmail(Subscriber subscriber);

	public void saveSubScriberList(List<Subscriber> subscribersInCsv);

	public Subscriber findSubscriberBySubscriberId(int parseInt);

	public List<Subscriber> findSubscriberNotInSubscriberGroup(SubscriberGroup subscriberGroup);

	public List<Subscriber> findSubscriberBySubscriberGroup(SubscriberGroup subscriberGroup);

}
