import {drawLines} from './drawLines';
import {drawLowLines} from './drawLowLines';
import {drawHighLines} from './drawHighLines';

export const drawAllLines = (context, dots, lower, high, lineColor, percent) => {
  //context.clearRect(0, 0, 750, 550);
  drawLines(context, dots, lineColor, percent);
  //drawLowLines(context, lower);
  //drawHighLines(context, high);
};