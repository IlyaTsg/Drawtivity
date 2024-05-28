import React from 'react';
import classes from './TaskItem.module.scss';
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { getActualTask } from '../../../entities/model/store/slices/tasksSlice';
import classNames from 'classnames';

const TaskItem = ({ ind, title, category, type, moreClickHandler }) => {
  const nav = useNavigate();
  const rowStyle = classNames(classes.itemRow, {
    [classes.rightGray]: (ind + 1) % 3 === 0,
    [classes.leftGray]: (ind + 1) % 9 === 0,
  });
  return (
    <div className={rowStyle}>
      <span className={classes.indClasses}>{ind}</span>
      <div className={classes.itemRow__item}>
        {title}
      </div>
      <div className={classes.itemRow__item}>{category}</div>
      <div className={classes.itemRow__item}>{type}</div>
      <Link className={classes.itemRow__item} to={`/task/${ind}`}
            onClick={() => nav(`/task/${ind}`)}>Подробнее</Link>
    </div>
  );
};

export default TaskItem;