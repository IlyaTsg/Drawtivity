import React from 'react';
import {Link} from "react-router-dom";
import classes from './Navbar.module.scss'
import DropDownList from "../../../dropDownList/DropDownList";
import userSlice, {removeUser} from "../../../../model/store/slices/userSlice";
import {useDispatch, useSelector} from "react-redux";
import useAuth from "../../../../model/store/hooks/useAuth";
const Navbar = () => {
    const isAuth = useAuth().isAuth
    const dispatch = useDispatch()
    return (
        <div className={classes.navbar}>
            <Link to={'/info'}>Общая информация</Link>
            <Link to={'/documentation'}>Документация</Link>
            <Link to={'/tasks'}>Решать задачи</Link>
            <Link to={'/constructor'}>Конструктор задач</Link>
            {!isAuth ?
                <DropDownList title={'Войти'}>
                    <Link className={classes.links} to={'/sign_up'}>Регистрация</Link>
                    <Link className={classes.links} to={'/sign_in'}>Авторизация</Link>
                </DropDownList >
                :
                <Link className={classes.links} to={'/info'} onClick={() => {dispatch(removeUser()); console.log(1)}}>Выйти</Link>
            }
        </div>
    );
};

export default Navbar;