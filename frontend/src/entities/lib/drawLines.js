import {drawLine} from './drawLine';
import {dotsPars} from './dotsPars';

export const drawLines = (context, dots, lineColor, percent) => {
  const dotsArr = typeof dots === 'string' ? dotsPars(dots) : dots;
  for (let i = 0; i < dotsArr.length - 1; i++) {
    drawLine(context, dotsArr[i].x, dotsArr[i].y, dotsArr[i + 1].x, dotsArr[i + 1].y, lineColor, percent);
  }
};