import React from 'react';
import classes from './DocumentationPage.module.scss'
import TextBlockAndImage from "../../../../entities/ui/textBlock/TextBlockAndImage";
const DocumentationPage = () => {
    return (
        <div className={classes.documentationWrap}>
            <TextBlockAndImage>
                random hz
            </TextBlockAndImage>
        </div>
    );
};

export default DocumentationPage;