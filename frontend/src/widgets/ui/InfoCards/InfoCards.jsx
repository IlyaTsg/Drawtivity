import React from 'react';
import LinkCard from "../../../features/info/ui/LinkCard/LinkCard";
import classes from "./InfoCards.module.scss";
const InfoCards = () => {
    return (
        <div className={classes.wrap}>
            <div className={classes.cards}>
            <LinkCard title={'Авторизация'} link={'fd'}
                      label={'Для того, чтобы начать пользоваться сервисом необходимо авторизоваться'}
                      text={'Авторизоваться'}
            />
            <LinkCard title={'Авторизация'} link={'fd'}
                      label={'Для того, чтобы начать пользоваться сервисом необходимо авторизоваться'}
                      text={'Авторизоваться'}
            />
            <LinkCard title={'Авторизация'} link={'fd'}
                      label={'Для того, чтобы начать пользоваться сервисом необходимо авторизоваться'}
                      text={'Авторизоваться'}
            />
            <LinkCard title={'Авторизация'} link={'fd'}
                      label={'Для того, чтобы начать пользоваться сервисом необходимо авторизоваться'}
                      text={'Авторизоваться'}
            />
            </div>
        </div>
    );
};

export default InfoCards;