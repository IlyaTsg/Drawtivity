import React, {useEffect, useState} from 'react';
import classes from './TasksList.module.scss'
import TaskItem from "../TaskItem/TaskItem";
import TaskHeader from "../TaskItem/TaskHeader";
import {useDispatch, useSelector} from "react-redux";
import {getTasks} from "../../../entities/model/store/slices/tasksSlice";

const TasksList = () => {
    /*const tasks =[
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
    ]*/
    const [tasks, setTasks] = useState([])
    const dispatch = useDispatch()

    const tempTasks =  useSelector(state => state.task.tasks)
    useEffect(()=>{
        dispatch(getTasks())
        setTasks(tempTasks)
    }, [])
    return (
        <div className={classes.wrapper}>
            <TaskHeader/>
            {tempTasks.map((item, i) =>
                <TaskItem key={i} ind={i}
                          title={item.title}
                          category={item.category}
                          type={item.type}/>
            )}
        </div>
    );
};

export default TasksList;