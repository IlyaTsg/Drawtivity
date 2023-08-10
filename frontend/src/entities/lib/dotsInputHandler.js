import {dotsPars} from "./dotsPars";
import {setCoordinates} from "../model/store/slices/tasksSlice";

export const dotsInputHandler = (event, dispatch, canvas) => {
    dispatch(setCoordinates(dotsPars(event.target.value)))
}