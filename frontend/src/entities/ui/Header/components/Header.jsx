import React from 'react';
import classes from "./Header.module.scss";
import Navbar from "../../Navbar/components/Navbar";
const Header = () => {
    return (
        <div className={classes.header}>
            <div className={classes.logo}>
                {// <img src={require('../../../../logo.jpg')} alt={'Logo'} />
                }
            </div>

            <Navbar/>
        </div>
    );
};

export default Header;