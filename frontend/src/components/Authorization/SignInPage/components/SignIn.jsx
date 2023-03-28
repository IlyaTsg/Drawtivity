import React from 'react';
import CustomForm from "../../UI/Form/CustomForm";
import {useForm} from "react-hook-form";
import classes from "./SignIn.module.scss";
import InputEmail from "../../UI/InputEmail/InputEmail";
import PasswordInput from "../../UI/PasswordInput/PasswordInput";

const SignIn = () => {
    const {
        register,
        formState:{
            errors,
            isValid
        },
        handleSubmit,
        reset,
    } = useForm({mode:'onChange'})
    const submitHandler = (data) =>{
        alert(JSON.stringify(data))
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