import React, {useEffect, useRef, useState} from 'react';
import {drawAllLines} from '../../lib/drawAllLines';
import useImage from '../../model/store/hooks/useImage';
import {useDispatch} from 'react-redux';
import {dotsParsClick} from '../../lib/dotsParsClick';
import ImageBar from '../../../shared/Constructor/ui/ImageBar/ImageBar';
import classes from '../ImageUploadBlock/ImageUploadBlock.module.scss';
import ImageBarX from '../../../shared/Constructor/ui/ImageBarX/ImageBarX';
import Form from 'react-bootstrap/Form';
import {createGrid} from '../../../features/constructor/lib/backgroundGrid';

const TaskSolution = ({setCoord}) => {
  const selectedImage = useImage();
  const [dots, setDots] = useState([]);
  const canvasRef = useRef(null);
  const backgroundCss = {
    backgroundImage: `${selectedImage ? `url(${URL.createObjectURL(selectedImage)})` : `null`}`,
    backgroundRepeat: `no-repeat`,
    backgroundSize: `750px 550px`,
    objectFit: 'cover',
  };
  const dispatch = useDispatch();
  const clickHandler = (event) => {
    let x = event.pageX,
      y = event.pageY;
    setDots([...dots, (dotsParsClick(x - canvasRef.current.offsetLeft, y - canvasRef.current.offsetTop - 275))]);
    //console.log(`${x - canvasRef.current.offsetLeft}:${y-canvasRef.current.offsetTop - 175}`);
  };
  useEffect(() => {
    if (canvasRef.current) {
      const context = canvasRef.current.getContext('2d');
      const base_image = new Image();

      base_image.src = selectedImage.name;
      base_image.onload = function() {
        context.drawImage(selectedImage.name, 0, 0);
      };
      //console.log('test')
      createGrid(context);
      drawAllLines(context, dots);
      setCoord(dots);
    }

  }, [canvasRef, dots]);
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
    </div>
  );
};

export default TaskSolution;