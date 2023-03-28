import React from 'react';
import Form from "react-bootstrap/Form";

const InputName = ({errors, register, name, label}) => {

    return (
        <Form.Group className="mb-3">
            <Form.Label>{label}</Form.Label>
            <Form.Control
                isInvalid={errors?.firstName}
                type='text'
                placeholder={label}
                {...register(`${name}`, {
                    required:"Поле обязательно для заполнения",
                    pattern: {
                        value: /^[A-Za-zА-ЯЁа-яё]+$/,
                        message: 'Введите корректное имя'
                    }
                })}/>
            {errors?.firstName
                &&
                <Form.Text className="text-muted">
                    {errors?.firstName?.message}
                </Form.Text>}
        </Form.Group>
    );
};

export default InputName;