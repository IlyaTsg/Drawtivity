import React from 'react';
import LinkButton from '../../../../shared/ui/LinkButton/LinkButton';
import TextInfoBlock from '../../../../shared/ui/TextInfoBlock/TextInfoBlock';
import classes from './DescriptionInfoBlock.module.scss';
import useAuth from '../../../../entities/model/store/hooks/useAuth';


const DescriptionInfoBlock = () => {
  const isAuth = useAuth().isAuth;
  return (
    <div>
      <TextInfoBlock
        info={'sd'}
        title={'DrawIt - сервис онлайн решения графических задач'}
        label={'Наш сервис является инструментом электронного ' +
          'обучения для создания онлайн-заданий,' +
          ' включающих рисование и обратную связь от преподавателя.\n' +
          '                    Благодаря нашей разработке ' +
          'облегчается массовое, повторное использование' +
          ' и адаптация образовательных программ.'}
        block={'info'}
      />
      <div className={classes.wrap}>
        <LinkButton text={'Попробовать'} block={'info'} link={isAuth ? '/tasks' : '/sign_in'} />
        <LinkButton text={'Интеграции'} block={'info'} link={'/documentation'} />
      </div>
    </div>
  );
};

export default DescriptionInfoBlock;