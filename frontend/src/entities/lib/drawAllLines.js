import {drawLines} from "./drawLines";
import {drawLowLines} from "./drawLowLines";
import {drawHighLines} from "./drawHighLines";

export const drawAllLines = (context, dots, lower, high) =>{
    context.clearRect(0, 0, 750, 550);
    drawLines(context, dots)
    drawLowLines(context, lower)
    drawHighLines(context, high)
}