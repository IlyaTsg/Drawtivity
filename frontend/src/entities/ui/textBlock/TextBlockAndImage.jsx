import React from 'react';
import classes from './TextBlock.module.scss'
import CenterMainBlock from "../centerMainBlock/CenterMainBlock";
const TextBlockAndImage = ({children}) => {

    let imageData = typeof children === "object" ? children[1].props.children.props.src : null
    const childrenText = typeof children === "object" ? children[0].props.children : children

    return (
        <CenterMainBlock>
                <div className={classes.text}>{childrenText}</div>
                <div className={classes.image}><img src={imageData}/></div>
        </CenterMainBlock>
    );
};

export default TextBlockAndImage;