import React from 'react';
import {NavDropdown} from "react-bootstrap";
import classes from "./DropDownList.module.scss";
const DropDownList = ({title, children}) => {
    return (
        <div className={classes.wrapperDropDown}>
            <NavDropdown
                id="nav-dropdown-dark-example"
                title={title}
            >
                {children}
            </NavDropdown>
        </div>
    );
};

export default DropDownList;