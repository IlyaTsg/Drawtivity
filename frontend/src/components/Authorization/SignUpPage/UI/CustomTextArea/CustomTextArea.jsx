import React from 'react';
import Form from "react-bootstrap/Form";
const CustomTextArea = ({title}, {placeholder}) => {
    return (
        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
            <Form.Label>{title}</Form.Label>
            <Form.Control as="textarea" rows={3}  placeholder={placeholder}/>
        </Form.Group>
    );
};

export default CustomTextArea;