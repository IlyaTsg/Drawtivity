import React, { useEffect, useRef, useState } from 'react';
import classes from './TaskBlock.module.scss';
import { useDispatch, useSelector } from 'react-redux';
import TaskSolution from '../../../entities/ui/TaskSolution/TaskSolution';
import {
  createSolution,
  getTasksById,
  setCoordinates,
  setProcCorrectSolution,
} from '../../../entities/model/store/slices/tasksSlice';
import SubmitButton from '../../../shared/ui/SubmitButton/SubmitButton';
import { useParams } from 'react-router-dom';
import { Box, Typography } from '@mui/material';
import { cleanAll, createGrid } from '../../../features/constructor/lib/backgroundGrid';
import Button from '@mui/material/Button';

const TaskBlock = () => {
  const { id } = useParams();
  const task = useSelector(state => state.task.actualTask);
  const [context, setContext] = useState();
  const [newCoord, setNewCoord] = useState();
  const dispatch = useDispatch();
  let data = {};

  const value = useSelector(state => state.task.actualTask.procCorrectSolution);
  //const id = useSelector(state => state.actualTask);


  useEffect(() => {
    data = {
      task_id: +id, points: newCoord,
    };
  }, [newCoord]);

  useEffect(() => {
    dispatch(getTasksById(id));
  }, [id]);

  useEffect(() => {
    return () => {
      dispatch(setProcCorrectSolution());
    };
  }, []);
  const clickHandler = (event) => {
    event.stopPropagation();
    dispatch(createSolution(data));
  };
  return (<Box sx={{ paddingTop: 2 }} height={'100%'}>
    <Box display={'flex'} width={'100%'} justifyContent={'space-between'}>
      <Typography fontSize={'40px'} textAlign={'center'} mb={5}>Задача: {task.title}</Typography>
      {value || value === 0 ?
        <Box sx={{ borderRight: '5px solid red', height: '55px' }}>
          <Typography pt={1} fontSize={'1.7em'} textAlign={'center'} fontWeight={'bolder'} fontStyle={'italic'} pr={2}
                      sx={{ borderRight: '5px solid red' }}>Ваш
            ответ
            верен
            на: {value}%</Typography></Box> :
        <Box height={'40px'} width={'339.6'}></Box>}</Box>
    <div className={classes.wrapper}>
      <div className={classes.test}>
        <TaskSolution
          setCoord={setNewCoord}
          type={task.type}
          percentVal={task.deviation}
          line_color={task.lineColor}
          image={task.image}
          ctxSetter={setContext}
        />
        {/*task.type !== 'Linear' && <div>
          <SubmitButton text={'Добавить новую фигуру'} handler={(e) => {
            e.preventDefault();
            dispatch(setCoordinates(newCoord + '  '));
          }
          } />
        </div>
        */}

      </div>
      <Box sx={{ width: '450px' }} display={'flex'}
           flexDirection={'column'}
           justifyContent={'space-between'}
      >
        <Box width={'100%'} minHeight={'360px'} sx={{ border: '1px solid dimgray', borderRadius: '10px' }} pt={2}
             fontStyle={'italic'}>
          {task.description ? <> <Typography textAlign={'center'} variant={'h4'}>Условия задачи: </Typography>
            <Typography pl={1} fontSize={'1.4em'} color={'#3b3b3b'}> {task.description}
            </Typography> </> : 'Тестовый блок с описанием задачиfdvg fdg dfgdgdfgdfgdfgdfgfdgdf' + '  fddfgdf dfg df gdfg d gd gdfdfd fd fd fd d dfd d хзхзхзхз'}
        </Box>
        <Box display={'flex'} justifyContent={'center'} width={'100%'} mb={15}>
          <SubmitButton
            handler={clickHandler}
            text={'Проверить'}
            disabled={(!!data.points)}
          >
            Проверить
          </SubmitButton>
        </Box>
      </Box>
    </div>
  </Box>);
};

export default TaskBlock;