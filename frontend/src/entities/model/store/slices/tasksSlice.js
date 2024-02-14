import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import {TaskApi} from '../../../../pages/api/TaskApi';

export const createTask = createAsyncThunk(
  'task/createTask',
  async (data) => {
    const response = await TaskApi.createTask(data);
    return response;
  },
);

export const createSolution = createAsyncThunk(
  'task/createSolution',
  async (data) => {
    const response = await TaskApi.createSolution(data);
    return response;
  },
);

export const getActualTask = createAsyncThunk(
  'task/getActualTask',
  async (id) => {
    const task = await TaskApi.getActualTask(id);
    return task;
  },
);

export const getTasks = createAsyncThunk(
  'task/getTasks',
  async () => {
    const tasks = await TaskApi.getTasks();
    return tasks;
  },
);

const initialState = {
  title: '',
  description: '',
  category: '',
  type: 0,
  image: '',
  percent: 0,
  coordinates: [],
  tasks: [],
  actualTask: {},
  percentVal: 0,
  isnetting: false,
  lineColor: '',
};

const tasksSlice = createSlice({
  name: 'tasksSlice',
  initialState,
  reducers: {
    setTask(state, action) {
      state.title = action.title;
      state.description = action.description;
      state.category = action.category;
      state.type = action.type;
      state.image = action.image;
      state.percent = action.percent;
    },
    setImage(state, action) {
      state.image = action.payload;
    },
    setCoordinates(state, action) {
      state.coordinates = action.payload;
    },
    setPercent(state, action) {
      state.percent = action.payload;
    },
    setIsNetting(state, action) {
      state.isNetting = action.payload;
    },
    setLineColor(state, action) {
      state.lineColor = action.payload;
    },
  },
  extraReducers: builder => {
    builder.addCase(getTasks.fulfilled, (state, action) => {
      state.tasks = action.payload;
    });
    builder.addCase(getActualTask.fulfilled, (state, action) => {
      state.actualTask = action.payload.task_id;
    });
    builder.addCase(createSolution.fulfilled, (state, action) => {
      state.percentVal = action.payload;
    });
  },
});

export default tasksSlice.reducer;
export const {setTask, setImage, setCoordinates, setPercent, setIsNetting, setLineColor} = tasksSlice.actions;