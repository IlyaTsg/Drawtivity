import React, {useEffect, useRef} from 'react';
import classes from './TaskBlock.module.scss'
import TextBlockAndImage from "../../../entities/ui/textBlock/TextBlockAndImage";
import Form from "react-bootstrap/Form";
import useImage from "../../../store/hooks/useImage";
import {useDispatch} from "react-redux";
import {calcRadius} from "../../../entities/lib/calcRadius";
import {drawAllLines} from "../../../entities/lib/drawAllLines";
import TaskSolution from "../../../entities/ui/TaskSolution/TaskSolution";
const TaskBlock = () => {
    return (
        <div className={classes.wrapper}>
            <div>
               <TaskSolution/>
                <button>Проверить</button>
            </div>
            <TextBlockAndImage>
                Постановка задачи
            </TextBlockAndImage>
        </div>
    );
};

export default TaskBlock;