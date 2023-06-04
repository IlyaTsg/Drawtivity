import {configureStore} from "@reduxjs/toolkit";
import userSlice from "./slices/userSlice";
import tasksSlice from "./slices/tasksSlice";

const store = configureStore({
        reducer: {
            user: userSlice,
            task: tasksSlice
        }
    }
)

export default store