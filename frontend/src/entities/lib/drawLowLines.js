import {drawLowLine} from "./drawLowLine";

export const drawLowLines = (context, dots) =>{
    for(let i = 0; i < dots.length-1; i++){
        drawLowLine(context, dots[i].x, dots[i].y, dots[i+1].x, dots[i+1].y)
    }
}