import classes from '../../../../pages/ui/Constructor/components/Constructor.module.scss';
import InputName from '../../../../pages/ui/Authorization/SignUpPage/UI/InputName/InputName';
import CustomTextArea from '../../../../pages/ui/Authorization/SignUpPage/UI/CustomTextArea/CustomTextArea';


const NamesBlock = ({register}) => {
  return (
    <div className={classes.namesBlock}>
      <div className={classes.twoElem}>
        <InputName label={'Название задачи'} register={register} name={'title'} />
      </div>
      <div className={classes.twoElem}>
        <CustomTextArea
          title={'Описание задачи'} placeholder={'Описание'} register={register} name={'description'} />
      </div>
    </div>
  );
};

export default NamesBlock;