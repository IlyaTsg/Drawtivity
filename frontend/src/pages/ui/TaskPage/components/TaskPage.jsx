import React from 'react';
import CenterMainBlock from "../../../../entities/ui/CenterMainBlock/CenterMainBlock";
import TaskBlock from "../../../../widgets/ui/TaskBlock/TaskBlock";
import classes from "./TaskPage.module.scss";
const TaskPage = () => {
    return (
        <div className={classes.wrap}>
            <CenterMainBlock width={'task'}>
                <TaskBlock/>
            </CenterMainBlock>
        </div>
    );
};

export default TaskPage;