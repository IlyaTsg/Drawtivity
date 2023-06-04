import React from 'react';
import classes from './Constructor.module.scss'
import CustomForm from "../../Authorization/UI/Form/CustomForm";
import {useForm} from "react-hook-form";
import InputName from "../../Authorization/SignUpPage/UI/InputName/InputName";
import CustomTextArea from "../../Authorization/SignUpPage/UI/CustomTextArea/CustomTextArea";
import NumberInput from "../../../../shared/ui/NumberInput/NumberInput";
import FileInput from "../../../../shared/ui/FileInput/FileInput";
import CustomSelect from "../../../../shared/ui/CustomSelect/CustomSelect";
import ImageUploadBlock from "../../../../entities/ui/ImageUploadBlock/ImageUploadBlock";
import classNames from "classnames";
import useImage from "../../../../store/hooks/useImage";
import {useDispatch, useSelector} from "react-redux";
import {createTask, setPercent} from "../../../../store/slices/tasksSlice";
import {signInThunk} from "../../../../store/slices/userSlice";
const Constructor = () => {
    const selectedImage = useImage()
    const dispatch = useDispatch()
    console.log(selectedImage.payload !== null,selectedImage.payload === null || selectedImage.payload === '', selectedImage)
    const classname = classNames(classes.wrapper, {
        [classes.imagesActive]: selectedImage.payload !== null,
        [classes.imagesNotActive]: selectedImage.payload === null || selectedImage === ''
    })
    const {
        register,
        getValues,
        formState:{
            errors,
            isValid
        },
        handleSubmit,
        reset,
    } = useForm({
        mode:'onChange',
        defaultValues: {
            title: ""
        }})
    const image = useSelector(state => state.task.image)
    const coordinates = useSelector(state => state.task.coordinates)
    const addTask = (data) => {
        const task = data
        task["image"] = image.payload
        task["dots"] = coordinates
        //console.log(JSON.stringify(data))
        console.log("task: ", task)
        dispatch(createTask(task))
    }
    const percentAdd = (event) => {
        dispatch(setPercent(event.target.value))
    }
    return (
        <div >
            <CustomForm formCl={classes.form} handlerSubmit={handleSubmit}
                        btnText={'Добавить задачу'}
                        isValid={isValid} submitHandler={addTask}>
                <div className={classname}>
                    <div>
                            <InputName label={'Название задачи'} errors={errors} register={register} name={'title'}/>
                            <CustomTextArea title={'Описание задачи'} placeholder={'Описание'} register={register} errors={errors} name={'description'}/>
                            <CustomSelect errors={errors} title={'Выберите категорию задачи'} optionName={"Категория"} register={register} name={"category"}/>
                            <CustomSelect errors={errors} title={'Выберите тип задачи'} optionName={"Тип задачи"} register={register} name={'type'}/>
                            <FileInput register={register} name={'image'}/>
                            <NumberInput errors={errors} register={register} name={"percent"} label={"Пример: 15"} inputHandler={percentAdd}/>
                    </div>
                    <div>
                        <ImageUploadBlock errors={errors} register={register}/>
                    </div>
                </div>
            </CustomForm>
        </div>
    );
};

export default Constructor;