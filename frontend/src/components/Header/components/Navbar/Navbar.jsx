import React from 'react';
import {Link} from "react-router-dom";
import classes from './Navbar.module.scss'
const Navbar = () => {
    return (
        <div className={classes.navbar}>
            <Link to={'/info'}>Общая информация</Link>
            <Link to={'/sign_in'}>Документация</Link>
            <Link to={'/sign_up'}>Готовые задачи</Link>
            <Link to={'/sign_in'}>Конструктор задач</Link>
            <Link to={'/sign_up'}>Регистрация</Link>
            {/*<Link to={'/sign_in'}>Авторизация</Link>*/}
        </div>
    );
};

export default Navbar;