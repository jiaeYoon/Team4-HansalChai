import styled from "styled-components";

const ButtonStyle = styled.button`
  width: 100%;
  height: 48px;
  left: 20px;
  background-color: ${({ role, theme }) =>
    role === "main"
      ? theme.colors.mainColor
      : theme.colors.subButtonBackground};
  color: ${({ role, theme }) =>
    role === "main" ? theme.colors.white : theme.colors.mainColor};
  font-family: "bold";
  font-size: 16px;
  text-align: center;
  border-radius: 10px;
  &:hover {
    opacity: 0.9;
  }
  &:disabled {
    background-color: ${({ theme }) => theme.colors.disabledColor};
    color: ${({ theme }) => theme.colors.white};
    &:hover {
      opacity: 1;
    }
  }
`;

const BottomButton = ({
  role,
  disabled = false,
  onClick = undefined,
  type = undefined,
  form = undefined,
  children
}) => {
  return (
    <ButtonStyle
      role={role}
      onClick={onClick}
      disabled={disabled}
      type={type}
      form={form}
    >
      {children}
    </ButtonStyle>
  );
};

export default BottomButton;
