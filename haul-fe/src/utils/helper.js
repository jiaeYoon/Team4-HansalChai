export const isEmptyString = value => {
  return value.length === 0;
};

export const isNumber = value => {
  return !isNaN(value);
};

export const isPositiveNumber = value => {
  return Number(value) > 0;
};

// 문자열이 이메일 형식인지 확인해주는 함수
export const checkEmail = email => {
  const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}$/;
  return emailRegex.test(email);
};

// 문자열이 전화번호 형식인지 확인해주는 함수 01012341234 010-3977-2337 둘다 가능함
export function isPhoneNumber(input) {
  const regPhone = /^(01[016789]|02|[0-9]{2,3})-?([0-9]{3,4})-?([0-9]{4})$/;
  if (regPhone.test(input)) {
    const cleanedNumber = input.replace(/-/g, "");
    return cleanedNumber;
  }
  return false;
}

// 2022-02-08이라는 문자열을 넣으면 Date객체로 만들어주는 함수
export function stringToDateObject(str) {
  const parts = str.split("-");
  const date = new Date(parts[0], parts[1] - 1, parts[2]);
  return date;
}

export function functionBinder(func, bindargs) {
  return function ({ ...args }) {
    return func({ ...bindargs, ...args });
  };
}

// useNavigate를 이용해 페이지 이동만 할 때 사용하는 함수
export const makeNavigator = (navigator, link) => {
  return () => navigator(link);
};

export const getDeviceInfo = () => {
  const { userAgent } = window.navigator;
  const isIOS = !!userAgent.match(/iPad|iPhone|iPod/i);
  const isAndroid = !!userAgent.match(/Android/i);
  const isComputer =
    !isIOS && !isAndroid && /^(Win|Mac|Linux)/.test(navigator.platform);
  const browser =
    userAgent.match(
      /(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i
    ) || [];

  if (isIOS) {
    return {
      device: "iOS",
      browser: "Safari",
      version: browser[2]
    };
  } else if (isAndroid) {
    return {
      device: "Android",
      browser: browser[1].toLowerCase(),
      version: browser[2]
    };
  } else if (isComputer) {
    return {
      device: "Computer",
      browser: browser[1].toLowerCase(),
      version: browser[2]
    };
  } else {
    return {
      device: "Unknown",
      browser: "Unknown",
      version: "Unknown"
    };
  }
};
