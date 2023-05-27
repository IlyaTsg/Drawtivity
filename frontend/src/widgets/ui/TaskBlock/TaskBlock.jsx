import React from 'react';
import classes from './TaskBlock.module.scss'
import TextBlockAndImage from "../../../entities/ui/textBlock/TextBlockAndImage";
const TaskBlock = () => {
    return (
        <div>
            <div>
                <img/>
                <button>Проверить</button>
            </div>
            <TextBlockAndImage>
                Постановка задачи
            </TextBlockAndImage>
        </div>
    );
};

export default TaskBlock;