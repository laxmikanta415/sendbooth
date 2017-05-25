package com.sendbooth.application.repository.criteriabased;

import java.util.List;

import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.SubscriberGroup;

public interface SubscriberCriteriaDao {

	public List<Subscriber> findSubscriberNotInSubscriberGroup(SubscriberGroup subscriberGroup);
	
}
