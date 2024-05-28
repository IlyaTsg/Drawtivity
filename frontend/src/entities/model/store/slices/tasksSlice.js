import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { TaskApi } from '../../../../pages/api/TaskApi';

export const createTask = createAsyncThunk('task/createTask', async (data) => {
  const response = await TaskApi.createTask(data);
  return response;
});

export const createSolution = createAsyncThunk('tasks/createSolution', async (data) => {
  const response = await TaskApi.createSolution(data);
  return response;
});

export const launchResponse = createAsyncThunk('tasks/response', async (data) => {
  const response = await TaskApi.ltiCheckRequest(data);
  return response;
});

export const getTasks = createAsyncThunk('task/getTasks', async () => {
  const tasks = await TaskApi.getTasks();
  return tasks;
});
export const getTasksById = createAsyncThunk('task/getTasksById', async (id) => {
  try {
    const tasks = await TaskApi.getTasksById(id);
    return tasks;
  } catch (e) {
    return e;
  }
});

export const ltiSolution = createAsyncThunk('task/ltiSolution', async (data) => {
  try {
    const solutionResponse = await TaskApi.ltiSolutionCheck(data);
    return solutionResponse;
  } catch (e) {
    console.log(e);
  }
});

const initialState = {
  title: '', description: '', category: '', type: 0, image: '', percent: 0, coordinates: [], tasks: [], actualTask: {
    title: '', description: '', procCorrectSolution: null,
  }, percentVal: 0, isnetting: false, lineColor: '', launchTask: {}, ltiStatus: '',
};

const tasksSlice = createSlice({
  name: 'tasksSlice', initialState, reducers: {
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
    setType(state, action) {
      state.type = action.payload;
    },
    setProcCorrectSolution(state) {
      state.actualTask.procCorrectSolution = undefined;
    },
    setLtiParam(state, action) {
      state.launchTask = action.payload;
    },
    setLtiStatus(state) {
      state.ltiStatus = '';
    },
  }, extraReducers: builder => {
    builder.addCase(getTasks.fulfilled, (state, action) => {
      state.tasks = action.payload;
    });

    builder.addCase(getTasksById.fulfilled, (state, action) => {
      state.actualTask.taskId = action.payload.task_id;
      state.actualTask.title = action.payload.title;
      state.actualTask.description = action.payload.description;
      state.actualTask.type = action.payload.type;
      state.actualTask.deviation = action.payload.deviation;
      state.actualTask.lineColor = action.payload.line_color;
      state.actualTask.image = action.payload.image;
    });
    builder.addCase(createSolution.fulfilled, (state, action) => {
      //console.log(action.payload.data, action.payload.data ? 'yes' : 'no');
      state.actualTask.procCorrectSolution = action.payload.data;
    });
    builder.addCase(launchResponse.pending, (state, action) => {
      state.ltiStatus = 'loading';
    });
    builder.addCase(launchResponse.rejected, (state, action) => {
      state.ltiStatus = 'error';
    });
    builder.addCase(launchResponse.fulfilled, (state, action) => {
      console.log(action.payload);
      state.ltiStatus = action.payload;
    });
    builder.addCase(ltiSolution.fulfilled, (state, action) => {
      console.log(action.payload);
      state.actualTask.procCorrectSolution = action.payload.data;
    });
  },
});

export default tasksSlice.reducer;
export const {
  setTask,
  setImage,
  setCoordinates,
  setPercent,
  setIsNetting,
  setLineColor,
  setType,
  setProcCorrectSolution,
  setLtiParam,
  setLtiStatus,
} = tasksSlice.actions;