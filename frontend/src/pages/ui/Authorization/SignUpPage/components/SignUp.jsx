import { useForm } from 'react-hook-form';
import classes from './SignUp.module.scss';
import CustomForm from '../../UI/Form/CustomForm';
import InputEmail from '../../UI/InputEmail/InputEmail';
import PasswordInput from '../../UI/PasswordInput/PasswordInput';
import InputName from '../UI/InputName/InputName';
import { removeUser, signUpThunk } from '../../../../../entities/model/store/slices/userSlice';
import { useDispatch } from 'react-redux';
import AuthBlock from '../../../../../widgets/ui/AuthBlock/AuthBlock';
import { useNavigate } from 'react-router-dom';

const SignUp = () => {
  const {
    register,
    formState: {
      errors, isValid,
    },
    handleSubmit,
    reset,
  } = useForm({ mode: 'onChange' });
  const dispatch = useDispatch();
  const nav = useNavigate();

  const submitHandler = (data) => {
    dispatch(signUpThunk(data));
    setTimeout(() => {
      reset();
      nav('/tasks');
    }, 400);
  };
  return (
    <div className={classes.wrapper}>
      <AuthBlock
        headerText={'Регистрация'}
        helperText={'Забыли пароль?'}
        auth={false}
      >
        <CustomForm
          btnText={'Sign Up'}
          submitHandler={submitHandler}
          handlerSubmit={handleSubmit}
          isValid={isValid}
          formCl={classes.form}
        >
          <InputName
            label={'First name'}
            errors={errors}
            register={register}
            name={'firstname'}
          />
          <InputName
            label={'Second name'}
            errors={errors}
            register={register}
            name={'lastname'}
          />
          <InputEmail errors={errors} register={register} />
          <PasswordInput errors={errors} register={register} />
        </CustomForm>
      </AuthBlock>
    </div>
  );
};

export default SignUp;
