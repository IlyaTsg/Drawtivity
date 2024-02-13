import React, {useEffect, useState} from 'react';
import Form from "react-bootstrap/Form";
import {dotsInputHandler} from "../../../entities/lib/dotsInputHandler";
import classes from "./DotsInput.module.scss";
import {useSelector} from "react-redux";
const DotsInput = ({errors, register, handler, dispatch}) => {
    const dots = useSelector(state => state.task.coordinates)
    const [dotsStr, setDotsStr] = useState('')
    useEffect(()=>{
        dots.map(item => setDotsStr( `${dotsStr ? `${dotsStr}, `: ''} ${item.x}: ${item.y}`))
    }, [dots])

    console.log(dots)
    return (
        <Form.Group className="mb-3">
            <Form.Label><div className={classes.label}>Введите координаты точек</div></Form.Label>
            <Form.Control
                isInvalid={errors?.dots}
                type='text'
                placeholder="0:0 11:33"
                value={dotsStr}
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