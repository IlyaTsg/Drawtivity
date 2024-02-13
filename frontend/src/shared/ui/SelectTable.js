import {Box, FormControl, InputLabel, MenuItem, Select} from '@mui/material';

const SelectTable = ({type, setType}) => {

  const handleChange = (event) => {
    setType(event.target.value);
  };

  return (
    <Box sx={{width: 200, ml: 1}}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Тип задачи</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={type}
          label="Тип задачи"
          onChange={handleChange}
        >
          <MenuItem value={'test'}>test</MenuItem>
          <MenuItem value={'test1'}>test1</MenuItem>
          <MenuItem value={'3'}> 3</MenuItem>
          <MenuItem value={''}> Сбросить</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
};

export default SelectTable;