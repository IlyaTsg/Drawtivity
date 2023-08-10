import React from 'react';
import Form from "react-bootstrap/Form";

const InputEmail = ({errors, register}) => {
    return (
        <Form.Group className="mb-3">
            <Form.Label>Email address</Form.Label>
            <Form.Control
                isInvalid={errors?.email}
                type='text'
                placeholder="name@example.com"
                {...register('email', {
                    required:"Поле обязательно для заполнения",
                    pattern: {
                        value: /^\w+@[a-zA-Z_]+\.[a-zA-Z]{2,3}$/,
                        message: 'Не валидный email'
                    }
                })}/>
            {errors?.email
                &&
                <Form.Text className="text-muted">
                    {errors?.email?.message}
                </Form.Text>}
        </Form.Group>
    );
};

export default InputEmail;