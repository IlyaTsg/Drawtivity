import React from 'react';
import {Link} from "react-router-dom";
import classes from './Navbar.module.scss'
import DropDownList from "../../DropDownList/DropDownList";
import  {removeUser} from "../../../model/store/slices/userSlice";
import {useDispatch} from "react-redux";
import useAuth from "../../../model/store/hooks/useAuth";
const Navbar = () => {
    const isAuth = useAuth().isAuth
    const dispatch = useDispatch()
    return (
        <div className={classes.navbar}>
            <Link to={'/info'}>Общая информация</Link>
            <Link to={'/documentation'}>Документация</Link>
            <Link to={'/tasks'}>Список задач</Link>
            <Link to={'/constructor'}>Конструктор задач</Link>
            {!isAuth ?
                        <DropDownList title={'Войти'}>
                            <div className={classes.links}>
                                <Link className={classes.links} to={'/sign_up'}>Регистрация</Link>
                            </div>
                            <div className={classes.links}>
                                <Link className={classes.links} to={'/sign_in'}>Авторизация</Link>
                            </div>
                        </DropDownList>
                :
                <Link className={classes.links} to={'/info'} onClick={() => {dispatch(removeUser()); console.log(1)}}>Выйти</Link>
            }
        </div>
    );
};

export default Navbar;