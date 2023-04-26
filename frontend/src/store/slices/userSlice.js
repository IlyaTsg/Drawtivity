import {createSlice} from "@reduxjs/toolkit";

const initialState={
    email: null,
    token: null,
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
    }
})

export default userSlice.reducer
export const {removeUser, setUser} = userSlice.actions