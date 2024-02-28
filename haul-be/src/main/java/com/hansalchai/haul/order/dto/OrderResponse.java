package com.hansalchai.haul.order.dto;

import static com.hansalchai.haul.common.utils.AddressUtil.*;
import static com.hansalchai.haul.common.utils.ReservationUtil.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.hansalchai.haul.reservation.constants.TransportStatus;
import com.hansalchai.haul.reservation.entity.Destination;
import com.hansalchai.haul.reservation.entity.Reservation;
import com.hansalchai.haul.reservation.entity.Source;
import com.hansalchai.haul.reservation.entity.Transport;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderResponse {

	@Getter
	public static class OrderDTO {
		List<OrderInfoDTO> orderInfoDTOS;
		boolean isLastPage;

		public OrderDTO(List<OrderInfoDTO> orderInfoDTOS, boolean isLastPage) {
			this.orderInfoDTOS = orderInfoDTOS;
			this.isLastPage = isLastPage;
		}

		@Getter
		public static class OrderInfoDTO {
			private final Long id;
			private final String src;
			private final String dst;
			private final String datetime;
			private final int cost;

			@Builder
			public OrderInfoDTO(Reservation reservation) {
				this.id = reservation.getReservationId();
				this.src = toSimpleAddress(reservation.getSource().getAddress());
				this.dst = toSimpleAddress(reservation.getDestination().getAddress());
				this.datetime = getDateTimeString(reservation.getDate(), reservation.getTime());
				this.cost = costCut(reservation.getTransport().getFee());
			}

			public int costCut(int fee) {
				if (fee < 10000)
					return 1;
				return fee / 10000;
			}

			public String getDateTimeString(LocalDate date, LocalTime time) {
				return date.toString() + " " + time.toString();
			}
		}
	}

	@Getter
	public static class OrderDetailDTO {
		private final UserDTO user;
		private final SourceDTO src;
		private final DestinationDTO dst;
		private final CargoDTO cargo;
		@NotNull(message = "비용은 Null 일 수 없다.")
		private final int cost;
		@NotNull(message = "걸리는시간은 Null 일 수 없다.")
		private final double requiredTime;
		@NotNull(message = "현재 상태는 Null 일 수 없다.")
		private final String transportStatus;
		@NotNull(message = "출발 날짜는 Null 일 수 없다.")
		private final String datetime;

		@Getter
		@Builder
		public static class UserDTO {
			private String name;
			private String tel;
		}

		@Getter
		@Builder
		public static class SourceDTO {
			@NotNull(message = "출발지 이름은 Null 일 수 없다.")
			private String name;
			@NotNull(message = "출발지 주소는 Null 일 수 없다.")
			private String address;
			@NotNull(message = "출발지 주소는 Null 일 수 없다.")
			private String detailAddress;
			@NotNull(message = "출발지 위도는 Null 일 수 없다.")
			private double latitude;
			@NotNull(message = "출발지 경도는 Null 일 수 없다.")
			private double longitude;
			@NotNull(message = "출발착지 번호는 Null 일 수 없다.")
			private String tel;
		}

		@Getter
		@Builder
		public static class DestinationDTO {
			@NotNull(message = "도착지 이름은 Null 일 수 없다.")
			private String name;
			@NotNull(message = "도착지 주소는 Null 일 수 없다.")
			private String address;
			@NotNull(message = "도착지 주소는 Null 일 수 없다.")
			private String detailAddress;
			@NotNull(message = "도착지 위도는 Null 일 수 없다.")
			private double latitude;
			@NotNull(message = "도착지 경도는 Null 일 수 없다.")
			private double longitude;
			@NotNull(message = "도착지 번호는 Null 일 수 없다.")
			private String tel;
		}

		@Getter
		@Builder
		public static class CargoDTO {
			@NotNull(message = "화물 가로는 Null 일 수 없다.")
			private int width;
			@NotNull(message = "화물 세로는 Null 일 수 없다.")
			private int length;
			@NotNull(message = "화물 높이는 Null 일 수 없다.")
			private int height;
			@NotNull(message = "화물 무게는 Null 일 수 없다.")
			private int weight;
		}

		@Builder
		public OrderDetailDTO(Reservation reservation) {
			this.user = UserDTO.builder()
				.name(reservation.getUser().getName())
				.tel(reservation.getUser().getTel())
				.build();
			this.src = SourceDTO.builder()
				.name(reservation.getSource().getName())
				.address(reservation.getSource().getAddress())
				.detailAddress(reservation.getSource().getDetailAddress())
				.latitude(reservation.getSource().getLatitude())
				.longitude(reservation.getSource().getLongitude())
				.tel(reservation.getSource().getTel())
				.build();
			this.dst = DestinationDTO.builder()
				.name(reservation.getDestination().getName())
				.address(reservation.getDestination().getAddress())
				.detailAddress(reservation.getDestination().getDetailAddress())
				.latitude(reservation.getDestination().getLatitude())
				.longitude(reservation.getDestination().getLongitude())
				.tel(reservation.getDestination().getTel())
				.build();
			this.cargo = CargoDTO.builder()
				.width(reservation.getCargo().getWidth())
				.length(reservation.getCargo().getLength())
				.height(reservation.getCargo().getHeight())
				.weight(reservation.getCargo().getWeight())
				.build();
			this.cost = costCut(reservation.getTransport().getFee());
			this.requiredTime = reservation.getTransport().getRequiredTime();
			this.transportStatus = TransportStatus.getCode(reservation.getTransport().getTransportStatus());
			this.datetime = getDateTimeString(reservation.getDate(), reservation.getTime());
		}

		public int costCut(int fee) {
			if (fee < 10000)
				return 1;
			return fee / 10000;
		}

		public String getDateTimeString(LocalDate date, LocalTime time) {
			return date.toString() + " " + time.toString();
		}
	}

	@Getter
	@NoArgsConstructor
	public static class OrderSearchResponseDto {

		private List<OrderSearchItem> orderSearchItems;
		private boolean isLastPage;

		public OrderSearchResponseDto(List<OrderSearchItem> orderSearchDtos, boolean isLastPage) {
			this.orderSearchItems = orderSearchDtos;
			this.isLastPage = isLastPage;
		}

		@Getter
		@NoArgsConstructor
		public static class OrderSearchItem {

			private Long id;
			private String srcSimpleAddress;
			private String dstSimpleAddress;
			private String transportDatetime;
			private int cost;

			public OrderSearchItem(Reservation reservation) {

				Source source = reservation.getSource();
				Destination destination = reservation.getDestination();
				Transport transport = reservation.getTransport();

				id = reservation.getReservationId();
				srcSimpleAddress = toSimpleAddress(source.getAddress());
				dstSimpleAddress = toSimpleAddress(destination.getAddress());
				transportDatetime = dateTimeToString(reservation.getDate(), reservation.getTime());
				cost = cutCost(transport.getFee());
			}
		}
	}

	/**
	 * 운송 상태 변경 응답 dto
	 * */
	@Getter
	public static class TransportStatusChangeResponseDto {

		private Long id; //오더 id

		public TransportStatusChangeResponseDto(Reservation reservation) {
			this.id = reservation.getReservationId();
		}
	}

	@Getter
	public static class TransportStatusChangeResponseDtoV2 {

		private boolean hasInProgressOrder;
		private boolean isDriverNearBy;

		@Builder
		public TransportStatusChangeResponseDtoV2(boolean hasInProgressOrder, boolean isDriverNearBy) {
			this.hasInProgressOrder = hasInProgressOrder;
			this.isDriverNearBy = isDriverNearBy;
		}

		public static TransportStatusChangeResponseDtoV2 ofInProgressOrderExist() {
			return TransportStatusChangeResponseDtoV2.builder()
				.hasInProgressOrder(true)
				.isDriverNearBy(false)
				.build();
		}

		public static TransportStatusChangeResponseDtoV2 ofRemoteLocation() {
			return TransportStatusChangeResponseDtoV2.builder()
				.hasInProgressOrder(false)
				.isDriverNearBy(false)
				.build();
		}

		public static TransportStatusChangeResponseDtoV2 ofStatusChangeAvailable() {
			return TransportStatusChangeResponseDtoV2.builder()
				.hasInProgressOrder(false)
				.isDriverNearBy(true)
				.build();
		}
	}
}
