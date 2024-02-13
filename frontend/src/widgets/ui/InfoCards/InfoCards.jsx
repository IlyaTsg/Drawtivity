import React from 'react'
import LinkCard from '../../../features/info/ui/LinkCard/LinkCard'
import classes from './InfoCards.module.scss'
import useAuth from '../../../entities/model/store/hooks/useAuth'

const InfoCards = () => {
  const isAuth = useAuth().isAuth
  return (
    <div className={classes.wrap}>
      <div className={classes.cards}>
        {isAuth ?
          <LinkCard
            title={'Решать задачи'}
            label={'Перейти к списку всех доступных для решения задачи'}
            text={'Список задач'}
            link={'/tasks'}
          />
          :
          <LinkCard
            title={'Авторизация'}
            label={'Войдите в уже существующий аккаунт для использования сервиса'}
            text={'Авторизоваться'}
            link={'/sign_in'}
          />
        }
        {isAuth ?
          <LinkCard
            title={'Создать задачу'}
            label={'Создайте и опубликуйте доступную для решения другими пользователями свою задачу!'}
            text={'Конструктор'}
            link={'/constructor'}
          />
          :
          <LinkCard
            title={'Регистрация'}
            label={'Зарегестрируйтесь и начните решать прямо сейчас!'}
            text={'Регистрация'}
          />
        }
      </div>
    </div>
  )
}

export default InfoCards