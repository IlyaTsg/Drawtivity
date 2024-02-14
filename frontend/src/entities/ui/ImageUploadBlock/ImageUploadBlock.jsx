import React, {useEffect, useRef, useState} from 'react';
import useImage from '../../model/store/hooks/useImage';
import classes from './ImageUploadBlock.module.scss';
import DotsInput from '../../../shared/ui/DotsInput/DotsInput';
import Form from 'react-bootstrap/Form';
import {dotsInputHandler} from '../../lib/dotsInputHandler';
import {useDispatch, useSelector} from 'react-redux';
import {calcRadius} from '../../lib/calcRadius';
import {drawAllLines} from '../../lib/drawAllLines';
import ImageBar from '../../../shared/Constructor/ui/ImageBar/ImageBar';
import ImageBarX from '../../../shared/Constructor/ui/ImageBarX/ImageBarX';
import {dotsParsClick} from '../../lib/dotsParsClick';
import {setCoordinates} from '../../model/store/slices/tasksSlice';
import {cleanAll, createGrid} from '../../../features/constructor/lib/backgroundGrid';

const ImageUploadBlock = ({register}) => {
  const canvasRef = useRef(null);
  const selectedImage = useImage();
  const dots = useSelector(state => state.task.coordinates);
  const percent = useSelector(state => state.task.percent);
  const isNetting = useSelector(state => state.task.isNetting);
  const lineColor = useSelector(state => state.task.lineColor);
  const backgroundCss = {
    backgroundImage: `${selectedImage ? `url(${URL.createObjectURL(selectedImage)})` : `null`}`,
    backgroundRepeat: `no-repeat`,
    backgroundSize: `750px 550px`,
  };
  const dispatch = useDispatch();
  const [lowerDots, highDots] = calcRadius(dots, percent);
  let context = canvasRef.current !== null ? canvasRef.current.getContext('2d') : null;
  const clickHandler = (event) => {
    let x = event.pageX, y = event.pageY;
    //setDots([...dots, (dotsParsClick(x - canvasRef.current.offsetLeft, y-canvasRef.current.offsetTop - 275))])
    dispatch(setCoordinates([...dots, (dotsParsClick(x - canvasRef.current.offsetLeft, (y - canvasRef.current.offsetTop - 275)))]));
    //console.log(`${x - canvasRef.current.offsetLeft}:${y-canvasRef.current.offsetTop - 175}`);
  };

  useEffect(() => {
    if (canvasRef.current) {
      const base_image = new Image();
      base_image.src = selectedImage.name;
      base_image.onload = function() {
        context.drawImage(selectedImage.name, 0, 0);
      };
      //if (isNetting) createGrid(context);
      drawAllLines(context, dots, lowerDots, highDots, lineColor, percent);
    }
  }, [dots, lineColor, context]);
  useEffect(() => {
    if (canvasRef.current && context) {
      if (isNetting) {
        createGrid(context);
        drawAllLines(context, dots, lowerDots, highDots, lineColor);
      } else {
        cleanAll(context);
        drawAllLines(context, dots, lowerDots, highDots, lineColor);
      }
    }
  }, [isNetting, context]);
  return (<div>
    {selectedImage && (<div>
      <div className={classes.header}>Ваше изображение</div>
      <Form.Group className="mb-3">
        <div className={classes.image}>
          <ImageBar />
          {/*<div className={classes.wrapper}>*/}
          <div>
            <canvas
              ref={canvasRef}
              style={backgroundCss}
              width={'750px'}
              height={'550px'}
              onClick={clickHandler}>
            </canvas>
            <ImageBarX />
            {//</div>
            }
          </div>
        </div>
      </Form.Group>
      <DotsInput register={register} handler={dotsInputHandler} dispatch={dispatch} />
    </div>)}
  </div>);
};

export default ImageUploadBlock;