import React from 'react';
import classes from './TaskItem.module.scss'
const TaskItem = ({ind, title, category, type}) => {
    return (
        <div className={classes.itemRow}>
            <span className={classes.indClasses}>{ind}</span>
            <div className={classes.itemRow__item}>
                {title}
            </div>
            <div className={classes.itemRow__item}>{category}</div>
            <div className={classes.itemRow__item}>{type}</div>
            <div className={classes.itemRow__item}>Подробнее</div>

        </div>
    );
};

export default TaskItem;