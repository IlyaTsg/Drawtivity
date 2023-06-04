import React from 'react';
import CustomForm from "../../UI/Form/CustomForm";
import {useForm} from "react-hook-form";
import classes from "./SignIn.module.scss";
import InputEmail from "../../UI/InputEmail/InputEmail";
import PasswordInput from "../../UI/PasswordInput/PasswordInput";
import {signInThunk} from "../../../../../store/slices/userSlice";
import {useDispatch} from "react-redux";

const SignIn = () => {
    const {
        register,
        getValues,
        formState:{
            errors,
            isValid
        },
        handleSubmit,
        reset,
    } = useForm({mode:'onChange'})
    const dispatch = useDispatch()
    const submitHandler = (data) =>{
        dispatch(signInThunk(data))
        reset()
    }
    return (
        <div className={classes.wrapper}>
            <CustomForm btnText={'Sign In'}
                        submitHandler={submitHandler}
                        handlerSubmit={handleSubmit}
                        isValid={isValid}
                        formCl={classes.form}
            >
                <InputEmail errors={errors} register={register}/>
                <PasswordInput errors={errors} register={register}/>
            </CustomForm>
        </div>
    );
};

export default SignIn;