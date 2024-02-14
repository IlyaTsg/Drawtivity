import React, {useEffect, useState} from 'react';
import classes from './TaskBlock.module.scss';
import {useDispatch, useSelector} from 'react-redux';
import TaskSolution from '../../../entities/ui/TaskSolution/TaskSolution';
import {createSolution, getTasksById} from '../../../entities/model/store/slices/tasksSlice';
import SubmitButton from '../../../shared/ui/SubmitButton/SubmitButton';
import {useParams} from 'react-router-dom';
import {Box, Typography} from '@mui/material';

const TaskBlock = () => {
  const {id} = useParams();
  const task = useSelector(state => state.task.actualTask);
  const [newCoord, setNewCoord] = useState();
  const dispatch = useDispatch();
  let data = {};
  const value = useSelector(state => state.task.percentVal);
  //const id = useSelector(state => state.actualTask);
  console.log(id);
  useEffect(() => {
    data = {
      task_id: id,
      points: newCoord,
    };
  }, [newCoord]);
  useEffect(() => {
    dispatch(getTasksById(id));
  }, [id]);
  const clickHandler = (event) => {
    event.stopPropagation();
    dispatch(createSolution(data));
  };
  return (
    <Box pt={5}>
      <Typography vatiant={'h5'} textAlign={'center'}>{task.title}</Typography>
      <div className={classes.wrapper}>
        <div className={classes.test}>
          {value ? <Typography mb={4}>Ваш результат: {value}</Typography> :
            <Typography mb={4}>Итоговая оценка: 5</Typography>}
          <TaskSolution setCoord={setNewCoord} />
        </div>
        <Box sx={{maxWidth: '500px'}}>
          <Box width={'100%'} mt={6}>
            {task.description ? <Typography textAlign={'center'}>
              {task.description}
            </Typography> : 'Тестовый блок с описанием задачиfdvg fdg dfgdgdfgdfgdfgdfgfdgdf' + '  fddfgdf dfg df gdfg d gd gdfdfd fd fd fd d dfd d хзхзхзхз'}
          </Box>
          <Box mt={5} display={'flex'} justifyContent={'center'} width={'100%'}>
            <SubmitButton
              handler={clickHandler}
              text={'Проверить'}
            >
              Проверить
            </SubmitButton>
          </Box>
        </Box>
      </div>
    </Box>);
};

export default TaskBlock;