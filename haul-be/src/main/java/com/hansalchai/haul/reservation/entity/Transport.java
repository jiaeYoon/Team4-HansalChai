package com.hansalchai.haul.reservation.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.hansalchai.haul.common.utils.BaseTime;
import com.hansalchai.haul.reservation.constants.TransportStatus;
import com.hansalchai.haul.reservation.constants.TransportType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SQLRestriction("is_deleted = FALSE")
@SQLDelete(sql = "UPDATE transport SET deleted_at = CURRENT_TIMESTAMP, is_deleted = TRUE where id = ?")
@Table(name = "transport")
public class Transport extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transportId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TransportType type;

	@PositiveOrZero(message = "운송 요금은 음수 일 수 없다.")
	@Column(nullable = false)
	private int fee;

	@PositiveOrZero(message = "운송이동시간은 음수 일 수 없다.")
	@Column(nullable = false)
	private double requiredTime;

	@Enumerated(EnumType.STRING)
	private TransportStatus transportStatus;

	@Builder
	public Transport(TransportType type, int fee, double requiredTime, TransportStatus transportStatus) {
		this.type = type;
		this.fee = fee;
		this.requiredTime = requiredTime;
		this.transportStatus = transportStatus;
	}

	public static Transport toEntity(TransportType type, int fee, double requiredTime,
		TransportStatus transportStatus) {
		return Transport.builder()
			.type(type)
			.fee(fee)
			.requiredTime(requiredTime)
			.transportStatus(transportStatus)
			.build();
	}

	public void changeStatusReserved() {
		this.transportStatus = TransportStatus.PENDING;
	}

	public void updateTransportStatus(TransportStatus transportStatus) {
		this.transportStatus = transportStatus;
	}
}

