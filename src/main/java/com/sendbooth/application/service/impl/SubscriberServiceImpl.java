package com.sendbooth.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.SubscriberGroup;
import com.sendbooth.application.model.User;
import com.sendbooth.application.repository.SubscriberRepository;
import com.sendbooth.application.repository.criteriabased.SubscriberCriteriaDao;
import com.sendbooth.application.service.SubscriberService;

@Service("subscriberService")
public class SubscriberServiceImpl implements SubscriberService {
	@Autowired
	private SubscriberRepository subscriberRepository;
	@Autowired
	private SubscriberCriteriaDao subscriberCriteriaDao;

	@Override
	public List<Subscriber> getSubscriberByUser(User user) {
		List<Subscriber> subscribersList = subscriberRepository.findByUser(user);
		return subscribersList;
	}

	@Override
	public void saveSubscriber(Subscriber subscriber) {
		try {

			subscriberRepository.save(subscriber);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Subscriber> findSubscriberBySubscriberEmail(Subscriber subscriber) {
		List<Subscriber> subscribersList = null;
		try {

			subscribersList = subscriberRepository.findSubscriberBySubscriberEmail(subscriber.getSubscriberEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribersList;
	}

	@Override
	public void saveSubScriberList(List<Subscriber> subscribersInCsv) {
		try {

			subscriberRepository.save(subscribersInCsv);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Subscriber findSubscriberBySubscriberId(int subscriberId) {

		Subscriber subscriber = null;
		try {

			subscriber = subscriberRepository.findSubscriberBySubscriberId(subscriberId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscriber;
	}

	@Override
	public List<Subscriber> findSubscriberNotInSubscriberGroup(SubscriberGroup subscriberGroup) {
		return subscriberCriteriaDao.findSubscriberNotInSubscriberGroup(subscriberGroup);
	}

	@Override
	public List<Subscriber> findSubscriberBySubscriberGroup(SubscriberGroup subscriberGroup) {
		List<Subscriber> subscriberListInCampaign = null;
		try {
			subscriberListInCampaign = subscriberRepository.findSubscriberBySubscriberGroup(subscriberGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscriberListInCampaign;
	}

}
