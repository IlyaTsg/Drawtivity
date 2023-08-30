export const drawHighLine = (context, x1, y1, x2, y2) =>{
    context.beginPath();
    context.moveTo(x1, y1 + 275);
// drawing a blueprint line to the finishing position
    context.lineTo(x2, y2 + 275);
// taking a purple pen and coloring the line
    context.strokeStyle = "blue";
    context.stroke();
    context.closePath();
}