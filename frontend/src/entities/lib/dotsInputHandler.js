import {setCoordinates} from '../model/store/slices/tasksSlice';

export const dotsInputHandler = (event, dispatch) => {
  dispatch(setCoordinates((event.target.value)));
};