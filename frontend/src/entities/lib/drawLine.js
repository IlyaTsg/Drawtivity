export const drawLine = (context, x1, y1, x2, y2) =>{
    context.beginPath();
    context.moveTo(x1, y1 + 175);
// drawing a blueprint line to the finishing position
    context.lineTo(x2, y2 + 175);
    context.lineWidth = 3;
// taking a purple pen and coloring the line
    context.strokeStyle = "black";
    context.stroke();
    context.closePath();
}