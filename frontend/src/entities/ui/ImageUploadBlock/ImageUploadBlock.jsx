import React, {useEffect, useRef} from 'react';
import useImage from '../../model/store/hooks/useImage';
import classes from './ImageUploadBlock.module.scss';
import DotsInput from '../../../shared/ui/DotsInput/DotsInput';
import Form from 'react-bootstrap/Form';
import {dotsInputHandler} from '../../lib/dotsInputHandler';
import {useDispatch, useSelector} from 'react-redux';
import {drawAllLines} from '../../lib/drawAllLines';
import ImageBar from '../../../shared/Constructor/ui/ImageBar/ImageBar';
import ImageBarX from '../../../shared/Constructor/ui/ImageBarX/ImageBarX';
import {setCoordinates} from '../../model/store/slices/tasksSlice';
import {cleanAll, createGrid} from '../../../features/constructor/lib/backgroundGrid';
import {Box} from '@mui/material';
import SubmitButton from '../../../shared/ui/SubmitButton/SubmitButton';


const ImageUploadBlock = ({register}) => {
    const canvasRef = useRef(null);
    const selectedImage = useImage();
    const dots = useSelector(state => state.task.coordinates);
    const percent = useSelector(state => state.task.percent);
    const isNetting = useSelector(state => state.task.isNetting);
    const lineColor = useSelector(state => state.task.lineColor);
    const type = useSelector(state => state.task.type);
    const dispatch = useDispatch();

    let context = canvasRef.current !== null ? canvasRef.current.getContext('2d') : null;
    const clickHandler = (event) => {
      event.preventDefault();
      event.stopPropagation();
      let x = event.pageX, y = event.pageY;
      //setDots([...dots, (dotsParsClick(x - canvasRef.current.offsetLeft, y-canvasRef.current.offsetTop - 275))])
      //console.log([dots.toString() + ` ${x - canvasRef.current.offsetLeft}:${y - canvasRef.current.offsetTop - 275}`], 76);
      dispatch(setCoordinates(dots.toString() + ` ${x - canvasRef.current.offsetLeft}:${y - canvasRef.current.offsetTop - 275}`));
      //console.log(`${x - canvasRef.current.offsetLeft}:${y-canvasRef.current.offsetTop - 175}`);
    };

    useEffect(() => {
      if (canvasRef.current) {
        const base_image = new Image();
        base_image.src = selectedImage.name;
        base_image.onload = function() {
          context.drawImage(selectedImage.name, 0, 0);
        };
      }
    }, []);
    useEffect(() => {
      //console.log(2);
      //if (isNetting) createGrid(context);
      if (canvasRef.current && context) {
        if (isNetting)
          createGrid(context);
        else cleanAll(context);

        drawAllLines(context, dots, lineColor, percent, type);
      }

    }, [dots, lineColor, context, isNetting]);

    useEffect(() => {
      dispatch(setCoordinates(' '));
      if (canvasRef.current && context) {
        cleanAll(context);
        drawAllLines(context, dots, lineColor, percent, type);
      }

    }, [type]);

    const backgroundCss = {
      backgroundImage: `${selectedImage ? `url(${URL.createObjectURL(selectedImage)})` : `null`}`,
      backgroundRepeat: `no-repeat`,
      backgroundSize: `750px 550px`,
    };
    return (<div>
      {selectedImage && (<div>
        <div className={classes.header}>Ваше изображение</div>
        <Box display={'flex'} columnGap={10}>
          <Form.Group className="mb-3">
            <div className={classes.image}>
              <ImageBar />
              {/*<div className={classes.wrapper}>*/}
              <div key={'3132212'}>
                <canvas
                  ref={canvasRef}
                  style={backgroundCss}
                  width={'750px'}
                  height={'550px'}
                  onClick={clickHandler}
                  key={'43412121'}
                >
                </canvas>
                <ImageBarX />
                {//</div>
                }
              </div>
            </div>
          </Form.Group>
          <div>
            <SubmitButton text={'Добавить новую фигуру'} handler={(e) => {
              e.preventDefault();
              dispatch(setCoordinates(dots + '  '));
            }
            } />
          </div>
        </Box>
        <DotsInput register={register} handler={dotsInputHandler} dispatch={dispatch} />
      </div>)
      }
    </div>);
  }
;

export default ImageUploadBlock;