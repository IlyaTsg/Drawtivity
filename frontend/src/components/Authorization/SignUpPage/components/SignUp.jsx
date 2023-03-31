import React from 'react';
import {useForm} from "react-hook-form";
import classes from "../../SignUpPage/components/SignUp.module.scss";
import CustomForm from "../../UI/Form/CustomForm";
import InputEmail from "../../UI/InputEmail/InputEmail";
import PasswordInput from "../../UI/PasswordInput/PasswordInput";
import InputName from "../UI/InputName/InputName";
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
    const submitHandler = (data) =>{
        alert(JSON.stringify(data))
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