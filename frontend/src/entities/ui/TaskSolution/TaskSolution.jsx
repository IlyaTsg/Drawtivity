import React, { useEffect, useRef, useState } from 'react';
import { drawAllLines } from '../../lib/drawAllLines';
import useImage from '../../model/store/hooks/useImage';
import { useDispatch, useSelector } from 'react-redux';
import { dotsParsClick } from '../../lib/dotsParsClick';
import ImageBar from '../../../shared/Constructor/ui/ImageBar/ImageBar';
import classes from '../ImageUploadBlock/ImageUploadBlock.module.scss';
import ImageBarX from '../../../shared/Constructor/ui/ImageBarX/ImageBarX';
import Form from 'react-bootstrap/Form';
import { cleanAll, createGrid } from '../../../features/constructor/lib/backgroundGrid';
import { drawSolution } from '../../lib/drawLines';
import { setCoordinates } from '../../model/store/slices/tasksSlice';
import { dotsPars } from '../../lib/dotsPars';
import Button from '@mui/material/Button';

const TaskSolution = ({ setCoord, type, percentVal, line_color, image }) => {
  const selectedImage = useImage();
  const canvasRef = useRef(null);
  let context; //= canvasRef.current !== null ? canvasRef.current.getContext('2d') : null;
  //const [dots, setDots] = useState([' ']);
  const [dots, setDots] = useState([]);
  const dots2 = useSelector(state => state.task.coordinates);
  //src={`data:image/png;base64,${data?.image}`}
  const backgroundCss = {
    backgroundImage: `${image ? `url(data:image/png;base64,${image})` : `null`}`,
    backgroundRepeat: `no-repeat`,
    backgroundSize: `750px 550px`,
    objectFit: 'cover',
  };
  const dispatch = useDispatch();
  const [canvasContext, setCanvasContext] = useState(canvasRef.current !== null ? canvasRef.current.getContext('2d') : null);
  const clickHandler = (event) => {
    let x = event.pageX,
      y = event.pageY;
    setDots(prev => [...prev, (dotsParsClick(x - canvasRef.current.offsetLeft, y - canvasRef.current.offsetTop - 275))]);
    dispatch(setCoordinates(dots2.toString() + ` ${x - canvasRef.current.offsetLeft}:${y - canvasRef.current.offsetTop - 275}`));
    if (type === 'Linear') {
      drawSolution(canvasContext, dots, line_color, type);
      setCoord(dots);
    } else {
      drawAllLines(canvasContext, dots2, line_color, type);
      setCoord(dotsPars(dots2.toString().trimStart()));
    }

  };
  useEffect(() => {
    if (canvasRef.current) {
      const base_image = new Image();
      const context = canvasRef.current.getContext('2d');
      setCanvasContext(context);
      base_image.src = selectedImage.name;
      base_image.onload = function() {
        context.drawImage(selectedImage.name, 0, 0);
      };
      //console.log('test')
      createGrid(context);
      if (type === 'Linear') {
        drawSolution(context, dots, line_color, type);
        setCoord(dots);
      } else {
        drawAllLines(context, dots2, line_color, type);
        setCoord(dotsPars(dots2.toString().trimStart()));
      }
    }
  }, [canvasRef]);
  useEffect(() => {
    //console.log(2);
    //if (isNetting) createGrid(context);
    if (canvasRef.current && canvasContext) {
      if (type === 'Linear') {
        drawSolution(canvasContext, dots, line_color, type);
        setCoord(dots);
      } else {
        drawAllLines(canvasContext, dots2, line_color, type);
        console.log(dotsPars(dots2.toString()));
        setCoord(dotsPars(dots2.toString().trimStart()));
      }

    }

  }, [dots]);

  useEffect(() => {
    return () => {
      if (context !== null && context !== undefined) {
        cleanAll(context);
      }
      if (canvasContext !== null && canvasContext !== undefined) {
        cleanAll(canvasContext);
        setCanvasContext(null);
      }
      dispatch(setCoordinates([]));
    };
  }, []);
  return (
    <div className={classes.wrapSol}>
      <Form.Group className="mb-3">
        <div className={classes.image}>
          <ImageBar />
          <div>
            <canvas ref={canvasRef}
                    style={backgroundCss} width={'750px'}
                    height={'550px'} onClick={clickHandler}>
            </canvas>
            <ImageBarX />
          </div>
        </div>
      </Form.Group>
      {<div>
        <Button onClick={(e) => {
          e.preventDefault();
          if (type !== 'Linear') {
            dispatch(setCoordinates(''));
          } else {
            setCoord([]);
            setDots([]);
          }
          cleanAll(context || canvasContext);
          createGrid(context || canvasContext);
        }
        } variant="contained" size="large">Очистить решение</Button>
      </div>
      }
    </div>
  );
};

export default TaskSolution;