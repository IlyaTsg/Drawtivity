import ImageUploadBlock from '../../../entities/ui/ImageUploadBlock/ImageUploadBlock';
import {useEffect} from 'react';

const TaskCreate = ({register, lineColor}) => {
  return (
    <>
      <ImageUploadBlock register={register} lineColor={lineColor} />
    </>
  );
};

export default TaskCreate;