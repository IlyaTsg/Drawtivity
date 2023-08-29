import React from 'react';
import {Link} from "react-router-dom";
import classes from './LinkButton.module.scss'
import classNames from "classnames";

const LinkButton = ({text, block}) => {
    const wrapStyle = classNames(classes.wrap, {
        [classes.infoStyle]: block === 'info',
        [classes.linkStyle]: block === 'link'
    })
    return (
        <div className={wrapStyle}>
            <Link to={'/sign_up'}>{text}</Link>
        </div>
    );
};

export default LinkButton;