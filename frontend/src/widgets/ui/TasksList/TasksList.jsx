import { useEffect, useReducer } from 'react';
import classes from './TasksList.module.scss';
import TaskItem from '../TaskItem/TaskItem';
import TaskHeader from '../TaskItem/TaskHeader';
import { useDispatch, useSelector } from 'react-redux';
import { getTasks } from '../../../entities/model/store/slices/tasksSlice';
import FilterTable from '../../../features/filter-table/ui/FilterTable';
import { useFiltered } from '../../../features/filter-table/hooks/useFiltered';
import { Typography } from '@mui/material';

function reducer(state, action) {
  switch (action.type) {
    case 'inpState' : {
      return {
        ...state,
        inpState: state.inpState = action.inpState,
      };
    }
    case 'type': {
      return {
        ...state,
        typeSt: state.typeSt = action.typeSt,
      };
    }
    case 'category': {
      return {
        ...state,
        category: state.category = action.category,
      };
    }
  }
  throw Error('Unknown action.');
}

const TasksList = () => {
  const dispatch = useDispatch();
  const tempTasks = useSelector(state => state.task.tasks);

  const [filters, setFilters] = useReducer(reducer, { inpState: '', typeSt: '', category: '' });
  const filtered = useFiltered(tempTasks, filters.typeSt, filters.category, filters.inpState);

  useEffect(() => {
    dispatch(getTasks());
  }, [localStorage.getItem('token')]);

  return (
    <div className={classes.wrapper}>
      <FilterTable filters={filters} dispatch={setFilters} />
      <TaskHeader />
      {filtered.length > 0 ? filtered.map((item, i) =>
          <>
            <TaskItem
              key={item.task_id}
              ind={item.task_id}
              title={item.title}
              category={item.category}
              type={item.type}
            />
          </>,
        )
        :
        <Typography textAlign={'center'} variant={'h5'} mt={3}>Ничего не найдено</Typography>
      }
    </div>
  );
};

export default TasksList;