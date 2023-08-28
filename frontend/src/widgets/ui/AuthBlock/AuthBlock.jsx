import React from 'react';
import classes from './AuthBlock.module.scss'
import classNames from "classnames";
const AuthBlock = ({headerText, helperText, auth,...props}) => {
    const wrapCl = classNames(classes.wrap, {
        [classes.auth]: auth,
        [classes.reg]: !auth
    })
    return (
        <div className={wrapCl}>
            <div className={classes.header}>
                {headerText}
            </div>
            {props.children}
            <div className={classes.helper}>
                {helperText}
            </div>
        </div>
    );
};

export default AuthBlock;