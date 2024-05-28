import Button from '@mui/material/Button';


const CreateTask = ({ handleSubmit }) => {
  return (
    <Button
      size="large"
      type={'submit'}
      variant="contained"
      sx={{
        color: 'white',
        background: 'black',
        borderColor: 'black',
        width: '230px',
        margin: '0 auto',
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