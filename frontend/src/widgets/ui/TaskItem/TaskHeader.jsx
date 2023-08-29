import React from 'react';
import classes from "./TaskItem.module.scss";

const TaskHeader = () => {
    return (
        <div className={classes.headerRow}>
            <span className={classes.indClasses}>№</span>
            <div className={classes.headerRow__item}>
                Название
            </div>
            <div className={classes.headerRow__item}>Категория</div>
            <div className={classes.headerRow__item}>Тип задачи</div>
            <div className={classes.headerRow__item}>Параметры</div>
        </div>
    );
};

export default TaskHeader;