import { drawLine, drawPoint, drawSolutionLine } from './drawLine';
import { dotsPars } from './dotsPars';
import { drawArea, drawSolutionArea } from './drawArea';

export const drawLines = (context, dots, lineColor, percent, type) => {
  const dotsArr = typeof dots === 'string' ? dotsPars(dots) : dots;
  if (type === 'Точки') {
    console.log(dotsArr, '111', dotsArr.length);
    if (dotsArr.length === 1) {
      drawLine(context, dotsArr[0].x, dotsArr[0].y, dotsArr[0].x, dotsArr[0].y, lineColor, percent);
    }
    for (let i = 0; i < dotsArr.length - 1; i++) {
      drawLine(context, dotsArr[i].x, dotsArr[i].y, dotsArr[i + 1].x, dotsArr[i + 1].y, lineColor, percent);
    }
  } else {
    drawArea(context, dots, lineColor);
  }
};

export const drawSolution = (context, dots, lineColor, type) => {
  const dotsArr = typeof dots === 'string' ? dotsPars(dots) : dots;
  if (type === 'Linear') {
    if (dotsArr.length === 1) {
      drawSolutionLine(context, dotsArr[0].x, dotsArr[0].y, dotsArr[0].x, dotsArr[0].y, lineColor);
    }
    for (let i = 0; i < dotsArr.length - 1; i++) {
      drawSolutionLine(context, dotsArr[i].x, dotsArr[i].y, dotsArr[i + 1].x, dotsArr[i + 1].y, lineColor);
    }
  } else {
    drawArea(context, dots, lineColor);
    //drawSolutionArea(context, dotsArr, lineColor);
  }
};