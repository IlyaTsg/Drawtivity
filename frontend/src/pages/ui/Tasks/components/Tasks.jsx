import React from 'react';
import CenterMainBlock from "../../../../entities/ui/CenterMainBlock/CenterMainBlock";
import TasksList from "../../../../widgets/ui/TasksList/TasksList";

const Tasks = () => {
    return (
        <div>
            <CenterMainBlock width={'large'}>
               <TasksList/>
            </CenterMainBlock>
        </div>
    );
};

export default Tasks;