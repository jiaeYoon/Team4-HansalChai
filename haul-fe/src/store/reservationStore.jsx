import { useReducer, createContext } from "react";

const initialState = {
  transportType: "",
  reservationDate: "",
  reservationTime: "",
  srcName: "",
  srcAddress: "",
  srcCoordinate: {
    srcLatitude: 37.497259947611596,
    srcLongitude: 127.03218978408303
  },
  srcDetailAddress: "",
  srcTel: "",
  dstName: "",
  dstAddress: "",
  dstCoordinate: {
    dstLatitude: 37.497259947611596,
    dstLongitude: 127.03218978408303
  },
  dstDetailAddress: "",
  dstTel: "",
  cargoWeight: "",
  cargoWidth: "",
  cargoLength: "",
  cargoHeight: "",
  specialNotes: [
    { note: "냉장", selected: false },
    { note: "냉동", selected: false },
    { note: "탑차", selected: false },
    { note: "가구", selected: false },
    { note: "리프트 필요", selected: false }
  ],
  guestName: "",
  guestTel: ""
};

const reservationStore = createContext(initialState);

const ReservationStoreProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const setInitialState = () => {
    dispatch({ type: "SET_INITIAL_STATE" });
  };

  const setTransportType = transportType => {
    dispatch({ type: "SET_TRANSPORT_TYPE", payload: { transportType } });
  };

  const setReservationDate = reservationDate => {
    dispatch({ type: "SET_RESERVATION_DATE", payload: { reservationDate } });
  };

  const setReservationTime = reservationTime => {
    dispatch({ type: "SET_RESERVATION_TIME", payload: { reservationTime } });
  };

  const setGuestInfo = ({ guestName, guestTel }) => {
    dispatch({ type: "SET_GUEST_INFO", payload: { guestName, guestTel } });
  };

  const setSrcInfo = ({
    srcName,
    srcAddress,
    srcLatitude,
    srcLongitude,
    srcDetailAddress,
    srcTel
  }) => {
    dispatch({
      type: "SET_SRC_INFO",
      payload: {
        srcName,
        srcAddress,
        srcLatitude,
        srcLongitude,
        srcDetailAddress,
        srcTel
      }
    });
  };

  const getReservationState = () => {
    return {
      transportType: state.transportType,
      reservationDate: state.reservationDate,
      reservationTime: state.reservationTime,
      srcName: state.srcName,
      srcAddress: state.srcAddress,
      srcCoordinate: state.srcCoordinate,
      srcDetailAddress: state.srcDetailAddress,
      srcTel: state.srcTel,
      dstName: state.dstName,
      dstAddress: state.dstAddress,
      dstCoordinate: state.dstCoordinate,
      dstDetailAddress: state.dstDetailAddress,
      dstTel: state.dstTel,
      cargoWeight: state.cargoWeight,
      cargoWidth: state.cargoWidth,
      cargoLength: state.cargoLength,
      cargoHeight: state.cargoHeight,
      specialNotes: state.specialNotes,
      guestName: state.guestName,
      guestTel: state.guestTel
    };
  };

  const setDstInfo = ({
    dstName,
    dstAddress,
    dstLatitude,
    dstLongitude,
    dstDetailAddress,
    dstTel
  }) => {
    dispatch({
      type: "SET_DST_INFO",
      payload: {
        dstName,
        dstAddress,
        dstLatitude,
        dstLongitude,
        dstDetailAddress,
        dstTel
      }
    });
  };

  const setRoadInfo = ({
    cargoWeight,
    cargoWidth,
    cargoLength,
    cargoHeight,
    specialNotes
  }) => {
    dispatch({
      type: "SET_ROAD_INFO",
      payload: {
        cargoWeight,
        cargoWidth,
        cargoLength,
        cargoHeight,
        specialNotes
      }
    });
  };

  return (
    <reservationStore.Provider
      value={{
        state,
        setInitialState,
        setTransportType,
        setReservationDate,
        setReservationTime,
        setSrcInfo,
        setDstInfo,
        setRoadInfo,
        setGuestInfo,
        getReservationState
      }}
    >
      {children}
    </reservationStore.Provider>
  );
};

const reducer = (state, action) => {
  switch (action.type) {
    case "SET_INITIAL_STATE":
      return {
        ...initialState
      };
    case "SET_TRANSPORT_TYPE":
      return {
        ...state,
        transportType: action.payload.transportType
      };
    case "SET_RESERVATION_DATE":
      return {
        ...state,
        reservationDate: action.payload.reservationDate
      };
    case "SET_RESERVATION_TIME":
      return {
        ...state,
        reservationTime: action.payload.reservationTime
      };
    case "SET_SRC_INFO":
      return {
        ...state,
        srcName: action.payload.srcName,
        srcAddress: action.payload.srcAddress,
        srcCoordinate: {
          srcLatitude: action.payload.srcLatitude,
          srcLongitude: action.payload.srcLongitude
        },
        srcDetailAddress: action.payload.srcDetailAddress,
        srcTel: action.payload.srcTel
      };
    case "SET_DST_INFO":
      return {
        ...state,
        dstName: action.payload.dstName,
        dstAddress: action.payload.dstAddress,
        dstCoordinate: {
          dstLatitude: action.payload.dstLatitude,
          dstLongitude: action.payload.dstLongitude
        },
        dstDetailAddress: action.payload.dstDetailAddress,
        dstTel: action.payload.dstTel
      };
    case "SET_ROAD_INFO":
      return {
        ...state,
        cargoWeight: action.payload.cargoWeight,
        cargoWidth: action.payload.cargoWidth,
        cargoLength: action.payload.cargoLength,
        cargoHeight: action.payload.cargoHeight,
        specialNotes: action.payload.specialNotes
      };
    case "SET_GUEST_INFO":
      return {
        ...state,
        guestName: action.payload.guestName,
        guestTel: action.payload.guestTel
      };
    default:
      return state;
  }
};

export { reservationStore, ReservationStoreProvider };
