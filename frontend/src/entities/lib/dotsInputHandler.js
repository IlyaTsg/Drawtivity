import {dotsPars} from './dotsPars';
import {setCoordinates} from '../model/store/slices/tasksSlice';

export const dotsInputHandler = (event, dispatch) => {
  //let value = event.nativeEvent.inputType === 'deleteContentBackward' ? event.target.value.split(' ').slice(0, -2).join('') : event.target.value;
  dispatch(setCoordinates(dotsPars(event.target.value)));
};