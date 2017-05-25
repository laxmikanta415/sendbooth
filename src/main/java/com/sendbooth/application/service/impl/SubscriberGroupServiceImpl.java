package com.sendbooth.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.SubscriberGroup;
import com.sendbooth.application.model.User;
import com.sendbooth.application.repository.SubscriberGroupRepository;
import com.sendbooth.application.service.SubscriberGroupService;

@Service("subscriberGroupService")
public class SubscriberGroupServiceImpl implements SubscriberGroupService {

	@Autowired
	private SubscriberGroupRepository subscriberGroupRepository;

	@Override
	public List<SubscriberGroup> getSubscriberGroupByUser(User user) {
		List<SubscriberGroup> subscriberGroupList = null;
		try {
			subscriberGroupList = subscriberGroupRepository.findSubscriberGroupByUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscriberGroupList;
	}

	@Override
	public void saveSubscriberGroup(SubscriberGroup subscriberGroup) {
		try {

			subscriberGroupRepository.save(subscriberGroup);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
