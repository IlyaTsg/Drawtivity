import React from 'react';
import CenterMainBlock from "../../../../entities/ui/CenterMainBlock/CenterMainBlock";
import TasksList from "../../../../widgets/ui/TasksList/TasksList";
import classes from "./Tasks.module.scss";

const Tasks = () => {
    return (
        <div className={classes.wrap}>
            <CenterMainBlock width={'large'}>
               <TasksList/>
            </CenterMainBlock>
        </div>
    );
};

export default Tasks;