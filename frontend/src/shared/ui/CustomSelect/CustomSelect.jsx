import React from 'react';
import Form from 'react-bootstrap/Form';
import classes from "./CustomSelect.module.scss";
const CustomSelect = ({title, optionName}) => {
    return (
        <div>
            <label>{title}</label>
            <Form.Select className={classes.optionList}>
                <option>{optionName}</option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </Form.Select>
        </div>
    );
};

export default CustomSelect;