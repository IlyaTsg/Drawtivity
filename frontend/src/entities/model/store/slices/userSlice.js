import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import UserApi from '../../../../pages/api/UserApi';
import { redirect } from 'react-router-dom';

export const signInThunk = createAsyncThunk(
  'user/signInThunk',
  async function(reqBody) {
    try {
      const response = await UserApi.singIn(reqBody);
      localStorage.setItem('token', response.token);
      return response;
    } catch (e) {
      alert('error');
      return 'ew';
    }
  },
);

export const signUpThunk = createAsyncThunk(
  'user/signUpThunk',
  async function(reqBody) {
    //console.log(reqBody)
    try {
      const response = await UserApi.signUp(reqBody);
      return response;
    } catch (e) {
      alert('error');
      return 'fs';
    }

  },
);


const initialState = {
  email: null,
  token: localStorage.key('token'),
  firstname: '',
  lastname: '',
  id: null,
  isLoading: false,
};

const userSlice = createSlice({
  name: 'userSlice',
  initialState,
  reducers: {
    setUser(state, action) {
      state.user_id = action.payload.id;
      state.email = action.payload.email;
      state.token = action.payload.token;
    },
    removeUser(state) {
      state.email = null;
      state.token = null;
      state.id = null;
      localStorage.removeItem('token');
    },
  },
  extraReducers: (builder) => {
    builder.addCase(signInThunk.pending, state => {
      state.isLoading = false;
    });
    builder.addCase(signInThunk.fulfilled, (state, action) => {
      state.token = action.payload.token;
      state.id = action.payload.user_id;
      localStorage.setItem('token', action.payload.token);
      state.isLoading = true;
    });
    builder.addCase(signUpThunk.pending, (state, action) => {

    });
    builder.addCase(signUpThunk.fulfilled, (state, action) => {
      state.token = action.payload.token;
      state.id = action.payload.user_id;
      localStorage.setItem('token', action.payload.token);
      redirect('/tasks');
    });
  },
});

export default userSlice.reducer;
export const { removeUser, setUser } = userSlice.actions;