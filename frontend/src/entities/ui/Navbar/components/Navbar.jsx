import React from 'react';
import {Link} from "react-router-dom";
import classes from './Navbar.module.scss'
import DropDownList from "../../DropDownList/DropDownList";
import  {removeUser} from "../../../model/store/slices/userSlice";
import {useDispatch} from "react-redux";
import useAuth from "../../../model/store/hooks/useAuth";
import AuthNavBlock from "../../../../features/auth/ui/AuthNavBlock/AuthNavBlock";
const Navbar = () => {
    const isAuth = useAuth().isAuth
    const dispatch = useDispatch()
    return (
        <div className={classes.navbar}>
            <Link to={'/info'}>Общая информация</Link>
            <Link to={'/documentation'}>Документация</Link>
            <Link to={'/tasks'}>Список задач</Link>
            <Link to={'/constructor'}>Конструктор задач</Link>

        </div>
    );
};

export default Navbar;