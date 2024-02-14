export const drawLowLine = (context, x1, y1, x2, y2) => {
  context.beginPath();
  context.lineWidth = 1;
  context.moveTo(x1, y1 + 275);
// drawing a blueprint line to the finishing position
  context.lineTo(x2, y2 + 275);
// taking a purple pen and coloring the line
  context.strokeStyle = '#F08A97';
  context.stroke();
  context.closePath();
};