import React from 'react';

import TextBlockAndImage from "../../../../entities/ui/textBlock/TextBlockAndImage";
const Info = () => {
    return (
            <TextBlockAndImage>
                <div>
                    Наш продукт является инструмент электронного обучения для создания онлайн-заданий, включающих рисование и обратную связь от преподавателя.
                    Благодаря нашей разработке облегчается массовое, повторное использование и адаптация образовательных программ.
                </div>
                <div>
                    <img src={require('../../../../example_activities.jpg')} alt={'exampe'}/>
                </div>
            </TextBlockAndImage>

    );
};

export default Info;