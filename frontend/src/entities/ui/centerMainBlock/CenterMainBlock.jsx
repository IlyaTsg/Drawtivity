import React from 'react';
import classes from "./CenterMainBlock.module.scss";
import classNames from "classnames";
const CenterMainBlock = ({children, width = 'medium'}) => {
    const classname = classNames(classes.info, {
        [classes.medium]: width === 'medium',
        [classes.large]: width === 'large'
    })
    return (
        <div className={classes.wrapper}>
            <div className={classname}>
                {children}
            </div>
        </div>
    );
};

export default CenterMainBlock;