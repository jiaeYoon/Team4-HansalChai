export const MaxDeviceWidth = "440px";
export const MinDeviceWidth = "315px";

export const UrlMap = {
  loginPageUrl: "/login",
  guestLoginPageUrl: "/guestLogin",
  signUpPageUrl: "/signup",
  choiceTranportTypeUrl: "/request/type",
  choiceDatePageUrl: "/request/date",
  choiceTimePageUrl: "/request/time",
  choiceSrcPageUrl: "/request/source",
  choiceDstPageUrl: "/request/destination",
  choiceLoadInfoPageUrl: "/request/loadInfo",
  choicePaymentPageUrl: "/request/purchase",
  guestInfoPageUrl: "/request/guestInfo",
  resultPageUrl: "/request/result",
  completePageUrl: "/request/complete",
  morePageUrl: "/more/list",
  moreUserInfoPageUrl: "/more/user-info",
  moreUserPayments: "/more/user-payments",
  moreUserTems: "/more/terms",
  checkReservationPageUrl: "/check/list",
  checkReservationDetailPageUrl: "/check/detail",
  checkReservationGuestPageUrl: "/check/guest"
};

export const ErrorMessageMap = {
  NoneId: "존재하지 않는 아이디입니다.",
  InvalidTelformat: "전화번호 형식이 아닙니다.",
  InvalidEmailformat: "이메일 형식이 아닙니다.",
  NotSamePassword: "비밀번호가 일치하지 않습니다.",
  IsNotNumber: "숫자만 입력 가능합니다.",
  IsNotPositiveNumber: "0 이하는 입력 불가능합니다.",
  IsNotMinPasswordCount: "8자리 이상의 비밀번호만 가능합니다.",
  NoUserFound: "사용자 정보를 찾을 수 없었어요.",
  ChangePasswordFailed: "비밀번호 변경에 실패했어요.",
  TryLater: "잠시 후 다시 시도해주세요.",
  NetworkError: "현재 네트워크 상태가 불안정합니다.",
  TokenExpired: "로그인 정보가 만료되었습니다. 다시 로그인 해주세요.",
  InvalidAccessError: "비정상적인 접근입니다. 다시 로그인 해주세요.",
  ExistingPhoneNumberError: "이미 가입된 전화번호입니다.",
  NoMatchingHaulCarError:
    "요청하신 화물정보에 부합하는 차량이 존재하지 않습니다.",
  NotFindReservationError: "예약 정보를 찾을 수 없습니다",
  AlreadyReservationError: "이미 예약이 완료된 내역입니다.",
  ReservationNotFound: "예약 정보를 찾을 수 없어요.",
  NoPermission: "다른 사람의 예약 정보는 볼 수 없어요.",
  UnknownError: "알 수 없는 오류가 발생했어요."
};

export const TransportTypeArr = [
  {
    transportType: "일반 용달",
    transportPlusInfo: "중고, 물품 운송",
    maxLoad: 10,
    boxEachColor: "#d9c7e7",
    img: "transport1"
  },
  {
    transportType: "용달 이사",
    transportPlusInfo: "원룸, 다인 가구 이사",
    maxLoad: 5,
    boxEachColor: "#FF9A62",
    img: "transport2"
  },
  {
    transportType: "미니 용달",
    transportPlusInfo: "소규모 운송, 물품 3개 이하",
    maxLoad: 1,
    boxEachColor: "#F6D776",
    img: "transport3"
  },
  {
    transportType: "비즈니스 운송",
    transportPlusInfo: "거래처 납부, 기업 운송",
    maxLoad: 100,
    boxEachColor: "#85C7EE",
    img: "transport4"
  }
];
