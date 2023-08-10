import {drawLines} from "./drawLines";
import {drawLowLines} from "./drawLowLines";

export const drawAllLines = (context, dots, lower, high) =>{
    context.clearRect(0, 0, 550, 350);
    drawLines(context, dots)
    drawLowLines(context, lower)
    drawLines(context, high)
}