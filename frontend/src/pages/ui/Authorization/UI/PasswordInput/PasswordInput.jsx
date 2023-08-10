import React from 'react';
import Form from "react-bootstrap/Form";

const PasswordInput = ({errors, register}) => {
    return (
        <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control isInvalid={errors?.password} type="password" placeholder="Password" {...register('password',{
                minLength: {
                    value:6,
                    message: 'Минимальная длина - 6 символов'
                },
                maxLength: {
                    value:35,
                    message:'Максимальная длина - 35 символов'
                },
                required:"Поле обязательно для заполнения",
            })}/>
            {errors?.password
                &&
                <Form.Text className="text-muted">
                    {errors?.password?.message || 'Минимальная длина - 6 символов'}
                </Form.Text>}
        </Form.Group>

    );
};

export default PasswordInput;