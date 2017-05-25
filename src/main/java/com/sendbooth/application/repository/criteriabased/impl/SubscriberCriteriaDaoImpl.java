package com.sendbooth.application.repository.criteriabased.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.SubscriberGroup;
import com.sendbooth.application.repository.criteriabased.SubscriberCriteriaDao;

@Component("subscriberCriteriaDao")
public class SubscriberCriteriaDaoImpl implements SubscriberCriteriaDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Subscriber> findSubscriberNotInSubscriberGroup(SubscriberGroup subscriberGroup) {
		Criteria criteria = sessionFactory.openSession().createCriteria(Subscriber.class,"subscriber");
		criteria.createAlias("subscriber.subscriberGroup", "subscriberGroup")
				.add(Restrictions.ne("subscriberGroup.subscriberGroupId", subscriberGroup.getSubscriberGroupId()));
		
		
		/*ProjectionList p1=Projections.projectionList();
        p1.add(Projections.property("subscriberId"));
        p1.add(Projections.property("subscriberName"));	
        p1.add(Projections.property("subscriberEmail"));	
        criteria.setProjection(p1);*/
		List<Subscriber> list = criteria.list();
		for (Subscriber subscriber : list) {
			System.out.println(subscriber.getSubscriberEmail());
		}
		return list;
	}
}