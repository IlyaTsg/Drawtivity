import {configureStore} from "@reduxjs/toolkit";
import userSlice from "./slices/userSlice";

const store = configureStore({
        reducer: {
            userSlice: userSlice
        }
    }
)

export default store