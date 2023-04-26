import React from 'react';
import classes from './TasksList.module.scss'
import TaskItem from "../TaskItem/TaskItem";
import TaskHeader from "../TaskItem/TaskHeader";

const TasksList = () => {
    const tasks =[
        {
            title:'sa',
            category: 'dsa',
            type: 'dss'
        },
        {
            title:'sa2',
            category: 'dsa2',
            type: 'dss2'
        },
        {
            title:'sadasfsfasdfsadfsad2',
            category: 'dsadsfasdfasdfasdf2',
            type: 'dss2dsffasdfasdfasdf'
        },
    ]
    return (
        <div className={classes.wrapper}>
            <TaskHeader/>
            {tasks.map((item, i) =>
                <TaskItem key={i} ind={i}
                          title={item.title}
                          category={item.category}
                          type={item.type}/>
            )}
        </div>
    );
};

export default TasksList;