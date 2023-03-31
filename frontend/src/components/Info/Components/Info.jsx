import React from 'react';
import classes from "./Info.module.scss";
const Info = () => {
    return (
        <div className={classes.wrapper}>
            <div className={classes.info}>
                <div className={classes.text}>
                    Drawtivity is an e-learning tool for creating on-line activities that involve drawing and feedback.
                    As an Open Education Resource authoring tool, Drawtivity facilitates the sharing,
                    reuse and adaptation of these activities. To learn more follow the links below:</div>
                <div>
                    <img src={require('../../../example_activities.jpg')} alt={'exampe'}/>
                </div>
            </div>
        </div>
    );
};

export default Info;