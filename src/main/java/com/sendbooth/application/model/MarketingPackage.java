package com.sendbooth.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MARKETING_PACKAGE")
public class MarketingPackage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MARKETING_PACKAGE_ID")
	private int marketingPackageid;

	@Column(name = "MARKETING_PACKAGE_NAME")
	private String marketingPackageName;

	@Column(name = "MARKETING_PACKAGE_PRICE")
	private Double marketingPackagePrice;

	public int getMarketingPackageid() {
		return marketingPackageid;
	}

	public void setMarketingPackageid(int marketingPackageid) {
		this.marketingPackageid = marketingPackageid;
	}

	public String getMarketingPackageName() {
		return marketingPackageName;
	}

	public void setMarketingPackageName(String marketingPackageName) {
		this.marketingPackageName = marketingPackageName;
	}

	public Double getMarketingPackagePrice() {
		return marketingPackagePrice;
	}

	public void setMarketingPackagePrice(Double marketingPackagePrice) {
		this.marketingPackagePrice = marketingPackagePrice;
	}

}
