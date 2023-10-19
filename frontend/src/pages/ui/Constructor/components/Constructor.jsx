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
import useImage from "../../../../entities/model/store/hooks/useImage";
import {useDispatch, useSelector} from "react-redux";
import {createTask, getTasks, setPercent} from "../../../../entities/model/store/slices/tasksSlice";
import {signInThunk} from "../../../../entities/model/store/slices/userSlice";
const Constructor = () => {
    const selectedImage = useImage()
    const dispatch = useDispatch()
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
    const id = useSelector(state => state.user.id)
    const addTask = (data) => {
        const task = data
        //task["image"] = image
        task["img_url"] = 'test'
        task["points"] = coordinates
        task["owner_id"] = id
        //console.log(JSON.stringify(data))
        dispatch(createTask(task))
        dispatch(getTasks())
    }
    const percentAdd = (event) => {
        dispatch(setPercent(event.target.value))
    }
    return (
        <div className={classes.wrap}>
            <CustomForm formCl={classes.form} handlerSubmit={handleSubmit}
                        btnText={'Добавить задачу'}
                        isValid={isValid} submitHandler={addTask}>
                <div className={classname}>
                    <div>
                        <ImageUploadBlock errors={errors} register={register}/>
                    </div>
                    <div className={classes.formBlockWrapper}>
                        <div className={classes.prom}>
                            <div className={classes.formBlock}>
                                    <div className={classes.formHeader}>
                                        Конструктор создания задач
                                    </div>
                                    <div className={classes.namesBlock}>
                                        <InputName label={'Название задачи'} errors={errors} register={register} name={'title'}/>
                                        <CustomTextArea title={'Описание задачи'} placeholder={'Описание'} register={register} errors={errors} name={'description'}/>
                                    </div>
                                <div className={classes.twoBlocks}>
                                    <div className={classes.twoElem}> <CustomSelect errors={errors}
                                                       title={'Выберите категорию задачи'}
                                                       optionName={"Категория"} register={register}
                                                       name={"category"}/>
                                    </div>
                                    <div className={classes.twoElem}>
                                    <CustomSelect errors={errors}
                                                  title={'Выберите тип задачи'}
                                                  optionName={"Тип задачи"} register={register}
                                                  name={'type'}/>
                                    </div>
                                </div>
                                <div className={classes.twoBlocks}>
                                    <div className={classes.twoElem}>
                                        <FileInput register={register} name={'image'}/>
                                    </div>
                                    <div className={classes.twoElem}><NumberInput errors={errors} register={register}
                                                      name={"deviation"} label={"Пример: 15"}
                                                      inputHandler={percentAdd}/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </CustomForm>
        </div>
    );
};

export default Constructor;