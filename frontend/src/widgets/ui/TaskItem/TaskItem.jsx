import React from 'react'
import classes from './TaskItem.module.scss'
import { Link } from 'react-router-dom'
import { useDispatch } from 'react-redux'
import { getActualTask } from '../../../entities/model/store/slices/tasksSlice'
import classNames from 'classnames'

const TaskItem = ({ ind, title, category, type, moreClickHandler }) => {
  const dispatch = useDispatch()
  const rowStyle = classNames(classes.itemRow, {
    [classes.rightGray]: (ind + 1) % 3 === 0,
    [classes.leftGray]: (ind + 1) % 9 === 0,
  })
  return (
    <div className={rowStyle}>
      <span className={classes.indClasses}>{ind + 1}</span>
      <div className={classes.itemRow__item}>
        {title}
      </div>
      <div className={classes.itemRow__item}>{category}</div>
      <div className={classes.itemRow__item}>{type}</div>
      <Link className={classes.itemRow__item} to={`/task/${ind + 1}`}
            onClick={() => dispatch(getActualTask(ind + 1))}>Подробнее</Link>
    </div>
  )
}

export default TaskItem