import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import UserApi from "../../../../pages/api/UserApi";

export const signInThunk = createAsyncThunk(
    "user/signInThunk",
    async function(reqBody){
       //console.log(reqBody)
        const id = await UserApi.singIn(reqBody);
        return id.data
    }
)

export const signUpThunk = createAsyncThunk(
    "user/signUpThunk",
    async function(reqBody){
        //console.log(reqBody)
        const id = await UserApi.signUp(reqBody);
        return id.data
    }
)


const initialState={
    email: null,
    token: null,
    firstname: '',
    lastname:'',
    id: null
}

const userSlice = createSlice({
    name:'userSlice',
    initialState,
    reducers:{
        setUser(state, action){
            state.id = action.payload.id
            state.email = action.payload.email
            state.token = action.payload.token
        },
        removeUser(state){
            state.email = null;
            state.token = null;
            state.id = null;
        }
    },
    extraReducers: (builder) => {
        builder.addCase(signInThunk.pending, state => {

        })
        builder.addCase(signInThunk.fulfilled, (state, action) =>{
            console.log(action.payload)
            state.id = action.payload
        })
        builder.addCase(signUpThunk.pending, (state, action) =>{
            console.log(action)
        })
        builder.addCase(signUpThunk.fulfilled, (state, action) => {
            console.log(action.payload)
            state.id = action.payload
        })
    }
})

export default userSlice.reducer
export const {removeUser, setUser} = userSlice.actions