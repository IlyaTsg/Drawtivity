import React from 'react';
import Form from "react-bootstrap/Form";
import {dotsInputHandler} from "../../../entities/lib/dotsInputHandler";
import classes from "./DotsInput.module.scss";
const DotsInput = ({errors, register, handler, dispatch}) => {
    //console.log(register)
    return (
        <Form.Group className="mb-3">
            <Form.Label><div className={classes.label}>Введите координаты точек</div></Form.Label>
            <Form.Control
                isInvalid={errors?.dots}
                type='text'
                placeholder="0:0 11:33"
                {...register('dots', {
                    required:"Поле обязательно для заполнения",
                    pattern: {
                        //value: /^\w:\w$/,
                        message: 'Не валидные координаты'
                    }
                })}
                onChange={(event) => handler(event, dispatch)}
            />
            {errors?.dots
                &&
                <Form.Text className="text-muted">
                    {errors?.dots?.message}
                </Form.Text>}
        </Form.Group>
    );
};

export default DotsInput;