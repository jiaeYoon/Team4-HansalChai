package com.hansalchai.haul.common.utils.KaKaoMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hansalchai.haul.common.utils.MapUtils.DistanceDurationInfo;
import com.hansalchai.haul.common.utils.MapUtils.Location;

@Component
public class KakaoMap {
	private final KakaoMapAPI kakaoMapAPI;

	@Autowired
	public KakaoMap(KakaoMapAPI kakaoMapAPI) {
		this.kakaoMapAPI = kakaoMapAPI;
	}

	/*
	Name     |  Type |  Description        |  Required
	distance	Int	   전체 검색 결과 거리(미터)	   필수
	duration	Int	   목적지까지 소요 시간(초)	   필수
	 */
	public DistanceDurationInfo carPathFind(Location src, Location dst) {
		return convertUnit(kakaoMapAPI.KaKaoCarDirections(src, dst));
	}

	/*
	 * 거리 m -> km
	 * 시간 sec -> min
	 * */
	private DistanceDurationInfo convertUnit(DistanceDurationInfo info) {
		double distance = info.getDistance() / (double)1000;
		double duration = info.getDuration() / (double)3600;
		return new DistanceDurationInfo(distance, duration);
	}

	/*
	시/도 를 반환함.
	 */
	public String searchRoadAddress(double latitude, double longitude) {
		return kakaoMapAPI.searchRoadAddress(latitude, longitude);
	}
}
