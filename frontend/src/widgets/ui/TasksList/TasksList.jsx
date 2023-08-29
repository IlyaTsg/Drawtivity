import React, {useEffect, useState} from 'react';
import classes from './TasksList.module.scss'
import TaskItem from "../TaskItem/TaskItem";
import TaskHeader from "../TaskItem/TaskHeader";
import {useDispatch, useSelector} from "react-redux";
import {getTasks} from "../../../entities/model/store/slices/tasksSlice";

const TasksList = () => {

    const [tasks, setTasks] = useState([
        {ind: 1, title: 'Test', category: 'First', type: 'Second'},
        {ind: 2, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 3, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 4, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 5, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 6, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 8, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 9, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 16, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 26, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 36, title: 'Test Test', category: 'Second', type: 'Third'},
        {ind: 46, title: 'Test Test', category: 'Second', type: 'Third'},

    ])
    const dispatch = useDispatch()

    const tempTasks =  useSelector(state => state.task.tasks)
    useEffect(()=>{
        dispatch(getTasks())
        //setTasks(tempTasks)
    }, [])
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