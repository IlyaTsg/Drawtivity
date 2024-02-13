import React from 'react'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import classes from './CustomForm.module.scss'
import LinkButton from '../../../../../shared/ui/LinkButton/LinkButton'

const CustomForm = ({ btnText, handlerSubmit, isValid, submitHandler, children, formCl }) => {

  return (
    <Form onSubmit={handlerSubmit(submitHandler)} className={formCl}>
      {children}
      {btnText ?
        <div className={classes.btnCl}>
          <Button disabled={!isValid} variant="dark" type="submit" size={'lg'} style={{ backgroundColor: 'black' }}>
            {btnText}
          </Button>
        </div>
        :
        false
      }
    </Form>
  )
}

export default CustomForm