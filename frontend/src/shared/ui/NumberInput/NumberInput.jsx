import React from 'react';
import classes from './NumberInput.module.scss'
import Form from "react-bootstrap/Form";
const NumberInput = ({name, errors, register, label, inputHandler}) => {
    return (
        <Form.Group className="mb-3">
            <div className={classes.wrapper}>
                <Form.Label>Процент отклонения:</Form.Label>
                <Form.Control
                    isInvalid={errors?.firstName}
                    type='number'
                    placeholder={label}
                    onInput={inputHandler}
                    {...register(`${name}`, {
                        required:"Поле обязательно для заполнения",
                        pattern: {
                            //value: /^[A-Za-zА-ЯЁа-яё]+$/,
                            message: 'Введите корректное имя'
                        }
                    })}/>
                {errors?.firstName
                    &&
                    <Form.Text className="text-muted">
                        {errors?.firstName?.message}
                    </Form.Text>}
            </div>
        </Form.Group>
    );
};

export default NumberInput;