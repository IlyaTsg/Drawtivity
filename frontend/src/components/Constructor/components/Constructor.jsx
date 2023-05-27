import React from 'react';
import classes from './Constructor.module.scss'
import CustomForm from "../../Authorization/UI/Form/CustomForm";
import {useForm} from "react-hook-form";
import InputName from "../../Authorization/SignUpPage/UI/InputName/InputName";
import CustomTextArea from "../../Authorization/SignUpPage/UI/CustomTextArea/CustomTextArea";
import NumberInput from "../../../shared/ui/NumberInput/NumberInput";
import FileInput from "../../../shared/ui/FileInput/FileInput";
import CustomSelect from "../../../shared/ui/CustomSelect/CustomSelect";
const Constructor = () => {
    const {
        register,
        formState:{
            errors,
            isValid
        },
        handleSubmit,
        reset,
    } = useForm({mode:'onChange'})
    const addTask = (e) => {

    }
    return (
        <div className={classes.wrapper}>
            <CustomForm formCl={classes.form} handlerSubmit={handleSubmit}
                        btnText={'Добавить задачу'}
                        isValid={isValid} submitHandler={addTask}>
                <InputName label={'Название задачи'} errors={errors} register={register} name={'Название'}/>
                <CustomTextArea title={'Описание задачи'} placeholder={'Описание'}/>
                <CustomSelect title={'Выберите категорию задачи'} optionName={"Категория"}/>
                <CustomSelect title={'Выберите тип задачи'} optionName={"Тип задачи"}/>
                <FileInput/>
                <NumberInput />

            </CustomForm>
        </div>
    );
};

export default Constructor;