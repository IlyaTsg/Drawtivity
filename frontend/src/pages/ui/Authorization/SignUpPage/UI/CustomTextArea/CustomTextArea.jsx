import React from 'react';
import Form from "react-bootstrap/Form";
const CustomTextArea = ({title, placeholder, register, errors, name}) => {
    return (
        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1" >
            <Form.Label>{title}</Form.Label>
            <Form.Control as="textarea" rows={3}  placeholder={placeholder}
                          {...register(`${name}`, {
                required:"Поле обязательно для заполнения",
            })}/>
            {errors?.firstName
                &&
                <Form.Text className="text-muted">
                    {errors?.firstName?.message}
                </Form.Text>
            }
        </Form.Group>
    );
};

export default CustomTextArea;