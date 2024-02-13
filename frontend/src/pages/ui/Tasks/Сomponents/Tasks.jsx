import TasksList from '../../../../widgets/ui/TasksList/TasksList'
import classes from './Tasks.module.scss'

const Tasks = () => {
  return (
    <div className={classes.wrap}>
        <TasksList />
    </div>
  )
}

export default Tasks