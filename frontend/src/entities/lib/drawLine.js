function calculateDistance(x1, y1, x2, y2) {
  return Math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2);
}

function normalizeVector(vector) {
  const length = Math.sqrt(vector.m ** 2 + 1);
  return {
    dx: vector.m / length,
    dy: 1 / length,
  };
}

function getParallelLineEquation(x1, y1, x2, y2) {
  // Вычислить угловой коэффициент
  const m = (y2 - y1) / (x2 - x1);

  // Вычислить свободный член
  const b = y1 - m * x1;

  // Вернуть уравнение прямой в виде объекта
  return {m, b};
}

function getParallelLinePoints(x1, y1, x2, y2, R) {
  const distance = calculateDistance(x1, y1, x2, y2);

  let normalizedVector;
  if (Math.abs(y2 - y1) < 1e-5) {
    // Если y1 и y2 близки
    normalizedVector = {dx: 0, dy: R};
  } else {
    // Нормализовать вектор направления
    normalizedVector = {dx: x2 - x1, dy: y2 - y1};
  }

  // Если x1 и x2 близки, корректируем направление
  if (Math.abs(x2 - x1) < 1e-5) {
    normalizedVector = {dx: R, dy: 0};
  }

  // Нормализовать вектор направления
  const length = Math.sqrt(normalizedVector.dx ** 2 + normalizedVector.dy ** 2);
  normalizedVector.dx /= length;
  normalizedVector.dy /= length;

  // Вычислить координаты начальной и конечной точек на расстоянии R
  const startX = x1;
  const startY = y1;

  const endX = x2;
  const endY = y2;

  const parallelStartX = startX + R * normalizedVector.dy;
  const parallelStartY = startY - R * normalizedVector.dx;

  const parallelEndX = endX + R * normalizedVector.dy;
  const parallelEndY = endY - R * normalizedVector.dx;
  return {
    start: {x: parallelStartX, y: parallelStartY}, end: {x: parallelEndX, y: parallelEndY},
  };
}

// Пример использования:
function getPerpendicularLinePoints(x1, y1, x2, y2, R) {
  let normalizedVector;
  normalizedVector = {dx: x2 - x1, dy: y2 - y1};

  // Нормализовать вектор направления
  const length = Math.sqrt(normalizedVector.dx ** 2 + normalizedVector.dy ** 2);
  normalizedVector.dx /= length;
  normalizedVector.dy /= length;

  // Вычислить координаты начальной и конечной точек на расстоянии R
  const startX = x1;
  const startY = y1;

  const endX = x2;
  const endY = y2;
  let perpendicularStartX = startX + R * normalizedVector.dy;
  let perpendicularStartY = startY - R * normalizedVector.dx;
  /*if (y2 === y1) {
    perpendicularStartX = startX + R;
  }
  if (x1 === x2) {
    perpendicularStartY = startY - R;
  }*/
  let perpendicularEndX = endX + R * normalizedVector.dy;
  let perpendicularEndY = endY - R * normalizedVector.dx;
  /*if (y2 === y1) {
    perpendicularEndX = endX + R;
  }
  if (x1 === x2) {
    perpendicularEndY = endY + R;
  }*/
  return {start: {x: perpendicularStartX, y: perpendicularStartY}, end: {x: perpendicularEndX, y: perpendicularEndY}};
}

function getRadians(degrees) {
  return (Math.PI / 180) * degrees;
}

export const drawLine = (context, x1, y1, x2, y2, lineColor, r = 1) => {
  const parallelLineWithDistance = getPerpendicularLinePoints(x1, y1, x2, y2, r);
  const parallelLineWithDistanceL = getPerpendicularLinePoints(x1, y1, x2, y2, -r);
  context.beginPath();
  context.moveTo(x1, y1 + 275);
// drawing a blueprint line to the finishing position
  context.lineTo(x2, y2 + 275);

  context.lineWidth = 3;
  context.lineJoin = 'round';
  context.miterLimit = 1;
// taking a purple pen and coloring the line
  context.strokeStyle = lineColor;
  context.stroke();
  context.closePath();
  context.beginPath();
  context.arc(x2, y2 + 275, r, 0, getRadians(360));
  //context.strokeRect(x2, y2 + 275, r, r);
  context.stroke();
  context.closePath();
  context.beginPath();
  console.log(parallelLineWithDistance);
  //const parallelLinePointsWithDistance = getParallelLinePointsWithDistance(parallelLineAbove, x1, x2, r);

  context.moveTo(parallelLineWithDistance.start.x,
    parallelLineWithDistance.start.y + 275);
// drawing a blueprint line to the finishing position
  context.lineTo(parallelLineWithDistance.end.x, parallelLineWithDistance.end.y + 275);

  context.lineWidth = 0.5;
  //context.lineJoin = 'round';
  context.miterLimit = 1;
// taking a purple pen and coloring the line
  context.strokeStyle = 'blue';
  context.stroke();
  context.closePath();
  context.beginPath();
  context.moveTo(parallelLineWithDistanceL.start.x,
    parallelLineWithDistanceL.start.y + 275);
// drawing a blueprint line to the finishing position
  context.lineTo(parallelLineWithDistanceL.end.x, parallelLineWithDistanceL.end.y + 275);

  context.lineWidth = 0.5;

  context.miterLimit = 1;


  context.stroke();

  context.closePath();

};