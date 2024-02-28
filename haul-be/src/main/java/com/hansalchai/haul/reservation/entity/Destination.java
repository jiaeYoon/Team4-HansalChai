package com.hansalchai.haul.reservation.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.hansalchai.haul.common.utils.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SQLRestriction("is_deleted = FALSE")
@SQLDelete(sql = "UPDATE destination SET deleted_at = CURRENT_TIMESTAMP, is_deleted = TRUE where id = ?")
@Table(name = "destination")
public class Destination extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long destinationId;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String address;

	@Column(length = 100, nullable = false)
	private String detailAddress;

	@Column(nullable = false)
	private double latitude;

	@Column(nullable = false)
	private double longitude;

	@Column(length = 15, nullable = false)
	private String tel;

	@Builder
	public Destination(String name, String address, String detailAddress, double latitude,
		double longitude, String tel) {
		this.name = name;
		this.address = address;
		this.detailAddress = detailAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tel = tel;
	}
}
