import { Link } from 'react-router-dom'
import classes from './AuthNavBlock.module.scss'
import { removeUser } from '../../../../entities/model/store/slices/userSlice'
import { useDispatch } from 'react-redux'

const AuthNavBlock = ({ isAuth }) => {

  const dispatch = useDispatch()

  const exitHandler = () => {
    dispatch(removeUser())
  }
  return (
    <div className={classes.wrap}>
      {
        isAuth
          ?
          <Link to={'/info'} onClick={exitHandler} className={'hover:text-white'}>
            Выйти
          </Link>
          :
          <div className={classes.content}>
            <div className={classes.first}>
              <Link to={'/sign_in'} className={'hover:text-black'}>Войти</Link>
            </div>
            <div className={classes.second}>
              <Link to={'/sign_up'} className={'hover:text-white'}>Регистрация</Link>
            </div>
          </div>
      }
    </div>
  )
}

export default AuthNavBlock