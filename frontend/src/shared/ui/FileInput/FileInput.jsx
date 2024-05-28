import React from 'react';
import Form from 'react-bootstrap/Form';
import { useDispatch } from 'react-redux';
import { setImage } from '../../../entities/model/store/slices/tasksSlice';

const FileInput = ({ register, name }) => {
  //const [selectedImage, setSelectedImage] = useState(null);
  const dispatch = useDispatch();
  return (
    <Form.Group controlId="formFile" className="mb-3" style={{ width: 400 }}>
      <Form.Label>Загрузите изображение</Form.Label>
      <Form.Control
        type="file"
        {...register(name, {
          required: 'Поле обязательно для заполнения',
          pattern: {
            //value: /^\w:\w$/,
            //message: 'Не валидные координаты'
          },
        })}
        onChange={(event) => {
          if (event.target.files.length > 0) {
            const file = event.target.files[0];
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = function() {
              dispatch(setImage(reader.result.replace('data:', '').replace(/^.+,/, '')));
            };
          } else
            dispatch(setImage(null));
        }
        } />
    </Form.Group>
  );
};

export default FileInput;