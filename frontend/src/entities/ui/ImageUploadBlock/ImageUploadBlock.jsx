import React, {useEffect, useRef} from 'react';
import useImage from "../../model/store/hooks/useImage";
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
        backgroundSize: `750px 550px`
    }
    const dispatch = useDispatch()
    const [lowerDots, highDots] = calcRadius(dots, percent)
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
    return (
        <div >
            {selectedImage && (
                <div>
                    <div className={classes.header}>Ваше изображение</div>
                    <Form.Group className="mb-3">
                        <div className={classes.wrapper}>
                            <canvas ref={canvasRef}
                                    style={backgroundCss} width={"750px"}
                                    height={"550px"}>
                            </canvas>
                        </div>
                    </Form.Group>
                    <DotsInput register={register} errors={errors} handler={dotsInputHandler} dispatch={dispatch}/>
                </div>
            )}
        </div>
    );
};

export default ImageUploadBlock;