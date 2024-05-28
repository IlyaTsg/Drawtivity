import classes from './Constructor.module.scss';
import { Controller, useForm } from 'react-hook-form';
import NumberInput from '../../../../shared/ui/NumberInput/NumberInput';
import FileInput from '../../../../shared/ui/FileInput/FileInput';
import useImage from '../../../../entities/model/store/hooks/useImage';
import { useDispatch, useSelector } from 'react-redux';
import {
  createTask, getTasks, setIsNetting, setLineColor, setPercent, setType,
} from '../../../../entities/model/store/slices/tasksSlice';
import TaskCreate from '../../../../widgets/constructor/ui/TaskCreate';
import Header from '../../../../entities/constructor/ui/Header';
import CreateTask from '../../../../features/constructor/ui/CreateTask';
import ArrowDownwardIcon from '@mui/icons-material/ArrowDownward';
import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  Box,
  Checkbox,
  FormControl,
  FormControlLabel,
  FormGroup,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
} from '@mui/material';
import { Sketch } from '@uiw/react-color';
import { useState } from 'react';
import { dotsParsClick } from '../../../../entities/lib/dotsParsClick';
import { dotsPars } from '../../../../entities/lib/dotsPars';
import { useNavigate } from 'react-router-dom';

const Constructor = () => {
  const selectedImage = useImage();
  const dispatch = useDispatch();
  const {
    control, register, handleSubmit, formState, getValues, setValue,
  } = useForm({
    defaultValues: {
      title: '', category: '', type: 'Точки', description: '', color: '', isNetting: false,
    },
  });
  const [color, setColor] = useState(getValues('color').hex);

  const coordinates = useSelector(state => state.task.coordinates);
  const colorLine = useSelector(state => state.task.lineColor);
  const id = useSelector(state => state.user.id);
  const nav = useNavigate();

  const addTask = (data) => {

    const tempTask = {
      'owner_id': id,
      'title': data.title,
      'description': data.description,
      'category': 'one',
      'type': data.type === 'Площадь' ? 'Overlap' : 'Linear',
      'line_color': colorLine ? colorLine : '#000000',
      'image': selectedImage,
      'deviation': +data.deviation,
      'points': dotsPars(coordinates.toString().trimStart()),
      'fill_color': data.type === 'Площадь' ? colorLine : '#FF0000',
    };

    dispatch(createTask(tempTask));
    setTimeout(() => {
      dispatch(getTasks());
      nav('/tasks');
    }, 400);

  };
  const percentAdd = (event) => {
    dispatch(setPercent(event.target.value));
  };
  return (
    <form className={classes.constructorPage} onSubmit={handleSubmit(addTask)}>
      <Header />
      <Box display={'flex'} width={'100%'} justifyContent={'space-around'} mt={2} mb={3}>
        <Controller
          name="title"
          control={control}
          render={({ field }) => <TextField label={'Название задачи'}  {...field} sx={{ width: '400px' }} />}
        />
        <Controller
          name="description"
          control={control}
          render={({ field }) => <TextField
            label={'Описание задачи'}
            {...field}
            sx={{ width: '400px' }}
            multiline
            rows={3} />}
        />
      </Box>
      <Box display={'flex'} width={'100%'} justifyContent={'space-around'} mt={2} mb={3}>
        <Controller
          name="category"
          control={control}
          render={({ field }) => (<FormControl sx={{ width: '400px' }}>
            <InputLabel id="demo-simple-select-label">Категория</InputLabel>
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              label="Категория"
              {...field}
            >
              {['Математика', 'Медицина', 'География'].map(item => <MenuItem value={item}
                                                                             key={item}>{item}</MenuItem>)}
            </Select>
          </FormControl>)}
        />
        <Controller
          name="type"
          control={control}
          render={({ field }) => (<FormControl sx={{ width: '400px' }}>
            <InputLabel id="demo-simple-select-label">Тип</InputLabel>
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              label="Тип"
              {...field}
              //value={e.target.value}
              onChange={(e) => {
                //dispatch(setType(e.target.value));
                dispatch(setType(e.target.value));
                setValue('type', e.target.value);
              }}
            >
              {['Площадь', 'Точки'].map((item, ind) => <MenuItem
                value={item}
                key={ind}
              >
                {item}
              </MenuItem>)}
            </Select>
          </FormControl>)}
        />
      </Box>
      <Box display={'flex'} width={'100%'} justifyContent={'space-around'}>
        <FileInput
          register={register}
          name={'image'}
        />
        <Controller
          name="color"
          control={control}
          render={({ field }) => (<Box sx={{ width: '400px', mt: 3 }}>
            <Accordion sx={{
              width: '400px', boxShadow: 'none', borderRadius: '4px', border: '1px solid #ced4da',
            }}>
              <AccordionSummary
                expandIcon={<ArrowDownwardIcon />}
                aria-controls="panel1-content"
                id="panel2-header"
              >
                <Typography>Цвет основной линии:</Typography>
              </AccordionSummary>
              <AccordionDetails sx={{ width: '400px', display: 'flex', justifyContent: 'center' }}>
                <Sketch
                  {...field}
                  onChange={(value) => dispatch(setLineColor(value.hex))}
                  style={{ width: 390 }}
                />
              </AccordionDetails>
            </Accordion>
          </Box>)}
        />
      </Box>
      <Box display={'flex'} width={'100%'} justifyContent={'space-around'} mt={3}>
        <NumberInput
          register={register}
          name={'deviation'}
          label={'Пример: 15'}
          inputHandler={percentAdd}
        />
        <Controller
          name="isNetting"
          control={control}
          render={({ field }) => (<Box>
            <Typography variant={'h7'}>Отображение сетки координат на изображении:</Typography>
            <FormGroup {...field} sx={{ width: '400px', pr: 20 }}
                       onClick={() => dispatch(setIsNetting(!getValues('isNetting')))}
            >
              <FormControlLabel required control={<Checkbox />} label="Наличие сетки"
                                labelPlacement="start" />
            </FormGroup>
          </Box>)}
        />
      </Box>
      <TaskCreate register={register} lineColor={getValues('color').hex} />
      <Box width={'100%'} display={'flex'} justifyContent={'center'} sx={{ pb: 3 }}>
        <CreateTask handleSubmit={handleSubmit(addTask)} />
      </Box>
    </form>);
};

export default Constructor;