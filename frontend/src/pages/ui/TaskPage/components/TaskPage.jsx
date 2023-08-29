import React from 'react';
import CenterMainBlock from "../../../../entities/ui/CenterMainBlock/CenterMainBlock";
import TaskBlock from "../../../../widgets/ui/TaskBlock/TaskBlock";

const TaskPage = () => {
    return (
        <CenterMainBlock width={'task'}>
            <TaskBlock/>
        </CenterMainBlock>
    );
};

export default TaskPage;