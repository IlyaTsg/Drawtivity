import React from 'react';
import classes from "./TextInfoBlock.module.scss";
import classNames from "classnames";
const TextInfoBlock = ({info, title, label}) => {
    const titleStyle = classNames(classes.title)
    return (
        <div className={classes.wrap}>
            <div className={classes.title}>
                {title}
            </div>
            <div className={classes.label}>
                {label}
            </div>
        </div>
    );
};

export default TextInfoBlock;