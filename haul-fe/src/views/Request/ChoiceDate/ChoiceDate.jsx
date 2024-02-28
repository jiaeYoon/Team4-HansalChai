import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { reservationStore } from "../../../store/reservationStore.jsx";
import MobileLayout from "../../../components/MobileLayout/MobileLayout.jsx";
import Header from "../../../components/Header/Header.jsx";
import Margin from "../../../components/Margin/Margin.jsx";
import Typography from "../../../components/Typhography/Typhography.jsx";
import TypographySpan from "../../../components/Typhography/TyphographySpan.jsx";
import Calendar from "./components/Calendar.jsx";
import FixedCenterBox from "../../../components/FixedBox/FixedCenterBox.jsx";
import BottomButton from "../../../components/Button/BottomButton.jsx";
import NavigationBar from "../../../components/NavigationBar/NavigationBar.jsx";
import MotionWrapper from "../../../components/MotionWrapper/MotionWrapper.jsx";
import { isEmptyString, stringToDateObject } from "../../../utils/helper.js";
import { UrlMap } from "../../../data/GlobalVariable.js";

const ChoiceDate = () => {
  const navigation = useNavigate();
  const [selectedDay, setSelectedDay] = useState();

  const {
    setReservationDate,
    state: { transportType, reservationDate }
  } = useContext(reservationStore);

  useEffect(() => {
    //예상치 않은 URL접속을 방지
    if (isEmptyString(transportType)) {
      navigation(UrlMap.choiceTranportTypeUrl);
    }
    //이전에 선택되어진게 있는지 확인. 있다면 그걸로 선택
    if (!isEmptyString(reservationDate)) {
      const beforeChoiceDate = stringToDateObject(reservationDate);
      setSelectedDay(beforeChoiceDate);
    }
  }, []);

  const DateFormChange = date => {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const day = date.getDate().toString().padStart(2, "0");
    return `${year}-${month}-${day}`;
  };

  const SubmitDateBtnFun = () => {
    setReservationDate(DateFormChange(selectedDay));
    navigation(UrlMap.choiceTimePageUrl);
  };

  return (
    <MotionWrapper>
      <MobileLayout>
        <Header>
          HAUL
          <TypographySpan color="subColor">.</TypographySpan>
        </Header>
        <Margin height="24px" />
        <Typography font="bold24">
          <TypographySpan color="subColor">{transportType}</TypographySpan>을
          선택하셨군요.
        </Typography>
        <Margin height="4px" />
        <Typography font="bold24">언제 찾아뵈면 될까요?</Typography>
        <Margin height="60px" />
        <Calendar selectedDay={selectedDay} setSelectedDay={setSelectedDay} />
        <FixedCenterBox bottom="100px">
          <BottomButton
            role="main"
            disabled={!selectedDay}
            onClick={() => {
              SubmitDateBtnFun();
            }}
          >
            선택 완료
          </BottomButton>
        </FixedCenterBox>
        <NavigationBar />
      </MobileLayout>
    </MotionWrapper>
  );
};
export default ChoiceDate;
