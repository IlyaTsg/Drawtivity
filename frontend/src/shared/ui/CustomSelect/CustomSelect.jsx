import React from 'react';
import Form from 'react-bootstrap/Form';
import classes from './CustomSelect.module.scss';
import {FormControl, FormHelperText, InputLabel, MenuItem, Select, Typography} from '@mui/material';

const CustomSelect = ({title, register, name, errors, options}) => {
  return (
    <>
      <Typography variant={'h6'} textAlign={'center'} mb={2}>{title}</Typography>
      <FormControl sx={{width: 350}}>
        <InputLabel id="demo-simple-select-required-label">{title}</InputLabel>
        <Select
          fullWidth
          select
          SelectProps={{
            native: true, inputProps: {ref: register, name: `${name}`},
          }}
          label={title}
          defaultValue={''}
        >
          <MenuItem value="">
            <em>None</em>
          </MenuItem>
          {options.map(item => <MenuItem value={item}>{item}</MenuItem>)}
        </Select>
        <FormHelperText>Required</FormHelperText>
      </FormControl>
    </>
  );
};

export default CustomSelect;