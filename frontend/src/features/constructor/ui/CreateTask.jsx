import Button from '@mui/material/Button';
import React from 'react';

const CreateTask = ({handleSubmit}) => {
  return (
    <Button
      size="large"
      variant="contained"
      sx={{
        color: 'white',
        background: 'black',
        borderColor: 'black',
        width: '230px',
        margin: '0 auto',
      }}
      onClick={(event) => {
        event.preventDefault();
        handleSubmit();
      }}
      onSubmit={(event) => {
        event.preventDefault();
        handleSubmit();
      }}
    >
      Добавить задачу
    </Button>
  );
};

export default CreateTask;