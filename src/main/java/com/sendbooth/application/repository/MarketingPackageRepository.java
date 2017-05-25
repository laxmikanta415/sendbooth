package com.sendbooth.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sendbooth.application.model.MarketingPackage;

@Repository("marketingPackageRepository")
public interface MarketingPackageRepository extends JpaRepository<MarketingPackage, Long> {
	 
}