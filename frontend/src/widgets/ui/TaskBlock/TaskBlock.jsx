import React, {useEffect, useRef, useState} from 'react';
import classes from './TaskBlock.module.scss'
import TextBlockAndImage from "../../../entities/ui/TextBlock/TextBlockAndImage";
import Form from "react-bootstrap/Form";
import useImage from "../../../entities/model/store/hooks/useImage";
import {useDispatch, useSelector} from "react-redux";
import {calcRadius} from "../../../entities/lib/calcRadius";
import {drawAllLines} from "../../../entities/lib/drawAllLines";
import TaskSolution from "../../../entities/ui/TaskSolution/TaskSolution";
import Button from "react-bootstrap/Button";
import {createSolution} from "../../../entities/model/store/slices/tasksSlice";
const TaskBlock = () => {
    const task = useSelector(state => state.task.actualTask)
    const [newCoord, setNewCoord] = useState()
    const dispatch = useDispatch()
    let data ={

    }
    const value = useSelector(state => state.task.percentVal)
    const id = useSelector(state => state.task.id)
    useEffect(()=>{
        data = {
            id: id,
            points: newCoord
        }
    }, [newCoord])
    return (
        <div className={classes.wrapper}>
            <div>
                <TaskSolution setCoord={setNewCoord}/>
                <Button onClick={() => dispatch(createSolution(data))}>Проверить</Button>
            </div>
            <TextBlockAndImage>
                {task.description ? task.description : ''}
            </TextBlockAndImage>
            {value ? <div>{value}</div> : null}
        </div>
    );
};

export default TaskBlock;