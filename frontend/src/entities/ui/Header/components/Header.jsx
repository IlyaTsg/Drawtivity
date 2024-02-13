import React from 'react';
import classes from './Header.module.scss';
import Navbar from '../../Navbar/components/Navbar';
import AuthNavBlock from '../../../../features/auth/ui/AuthNavBlock/AuthNavBlock';
import useAuth from '../../../model/store/hooks/useAuth';

const Header = () => {
  const isAuth = useAuth().isAuth;
  return (
    <div className={classes.header}>
      <div className={classes.logo}>
        DrawIt
      </div>

      <Navbar isAuth={isAuth} />
      <AuthNavBlock isAuth={isAuth} />

    </div>
  );
};

export default Header;