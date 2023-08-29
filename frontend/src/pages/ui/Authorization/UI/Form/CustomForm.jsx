import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import classes from "./CustomForm.module.scss";
import LinkButton from "../../../../../shared/ui/LinkButton/LinkButton";

const CustomForm = ({btnText, handlerSubmit, isValid, submitHandler, children, formCl}) => {

    return (
        <Form onSubmit={handlerSubmit(submitHandler)} className={formCl}>
            {children}
            <div className={classes.btnCl}>
                <Button disabled={!isValid} variant="dark" type="submit" size={"lg"}>
                    {btnText}
                </Button>
            </div>
        </Form>
    );
};

export default CustomForm;