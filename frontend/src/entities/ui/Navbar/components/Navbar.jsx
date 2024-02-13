import React from 'react'
import { Link } from 'react-router-dom'
import classes from './Navbar.module.scss'
import useAuth from '../../../model/store/hooks/useAuth'

const Navbar = ({ isAuth }) => {

  return (
    <div className={classes.navbar}>
      <Link to={'/info'} className={'hover:text-white ml-1 mr-2'}>Общая информация</Link>
      <Link to={'/documentation'} className={'hover:text-white ml-6 mr-2'}>Документация</Link>
      {isAuth && <Link to={'/tasks'} className={'hover:text-white ml-6 mr-2'}>Список задач</Link>}
      {isAuth && <Link to={'/constructor'} className={'hover:text-white ml-6 mr-1'}>Конструктор задач</Link>}
    </div>
  )
}

export default Navbar