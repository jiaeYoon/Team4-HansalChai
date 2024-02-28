import styled, { keyframes } from "styled-components";
import { MaxDeviceWidth } from "../../data/GlobalVariable.js";
// ToastMaker에서 props를 통한 theme 전파를 사용할 수 없어
// Toast에서도 props로 theme을 받을 수 없음
import theme from "../../styles/theme/Theme.jsx";
import {
  CgCloseO as ErrorSvg,
  CgDanger as WarningSvg,
  CgMoreO as InfoSvg,
  CgCheckO as SuccessSvg
} from "react-icons/cg";

const toastAnimation = keyframes`
  0% {
    transform: translateY(100%);
  }
  100% {
    transform: translateY(0%);
  }
`;

const typeColor = {
  success: theme.colors.successColor,
  error: theme.colors.errorColor,
  warning: theme.colors.warningColor,
  info: theme.colors.infoColor
};

const typeBackground = {
  success: theme.colors.successColor,
  error: theme.colors.errorColor,
  warning: theme.colors.warningColor,
  info: theme.colors.infoColor
};

const typeSvg = {
  success: <SuccessSvg size={20} color={theme.colors.white} />,
  error: <ErrorSvg size={20} color={theme.colors.white} />,
  warning: <WarningSvg size={20} color={theme.colors.white} />,
  info: <InfoSvg size={20} color={theme.colors.white} />
};

const ToastFrame = styled.div`
  max-width: calc(${MaxDeviceWidth} - 40px);
  width: calc(100% - 40px);
  height: 48px;
  background-color: ${({ type }) => typeBackground[type]};
  border-radius: 10px;
  padding: 4px;
  font-family: "semiBold";
  font-size: 16px;
  text-align: center;
  color: ${theme.colors.white};
  border: 1pt solid ${({ type }) => typeColor[type]};
  ${theme.flex.flexCenter};
  position: relative;
  animation: ${toastAnimation} 0.5s;
  z-index: 101;
`;

const ToastIcon = styled.div`
  position: absolute;
  width: 20px;
  height: 20px;
  left: 20px;
  top: 50%;
  transform: translateY(-50%);
`;

const ToastText = styled.div`
  width: calc(100% - 100px);
  display: flex;
  justify-content: center;
  text-align: left;
  align-items: center;
  word-break: keep-all;
  line-break: loose;
`;

const Toast = ({ type, children }) => {
  return (
    <ToastFrame type={type}>
      <ToastIcon>{typeSvg[type]}</ToastIcon>
      <ToastText>{children}</ToastText>
    </ToastFrame>
  );
};

export default Toast;
