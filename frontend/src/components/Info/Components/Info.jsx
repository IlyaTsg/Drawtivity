import React from 'react';
import classes from "./Info.module.scss";
import TextBlockAndImage from "../../../entities/ui/textBlock/TextBlockAndImage";
const Info = () => {
    return (
            <TextBlockAndImage>
                <div>
                    Drawtivity is an e-learning tool for creating on-line activities that involve drawing and feedback.
                    As an Open Education Resource authoring tool, Drawtivity facilitates the sharing,
                    reuse and adaptation of these activities. To learn more follow the links below:
                </div>
                <div>
                    <img src={require('../../../example_activities.jpg')} alt={'exampe'}/>
                </div>
            </TextBlockAndImage>

    );
};

export default Info;