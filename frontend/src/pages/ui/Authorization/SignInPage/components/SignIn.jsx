import React from 'react';
import CustomForm from '../../UI/Form/CustomForm';
import { useForm } from 'react-hook-form';
import classes from './SignIn.module.scss';
import InputEmail from '../../UI/InputEmail/InputEmail';
import PasswordInput from '../../UI/PasswordInput/PasswordInput';
import { signInThunk } from '../../../../../entities/model/store/slices/userSlice';
import { useDispatch } from 'react-redux';
import AuthBlock from '../../../../../widgets/ui/AuthBlock/AuthBlock';
import { useNavigate } from 'react-router-dom';

const SignIn = () => {
  const {
    register,
    getValues,
    formState: {
      errors,
      isValid,
    },
    handleSubmit,
    reset,
  } = useForm({ mode: 'onChange' });
  const dispatch = useDispatch();
  const nav = useNavigate();

  const submitHandler = (data) => {
    dispatch(signInThunk(data));
    setTimeout(() => {
      reset();
      nav('/tasks');
    }, 400);
  };

  return (
    <div className={classes.wrapper}>
      <AuthBlock headerText={'Авторизация'} helperText={''} auth={true}>
        <CustomForm
          btnText={'Sign In'}
          submitHandler={submitHandler}
          handlerSubmit={handleSubmit}
          isValid={isValid}
          formCl={classes.form}
        >
          <InputEmail errors={errors} register={register} />
          <PasswordInput errors={errors} register={register} />
        </CustomForm>
      </AuthBlock>
    </div>
  );
};

export default SignIn;