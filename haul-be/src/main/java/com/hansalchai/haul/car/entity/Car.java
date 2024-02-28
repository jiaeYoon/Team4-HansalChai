package com.hansalchai.haul.car.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Range;

import com.hansalchai.haul.car.constants.CarCategory;
import com.hansalchai.haul.car.constants.CarType;
import com.hansalchai.haul.common.utils.BaseTime;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@SQLDelete(sql = "UPDATE car SET deleted_at = CURRENT_TIMESTAMP, is_deleted = TRUE where id = ?")
@Table(name = "car")
public class Car extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CarType type;

	@Column(nullable = false)
	private String model;

	@Nullable
	private String photo;

	@Range(min = 0, max = 10000, message = "자동차 가로는 100m를 넘을 수 없다.")
	@Column(nullable = false)
	private int width;

	@Range(min = 0, max = 10000, message = "자동차 세로는 100m를 넘을 수 없다.")
	@Column(nullable = false)
	private int length;

	@Range(min = 0, max = 10000, message = "자동차 높이는 100m를 넘을 수 없다.")
	@Column(nullable = false)
	private int height;

	@Range(min = 0, max = 50000, message = "자동차 허용 무게는 50T를 넘을 수 없다.")
	@Column(nullable = false)
	private int weight;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CarCategory category;

	@Column(nullable = false)
	private Boolean isboxtruck;

	@Builder
	public Car(Long carId, CarType type, String model, @Nullable String photo, int width, int length, int height,
		int weight,
		CarCategory category, Boolean isboxtruck) {
		this.carId = carId;
		this.type = type;
		this.model = model;
		this.photo = photo;
		this.width = width;
		this.length = length;
		this.height = height;
		this.weight = weight;
		this.category = category;
		this.isboxtruck = isboxtruck;
	}

	public Car(CarType carType, String model, String photo, int width, int legnth, int height, int weight,
		CarCategory carCategory, boolean b) {
		this.type = carType;
		this.model = model;
		this.photo = photo;
		this.width = width;
		this.length = legnth;
		this.height = height;
		this.weight = weight;
		this.category = carCategory;
		this.isboxtruck = b;
	}
}
