package com.sendbooth.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.SubscriberGroup;
import com.sendbooth.application.model.User;

@Repository("subscriberRepository")
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
	public List<Subscriber> findByUser(User user);

	public List<Subscriber> findSubscriberBySubscriberEmail(String subscriberEmail);

	public Subscriber findSubscriberBySubscriberId(int subscriberId);

	public List<Subscriber> findSubscriberBySubscriberGroup(SubscriberGroup subscriberGroup);
	
	
}