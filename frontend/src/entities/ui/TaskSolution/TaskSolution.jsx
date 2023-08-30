import React, {useEffect, useRef, useState} from 'react';
import {drawAllLines} from "../../lib/drawAllLines";
import useImage from "../../model/store/hooks/useImage";
import {useDispatch} from "react-redux";
import {dotsParsClick} from "../../lib/dotsParsClick";

const TaskSolution = ({setCoord}) => {
    const selectedImage = useImage()
    const [dots,setDots] = useState([])
    const canvasRef = useRef(null)
    const backgroundCss = {
        backgroundImage: `${selectedImage ?`url(${URL.createObjectURL(selectedImage)})`: `null` }`,
        backgroundRepeat:  `no-repeat`,
        backgroundSize: `770px 550px`
    }
    const dispatch = useDispatch()
    const clickHandler = (event) =>{
        let x = event.pageX,
            y = event.pageY;
        setDots([...dots, (dotsParsClick(x - canvasRef.current.offsetLeft, y-canvasRef.current.offsetTop - 275))])
        //console.log(`${x - canvasRef.current.offsetLeft}:${y-canvasRef.current.offsetTop - 175}`);
    }
    useEffect(() => {
        if(canvasRef.current){
            const context = canvasRef.current.getContext("2d");
            const base_image = new Image();

            base_image.src = selectedImage.name;
            base_image.onload = function(){
                context.drawImage(selectedImage.name, 0, 0);
            }
            //console.log('test')
            drawAllLines(context, dots, [{x:0, y:0}],[{x:0, y:0}])
            setCoord(dots)
        }

    }, [canvasRef, dots]);
    return (
        <div>
            <form>
                <canvas ref={canvasRef}
                        style={backgroundCss} width={"770px"}
                        height={"550px"} onClick={clickHandler}>
                </canvas>
            </form>
        </div>
    );
};

export default TaskSolution;