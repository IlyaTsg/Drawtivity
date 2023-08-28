import React from 'react';
import classes from "./TextInfoBlock.module.scss";
import classNames from "classnames";
const TextInfoBlock = ({block, title, label}) => {
    const titleStyle = classNames(classes.title, {
        [classes.infoTitle]: block === 'info',
        [classes.linkTitle]: block === 'link'
    })
    const labelStyle = classNames(classes.label, {
        [classes.infoLabel]: block === 'info',
        [classes.linkLabel]: block === 'link'
    })
    const wrapStyle = classNames({
        [classes.wrapInfo]: block === 'info',
        [classes.wrapLink]: block === 'link'
    })
    return (
        <div className={wrapStyle}>
            <div className={titleStyle}>
                {title}
            </div>
            <div className={labelStyle}>
                {label}
            </div>
        </div>
    );
};

export default TextInfoBlock;