import {Autocomplete, Box, TextField} from '@mui/material';
import SelectTable from '../../../shared/ui/SelectTable';
import {useSelector} from 'react-redux';

const FilterTable = ({filters, dispatch}) => {
  const tasks = useSelector(state => state.task.tasks);
  console.log(dispatch, filters, 34);
  return (
    <Box display={'flex'} width={'100%'} justifyContent={'flex-end'} mb={3}>
      <TextField
        id="outlined-basic"
        label="Введите название"
        variant="outlined"
        ml={3}
        mr={2}
        value={filters.inpState}
        onChange={(e) => dispatch({
          type: 'inpState',
          inpState: e.target.value,
        })} />
      <Autocomplete
        value={filters.category}
        onChange={(event, newValue) => {
          dispatch({
            type: 'category',
            category: newValue,
          });
        }}
        id="combo-box-demo"
        options={tasks.map(item => item.category)}
        sx={{width: 250, ml: 3, mr: 2}}
        renderInput={(params) => <TextField {...params} label="Категория" />}
      />
      <SelectTable type={filters.type} setType={(newValue) => dispatch({
        type: 'type',
        typeSt: newValue,
      })} />
    </Box>
  );
};

export default FilterTable;