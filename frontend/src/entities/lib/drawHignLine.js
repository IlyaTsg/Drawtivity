export const drawHignLine = (context, x1, y1, r) =>{
   context.beginPath();
   context.translate(x1 + r,y1 + r)
    context.lineWidth = 3;
// taking a purple pen and coloring the line
    context.strokeStyle = "yellow";
    context.stroke();
    context.closePath();
}