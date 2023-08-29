import React from 'react';
import TextBlockAndImage from "../../../../entities/ui/TextBlock/TextBlockAndImage";
import InfoBlock from "../../../../widgets/ui/InfoBlock/InfoBlock";
import classes from './Info.module.scss'
import InfoCards from "../../../../widgets/ui/InfoCards/InfoCards";
const Info = () => {
    return (
        <div className={classes.wrap}>
            <InfoBlock/>
            <InfoCards/>
        </div>
    );
};

export default Info;