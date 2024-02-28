import ToastMaker from "../components/Toast/ToastMaker";
import { ErrorMessageMap } from "../data/GlobalVariable";
import { getAccessToken, logoutFun } from "../utils/localStorage";

const apiKey = import.meta.env.VITE_API_KEY;

export async function loginFun({ tel, password }) {
  try {
    const response = await fetch(`${apiKey}/api/v2/users/customers/sign-in`, {
      mode: "cors",
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ tel, password })
    });
    const data = await response.json();
    if (data.status === 200)
      return {
        success: true,
        data
      };
    return { success: false, code: data.code };
  } catch (error) {
    console.error("Login error:", error);
    return { success: false, message: error.toString() };
  }
}

export async function signUpFun({ name, tel, password, email }) {
  try {
    const response = await fetch(`${apiKey}/api/v1/users/sign-up`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ name, tel, password, email })
    });
    const data = await response.json();
    if (data.status === 200)
      return {
        success: true,
        data
      };
    return { success: false, code: data.code };
  } catch (error) {
    console.error("Sign Up error:", error);
    return { success: false, message: error.toString() };
  }
}

export async function getUserProfile() {
  try {
    const response = await fetch(`${apiKey}/api/v1/users/profile`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${getAccessToken()}`
      }
    });
    const body = await response.json();
    if (body.code === 200) {
      return {
        success: true,
        data: body.data
      };
    } else {
      return {
        success: false,
        code: body.code,
        message: ErrorMessageMap.NoUserFound
      };
    }
  } catch (error) {
    console.error("Get Profile error:", error);
    return { success: false, code: 0, message: ErrorMessageMap.UnknownError };
  }
}

export async function putPassword({ password }) {
  try {
    const response = await fetch(`${apiKey}/api/v1/users/profile`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${getAccessToken()}`
      },
      body: JSON.stringify({ password })
    });

    const body = await response.json();

    if (body.code === 200) {
      return {
        success: true,
        message: "비밀번호 변경에 성공했어요"
      };
    } else {
      return {
        success: false,
        code: body.code,
        message: ErrorMessageMap.ChangePasswordFailed
      };
    }
  } catch (error) {
    return { success: false, code: 0, message: ErrorMessageMap.UnknownError };
  }
}

export function isTokenInvalid(code) {
  switch (code) {
    case 2003:
      ToastMaker({
        type: "error",
        children: ErrorMessageMap.InvalidAccessError
      });
      break;
    case 1001:
    case 2001:
    case 2002: //fall-through
      ToastMaker({
        type: "error",
        children: ErrorMessageMap.TokenExpired
      });
      break;
    default:
      return false;
  }
  logoutFun();
  return true;
}
