import MobileLayout from "../../../components/MobileLayout/MobileLayout";
import Header from "../../../components/Header/Header.jsx";
import TypographySpan from "../../../components/Typhography/TyphographySpan.jsx";
import Typography from "../../../components/Typhography/Typhography.jsx";
import Margin from "../../../components/Margin/Margin.jsx";
import UserInfoBox from "../../../components/UserInfoBox/UserInfoBox.jsx";
import DetailInfo from "../../../components/DetailInfo/DetailInfo.jsx";
import HaulInfoBox from "../../../components/HaulInfoBox/HaulInfoBox.jsx";
import Carousel from "../../../components/Carousel/Carousel.jsx";
import DriverStatusButton from "./components/DriverStatusButton.jsx";
import ToastMaker from "../../../components/Toast/ToastMaker.jsx";
import Loading from "../../Loading/Loading.jsx";
import { useParams, useNavigate } from "react-router-dom";
import { checkOrderDetail } from "../../../repository/checkRepository.jsx";
import { useState, useEffect } from "react";
import { getUserName } from "../../../utils/localStorage.js";

const ScheduleCheckDetail = () => {
  const { orderId } = useParams();
  const driverName = getUserName();
  console.log(driverName);
  const [orderData, setOrderData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    showDetailFun();
  }, []);

  async function showDetailFun() {
    const { success, data, message } = await checkOrderDetail({
      orderId: orderId
    });
    if (success) {
      setOrderData(data.data);
    } else {
      //FIXME: 예외처리 생성 시 적용
      ToastMaker({ type: "error", children: message });
      navigate(-1);
    }
  }

  if (!orderData) {
    return <Loading />;
  }

  return (
    <MobileLayout>
      <Header>
        <Typography font="bold24">
          <TypographySpan color="subColor">{driverName}</TypographySpan>님의
          일정<TypographySpan color="subColor"> .</TypographySpan>
        </Typography>
      </Header>
      <Margin height="24px" />
      <Carousel
        carouselList={[
          <UserInfoBox
            key="user"
            kind="user"
            name={orderData.user.name}
            tel={orderData.user.tel}
          />,
          <UserInfoBox key="src" kind="src" tel={orderData.user.tel} />,
          <UserInfoBox key="dst" kind="dst" tel={orderData.user.tel} />
        ]}
        initialIndex={0}
      />
      <Margin height="20px" />
      <HaulInfoBox
        time="2023.11.28 13:50"
        srcName={orderData.src.name}
        srcAddres={orderData.src.address}
        srcDetailAddress={orderData.src.detailAddress}
        dstName={orderData.dst.name}
        dstAddress={orderData.dst.address}
        dstDetailAddress={orderData.dst.detailAddress}
        load={orderData.cargo.weight}
        width={orderData.cargo.width}
        length={orderData.cargo.length}
        height={orderData.cargo.height}
      />

      <Margin height="24px" />
      <DetailInfo
        srcCoordinate={{
          lat: orderData.src.latitude,
          lng: orderData.src.longitude
        }}
        srcAddress={orderData.src.address}
        srcName={orderData.src.name}
        dstCoordinate={{
          lat: orderData.dst.latitude,
          lng: orderData.dst.longitude
        }}
        dstAddress={orderData.dst.address}
        dstName={orderData.dst.name}
        fee={orderData.cost}
        time={orderData.requiredTime}
      />
      <Margin height="30px" />

      <DriverStatusButton
        orderId={orderId}
        status={orderData.transportStatus}
      />
      <Margin height="70px" />
    </MobileLayout>
  );
};

export default ScheduleCheckDetail;
