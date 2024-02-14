import React, {useEffect, useState} from 'react';
import Form from 'react-bootstrap/Form';
import {dotsInputHandler} from '../../../entities/lib/dotsInputHandler';
import classes from './DotsInput.module.scss';
import {useSelector} from 'react-redux';

const DotsInput = ({register, handler, dispatch}) => {
  const dots = useSelector(state => state.task.coordinates);
  const [dotsStr, setDotsStr] = useState('');
  useEffect(() => {
    //let str = dots.reduce((str, item) => str + ` ${item.x}: ${item.y}`, '');
    //setDotsStr(str);
  }, [dots]);

  return (
    <Form.Group className="mb-3">
      <Form.Label>
        <div className={classes.label}>Введите координаты точек</div>
      </Form.Label>
      <Form.Control
        type="text"
        as="textarea" rows={2}
        placeholder="0:0 11:33"
        value={dots.toString()}
        {...register('dots', {
          required: 'Поле обязательно для заполнения',
          pattern: {
            //value: /^\w:\w$/,
            message: 'Не валидные координаты',
          },
        })}
        onChange={(event) => handler(event, dispatch)}
      />

    </Form.Group>
  );
};

export default DotsInput;