import React from 'react';
import classes from './DocumentationPage.module.scss'
import TextBlockAndImage from "../../../../entities/ui/textBlock/TextBlockAndImage";
const DocumentationPage = () => {
    return (
        <div className={classes.documentationWrap}>
            <TextBlockAndImage>
                Для создания задачи необходимо перейти на страницу с конструктором задач (кликнув по надписи «Конструктор задач»).
                Далее необходимо будет ввести: <br/>
                    1. Название задачи <br/>
                    2. Описание<br/>
                    3. Выбрать категорию<br/>
                    4. Выбрать тип задачи<br/>
                    5. Загрузить фотографию<br/>
                    6. Ввести радиус отклонения, в промежутках которого ответ будет считаться правильным<br/>
                    7. Ввести список координат, считающимися правильными<br/>
            </TextBlockAndImage>
        </div>
    );
};

export default DocumentationPage;