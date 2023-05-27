import React from 'react';
import classes from './TaskItem.module.scss'
import {Link} from "react-router-dom";
const TaskItem = ({ind, title, category, type, moreClickHandler}) => {
    return (
        <div className={classes.itemRow}>
            <span className={classes.indClasses}>{ind}</span>
            <div className={classes.itemRow__item}>
                {title}
            </div>
            <div className={classes.itemRow__item}>{category}</div>
            <div className={classes.itemRow__item}>{type}</div>
            <Link className={classes.itemRow__item} to={`/task/${ind}`}>Подробнее</Link>

        </div>
    );
};

export default TaskItem;