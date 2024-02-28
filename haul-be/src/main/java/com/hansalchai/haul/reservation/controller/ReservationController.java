package com.hansalchai.haul.reservation.controller;

import static com.hansalchai.haul.common.utils.ApiResponse.*;
import static com.hansalchai.haul.reservation.dto.ReservationRequest.*;
import static com.hansalchai.haul.reservation.dto.ReservationResponse.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hansalchai.haul.common.auth.anotation.LoggedInUser;
import com.hansalchai.haul.common.auth.dto.AuthenticatedUser;
import com.hansalchai.haul.common.utils.ApiResponse;
import com.hansalchai.haul.common.utils.SuccessCode;
import com.hansalchai.haul.reservation.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReservationController {
	private final ReservationService reservationService;
	private static final String V1_RESERVATIONS_PATH = "/api/v1/reservations";

	@PostMapping(V1_RESERVATIONS_PATH)
	public ResponseEntity<ApiResponse<ReservationRecommendationDTO>> postCustomerReservation(
		@LoggedInUser AuthenticatedUser authenticatedUser,
		@Valid @RequestBody CreateReservationDTO reservationDTO) {
		ReservationRecommendationDTO response
			= reservationService.createReservation(reservationDTO, authenticatedUser.getUserId());
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, response));
	}

	@PatchMapping(V1_RESERVATIONS_PATH + "/{id}")
	public ResponseEntity<ApiResponse<Object>> patchCustomerReservation(
		@LoggedInUser AuthenticatedUser authenticatedUser,
		@PathVariable("id") Long id) {
		reservationService.patchReservation(id, authenticatedUser.getUserId());
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, null));
	}

	@PostMapping(V1_RESERVATIONS_PATH + "/guest")
	public ResponseEntity<ApiResponse<ReservationRecommendationDTO>> postGuestReservation(
		@Valid @RequestBody CreateReservationGuestDTO reservationDTO) {
		ReservationRecommendationDTO response = reservationService.createGuestReservation(reservationDTO);
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, response));
	}

	@PatchMapping(V1_RESERVATIONS_PATH + "/guest/{id}")
	public ResponseEntity<ApiResponse<Object>> patchGuestReservation(
		@PathVariable("id") Long id) {
		reservationService.patchGuestReservation(id);
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, null));
	}

	@GetMapping(V1_RESERVATIONS_PATH)
	public ResponseEntity<ApiResponse<ReservationDTO>> getCustomerReservation(
		@LoggedInUser AuthenticatedUser authenticatedUser,
		@RequestParam(value = "keyword", defaultValue = "매칭 중") String keyword,
		@RequestParam(value = "page", defaultValue = "0") int page) {
		ReservationDTO response = reservationService.getReservation(keyword, page, authenticatedUser.getUserId());
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, response));
	}

	@GetMapping(V1_RESERVATIONS_PATH + "/guest")
	public ResponseEntity<ApiResponse<ReservationDTO>> getGuestReservation(
		@RequestParam(value = "number") String number) {
		ReservationDTO response = reservationService.getGuestReservation(number);
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, response));
	}

	@GetMapping(V1_RESERVATIONS_PATH + "/{id}")
	public ResponseEntity<ApiResponse<ReservationDetailDTO>> getCustomerReservationDetail(
		@LoggedInUser AuthenticatedUser authenticatedUser,
		@PathVariable("id") Long id) {
		ReservationDetailDTO response = reservationService.getReservationDetail(id, authenticatedUser.getUserId());
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, response));
	}

	@GetMapping(V1_RESERVATIONS_PATH + "/guest/{id}")
	public ResponseEntity<ApiResponse<ReservationDetailDTO>> getGuestReservationDetail(
		@PathVariable("id") Long id) {
		ReservationDetailDTO response = reservationService.getGuestReservationDetail(id);
		return ResponseEntity.ok(success(SuccessCode.GET_SUCCESS, response));
	}
}
