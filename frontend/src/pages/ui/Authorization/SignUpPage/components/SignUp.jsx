import React from 'react';
import {useForm} from "react-hook-form";
import classes from "./SignUp.module.scss";
import CustomForm from "../../UI/Form/CustomForm";
import InputEmail from "../../UI/InputEmail/InputEmail";
import PasswordInput from "../../UI/PasswordInput/PasswordInput";
import InputName from "../UI/InputName/InputName";
import {signUpThunk} from "../../../../../store/slices/userSlice";
import {useDispatch} from "react-redux";
const SignUp = () => {
    const {
        register,
        formState:{
            errors,
            isValid
        },
        handleSubmit,
        reset,
    } = useForm({mode:'onChange'})
    const dispatch = useDispatch()
    const submitHandler = (data) =>{
        dispatch(signUpThunk(data))
        reset()
    }
    return (
        <div className={classes.wrapper}>
            <CustomForm btnText={'Sign Up'}
                        submitHandler={submitHandler}
                        handlerSubmit={handleSubmit}
                        isValid={isValid}
                        formCl={classes.form}
            >
                <InputName label={'First name'} errors={errors} register={register} name={'firstName'}/>
                <InputName label={'Second name'} errors={errors} register={register} name={'secondName'}/>
                <InputEmail errors={errors} register={register}/>
                <PasswordInput errors={errors} register={register}/>
            </CustomForm>
        </div>
    );
};

export default SignUp;