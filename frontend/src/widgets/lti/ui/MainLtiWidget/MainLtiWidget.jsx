import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import {
  createSolution, getTasksById, launchResponse, ltiSolution, setLtiParam, setLtiStatus, setProcCorrectSolution,
} from '../../../../entities/model/store/slices/tasksSlice';
import { Box, Typography } from '@mui/material';
import classes from '../../../ui/TaskBlock/TaskBlock.module.scss';
import TaskSolution from '../../../../entities/ui/TaskSolution/TaskSolution';
import SubmitButton from '../../../../shared/ui/SubmitButton/SubmitButton';

const MainLtiWidget = () => {
  const { id } = useParams();
  const task = useSelector(state => state.task.actualTask);
  const ltiStatus = useSelector(state => state.task.ltiStatus);
  const [reactLtiStatus, setReactLtiStatus] = useState(ltiStatus);
  const [newCoord, setNewCoord] = useState();
  let data = {};
  const value = useSelector(state => state.task.actualTask.procCorrectSolution);
  const dispatch = useDispatch();
  const ltiParam = useSelector(state => state.task.launchTask);

  useEffect(() => {
    const socket = new WebSocket('ws://localhost:4000');

    socket.onopen = () => {
      console.log('WebSocket connection established with ws://localhost:4000: 332');
    };

    socket.onmessage = (event) => {
      const data = JSON.parse(event.data);
      dispatch(setLtiParam(data.message));
      dispatch(launchResponse(data.message));
    };

    return () => {
      socket.close();
    };
  }, []);

  console.log(ltiParam);
  useEffect(() => {
    setReactLtiStatus(ltiStatus);
  }, [ltiStatus]);
  useEffect(() => {
    data = {
      outcome_service_url: ltiParam.lis_outcome_service_url,
      oauth_consumer_key: ltiParam.oauth_consumer_key,
      lis_result_sourcedid: ltiParam.lis_result_sourcedid,
      task_id: +id,
      points: newCoord,
    };
  }, [newCoord]);

  useEffect(() => {
    return () => {
      dispatch(setProcCorrectSolution());
    };
  }, []);
  /*useEffect(() => {
    dispatch(getTasksById(id));
  }, [id]);*/
  const clickHandler = (event) => {
    event.stopPropagation();
    dispatch(ltiSolution(data));
  };
  useEffect(() => {
    if (reactLtiStatus.data === 'Lti veirification success!') {
      dispatch(getTasksById(id));
    }
  }, [reactLtiStatus.data]);

  if (ltiStatus === 'loading') {
    console.log(ltiStatus);
    return (
      <Box height={'calc(100vh - 64px)'} width={'100%'} display={'flex'} justifyContent={'center'} alignItems={'center'}
      >
        <Typography variant={'h4'}>Загрузка...</Typography>
      </Box>
    );
  }
  if (ltiStatus === 'error') {
    return (
      <Box height={'calc(100vh - 64px)'} width={'100%'} display={'flex'} justifyContent={'center'} alignItems={'center'}
      >
        <Typography variant={'h4'}>Ошибка подключения по lti</Typography>
      </Box>
    );
  }
  console.log(ltiStatus);
  return (
    <>
      {ltiStatus.data === 'Lti veirification success!' &&
        <Box sx={{ paddingTop: 2 }} height={'100%'}>
          <Box display={'flex'} width={'100%'} justifyContent={'space-between'}>
            <Typography fontSize={'40px'} textAlign={'center'} mb={5}>Задача: {task.title}</Typography>
            {value || value === 0 ?
              <Box sx={{ borderRight: '5px solid red', height: '55px' }}>
                <Typography
                  pt={1}
                  fontSize={'1.7em'}
                  textAlign={'center'}
                  fontWeight={'bolder'}
                  fontStyle={'italic'}
                  pr={2}
                >
                  Ваш ответ верен на: {value}%
                </Typography>
              </Box> : <Box height={'40px'} width={'339.6'}></Box>}</Box>
          <div className={classes.wrapper}>
            <div className={classes.test}>
              <TaskSolution
                setCoord={setNewCoord}
                type={task.type}
                percentVal={task.deviation}
                line_color={task.lineColor}
                image={task.image}
              />
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
        </Box>} </>);
};

export default MainLtiWidget;