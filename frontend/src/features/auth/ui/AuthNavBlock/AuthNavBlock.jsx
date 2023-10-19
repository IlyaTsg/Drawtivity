import React from 'react';
import {Link} from "react-router-dom";
import classes from "./AuthNavBlock.module.scss";
import {removeUser} from "../../../../entities/model/store/slices/userSlice";
import useAuth from "../../../../entities/model/store/hooks/useAuth";
import {useDispatch} from "react-redux";

const AuthNavBlock = () => {
    const isAuth = useAuth().isAuth
    const dispatch = useDispatch()
    return (
        <div className={classes.wrap}>
            {!isAuth ?
                <div className={classes.content}>
                    <div className={classes.first} >
                        <Link to={'/sign_in'} target="_blank">Войти</Link>
                    </div>
                    <div className={classes.second}>
                        <Link to={'/sign_up'} target="_blank">Регистрация</Link>
                    </div>
                </div>
                :
                <Link to={'/info'} onClick={() => {dispatch(removeUser())}}>Выйти</Link>
            }
        </div>
    );
};

export default AuthNavBlock;