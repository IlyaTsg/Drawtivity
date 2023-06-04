import React from 'react';
import Form from 'react-bootstrap/Form';
import classes from "./CustomSelect.module.scss";
const CustomSelect = ({title, optionName, register, name, errors}) => {
    return (
        <div>
            <Form.Group className="mb-3">
                <Form.Label>{title}</Form.Label>
                    <Form.Select className={classes.optionList} {...register(`${name}`, {
                        required:"Поле обязательно для заполнения",
                        pattern: {
                            //value: /^[A-Za-zА-ЯЁа-яё]+$/,
                            //message: 'Введите корректное имя'
                        }
                    })}>
                        <option>{optionName}</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </Form.Select>
            </Form.Group>
        </div>
    );
};

export default CustomSelect;