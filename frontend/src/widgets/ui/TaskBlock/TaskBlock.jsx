import React, {useEffect, useState} from 'react';
import classes from './TaskBlock.module.scss';
import {useDispatch, useSelector} from 'react-redux';
import TaskSolution from '../../../entities/ui/TaskSolution/TaskSolution';
import {createSolution} from '../../../entities/model/store/slices/tasksSlice';
import SubmitButton from '../../../shared/ui/SubmitButton/SubmitButton';

const TaskBlock = () => {
  const task = useSelector(state => state.task.actualTask);
  const [newCoord, setNewCoord] = useState();
  const dispatch = useDispatch();
  let data = {};
  const value = useSelector(state => state.task.percentVal);
  const id = useSelector(state => state.actualTask);
  useEffect(() => {
    data = {
      id: id,
      points: newCoord,
    };
    console.log(data);
  }, [newCoord]);

  const clickHandler = () => {
    dispatch(createSolution());
    console.log('hello');
  };
  return (
    <div className={classes.wrapper}>
      <div className={classes.test}>
        <TaskSolution setCoord={setNewCoord} />
        <div>
          {task.description ? task.description : 'Тестовый блок с описанием задачиfdvg fdg dfgdgdfgdfgdfgdfgfdgdf  fddfgdf dfg df gdfg d gd gdfdfd fd fd fd d dfd d хзхзхзхз'}
        </div>
        <SubmitButton
          handler={clickHandler}
          text={'Проверить'}
        >
          Проверить
        </SubmitButton>
      </div>
      {value ? <div>{value}</div> : <div>Итоговая оценка: 5</div>}
    </div>
  );
};

export default TaskBlock;