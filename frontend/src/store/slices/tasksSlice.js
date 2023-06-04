import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {TaskApi} from "../../pages/api/TaskApi";

const initialState = {
    title: '',
    description: '',
    category: '',
    type: 0,
    image: '',
    percent: 0,
    coordinates: []
}
export const createTask = createAsyncThunk(
    "task/createTask",
    async (data)=> {
        console.log(data)
        const response = await TaskApi.createTask(data);
        return response
    }
)
const tasksSlice = createSlice({
    name: 'tasksSlice',
    initialState,
    reducers:{
        setTask(state, action){
            state.title = action.title;
            state.description = action.description;
            state.category = action.category;
            state.type = action.type;
            state.image = action.image;
            state.percent = action.percent;
        },
        setImage(state, action){
            state.image = action.payload
        },
        setCoordinates(state, action){
            state.coordinates = action.payload
        },
        setPercent(state, action){
            state.percent = action.payload
        }
    }
})

export default tasksSlice.reducer
export const {setTask, setImage, setCoordinates, setPercent} = tasksSlice.actions