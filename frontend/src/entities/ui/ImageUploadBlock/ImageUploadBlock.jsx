import React, {useEffect, useRef} from 'react';
import useImage from "../../../store/hooks/useImage";
import classes from "./ImageUploadBlock.module.scss";
import DotsInput from "../../../shared/ui/DotsInput/DotsInput";
import Form from "react-bootstrap/Form";
import {dotsInputHandler} from "../../lib/dotsInputHandler";
import {useDispatch, useSelector} from "react-redux";
import {calcRadius} from "../../lib/calcRadius";
import {drawAllLines} from "../../lib/drawAllLines";
const ImageUploadBlock = ({errors, register}) => {
    const selectedImage = useImage()
    const dots = useSelector(state => state.task.coordinates)
    const percent = useSelector(state => state.task.percent)
    const canvasRef = useRef(null)
    const backgroundCss = {
        backgroundImage: `${selectedImage ?`url(${URL.createObjectURL(selectedImage)})`: `null` }`,
        backgroundRepeat:  `no-repeat`,
        backgroundSize: `550px 350px`
    }
    const dispatch = useDispatch()
    const [lowerDots, highDots] = calcRadius(dots, percent)
    console.log(lowerDots, ': lower ', highDots, ': high')
    useEffect(() => {
        if(canvasRef.current){
            const context = canvasRef.current.getContext("2d");
            const base_image = new Image();

            base_image.src = selectedImage.name;
            base_image.onload = function(){
                context.drawImage(selectedImage.name, 0, 0);
            }
            drawAllLines(context, dots, lowerDots, highDots)
        }

    }, [canvasRef, dots]);
    console.log(selectedImage)
    return (
        <div className={classes.wrapper}>
            {selectedImage && (
                <div>
                    <Form.Group className="mb-3">
                        <canvas ref={canvasRef}
                                style={backgroundCss} width={"550px"}
                                height={"350px"}>
                        </canvas>
                    </Form.Group>
                    <DotsInput register={register} errors={errors} handler={dotsInputHandler} dispatch={dispatch}/>
                </div>
            )}
        </div>
    );
};

export default ImageUploadBlock;