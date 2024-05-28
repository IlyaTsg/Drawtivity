import { dotsPars } from './dotsPars';

function processInputString(ctx, inputString) {
  const figures = inputString.split(/\s{2,}/);

  let lastUnfilledFigureIndex = -1;

  figures.forEach((figure, index) => {
    const lines = figure.split(/\s+/);

    const points = lines.map(line => {
      const [x, y] = line.split(':');
      return { x: parseInt(x), y: parseInt(y) };
    });

    const isFilled = isAreaFilled(ctx, points);

    if (!isFilled) {
      lastUnfilledFigureIndex = index;
    }
  });
  for (let i = lastUnfilledFigureIndex + 1; i < figures.length; i++) {
    const lines = figures[i].split(/\s+/);

    const points = lines.map(line => {
      const [x, y] = line.split(':');
      return { x: parseInt(x), y: parseInt(y) };
    });

    fillArea(ctx, points);
    //console.log(`Фигура ${i + 1} закрашена.`);
  }
}

function isAreaFilled(ctx, points) {
  ctx.beginPath();
  ctx.moveTo(points[0].x, points[0].y + 275);

  for (let i = 1; i < points.length; i++) {
    ctx.lineTo(points[i].x, points[i].y + 275);
  }

  ctx.closePath();
  ctx.fillStyle = 'rgba(255, 0, 0, 0.5)';
  ctx.fill();

  // Проверяем, закрашена ли область хотя бы в одной точке
  const checkPointX = points[0].x;
  const checkPointY = points[0].y + 275;
  //console.log(`Проверка точки (${checkPointX}, ${checkPointY}): ${isPointInPathResult}`);

  return ctx.isPointInPath(checkPointX, checkPointY);
}

function fillArea(ctx, points) {
  ctx.beginPath();
  ctx.moveTo(points[0].x, points[0].y + 275);

  for (let i = 1; i < points.length; i++) {
    ctx.lineTo(points[i].x, points[i].y + 275);
  }

  ctx.closePath();
  ctx.fillStyle = 'rgba(255, 0, 0, 0.5)';
  ctx.fill();
}

export const drawArea = (ctx, pointsStr, lineColor, isSolution) => {
  console.log(pointsStr);
  if (typeof pointsStr === 'string') {
    let points;
    let normalizedPoints;
    if (pointsStr.charAt(0) === ' ' && pointsStr.charAt(1) !== ' ') {
      normalizedPoints = pointsStr.slice(0);
    }
    points = dotsPars(normalizedPoints || pointsStr);

    console.log(points, pointsStr);
    if (points.length > 0) {
      // Перемещаем контекст к первой точке

      // Соединяем линиями остальные точки
      for (let i = 0; i < points.length - 1; i++) {
        ctx.beginPath();
        ctx.moveTo(points[i].x, points[i].y + 275);
        ctx.lineTo(points[i + 1].x, points[i + 1].y + 275);
        ctx.lineWidth = 3;
        ctx.lineJoin = 'round';
        ctx.miterLimit = 1;
        ctx.strokeStyle = lineColor;
        ctx.stroke();
      }

      // Закрываем фигуру
      ctx.closePath();
      // Закрашиваем площадь
      processInputString(ctx, normalizedPoints || pointsStr);
    }
  }
};

function processPoints(ctx, points) {
  // Определяем последнюю незаполненную фигуру
  let lastUnfilledFigureIndex = -1;

  // Проверка каждой фигуры на закрашенность
  for (let i = 0; i < points.length; i++) {
    const isFilled = isAreaFilled(ctx, points);

    if (!isFilled) {
      lastUnfilledFigureIndex = i;
    }
  }

  // Закрашиваем все фигуры, начиная с последней незакрашенной
  if (lastUnfilledFigureIndex !== -1) {
    fillArea(ctx, points);
  }

  function isAreaFilled(ctx, points) {
    ctx.beginPath();
    ctx.moveTo(points[0].x, points[0].y + 275);

    for (let i = 1; i < points.length; i++) {
      ctx.lineTo(points[i].x, points[i].y + 275);
    }

    ctx.closePath();
    ctx.fillStyle = 'rgba(255, 0, 0, 0.5)';
    ctx.fill();

    // Проверяем, закрашена ли область хотя бы в одной точке
    const checkPointX = points[0].x;
    const checkPointY = points[0].y + 275;

    return ctx.isPointInPath(checkPointX, checkPointY);
  }

  function fillArea(ctx, points) {
    ctx.beginPath();
    ctx.moveTo(points[0].x, points[0].y + 275);

    points.forEach(point => ctx.lineTo(point.x, point.y + 275));
    ctx.closePath();
    ctx.fill();
  }
}


export const drawSolutionArea = (ctx, points, lineColor) => {

  if (points.length > 0) {
    // Перемещаем контекст к первой точке

    // Соединяем линиями остальные точки
    for (let i = 0; i < points.length - 1; i++) {
      ctx.beginPath();
      ctx.moveTo(points[i].x, points[i].y + 275);
      ctx.lineTo(points[i + 1].x, points[i + 1].y + 275);
      ctx.lineWidth = 3;
      ctx.lineJoin = 'round';
      ctx.miterLimit = 1;
      ctx.strokeStyle = lineColor;
      ctx.stroke();
    }
    console.log(points);
    // Закрываем фигуру
    ctx.closePath();
    // Закрашиваем площадь
    //processInputString(ctx, points);
    processPoints(ctx, points);
  }
};