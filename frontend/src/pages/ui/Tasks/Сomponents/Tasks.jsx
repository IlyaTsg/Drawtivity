import React from 'react';
import CenterMainBlock from "../../../../entities/ui/CenterMainBlock/CenterMainBlock";
import TasksList from "../../../../widgets/ui/TasksList/TasksList";
import classes from "./Tasks.module.scss";

const Tasks = () => {
    return (
        <div className={classes.wrap}>
            <div className={classes.contentWrapper}>
                <CenterMainBlock width={'large'}>
                   <TasksList/>
                </CenterMainBlock>
            </div>
        </div>
    );
};

export default Tasks;