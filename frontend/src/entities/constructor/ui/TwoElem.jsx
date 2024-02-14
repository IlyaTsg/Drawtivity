import classes from '../../../pages/ui/Constructor/components/Constructor.module.scss';
import CustomSelect from '../../../shared/ui/CustomSelect/CustomSelect';
import React from 'react';

const TwoElem = ({register}) => {
  return (
    <div className={classes.twoBlocks}>
      <div className={classes.twoElem}>
        <CustomSelect
          title={'Выберите категорию задачи'}
          optionName={'Категория'}
          register={register('category', {
            required: 'Поле обязательно для заполнения',
          })}
          name={'category'}
          options={['Математика', 'Медицина', 'География']}
        />
      </div>
      <div className={classes.twoElem}>
        <CustomSelect
          title={'Выберите тип задачи'}
          optionName={'Тип задачи'}
          register={register('type', {
            required: 'Поле обязательно для заполнения',
          })}
          name={'type'}
          options={['Площадь', 'Точки']}
        />
      </div>
    </div>
  );
};

export default TwoElem;