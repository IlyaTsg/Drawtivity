import React from 'react';
import LinkButton from "../../../../shared/ui/LinkButton/LinkButton";
import TextInfoBlock from "../../../../shared/ui/TextInfoBlock/TextInfoBlock";
import classes from "./DescriptionInfoBlock.module.scss";
const DescriptionInfoBlock = () => {
    return (
        <div className={classes.wrap}>
            <TextInfoBlock info={'sd'}
                           title={'DrawIt - сервис онлайн решения графических задач'}
                           label={'Наш сервис является инструментом электронного ' +
                               'обучения для создания онлайн-заданий,' +
                               ' включающих рисование и обратную связь от преподавателя.\n' +
                               '                    Благодаря нашей разработке ' +
                               'облегчается массовое, повторное использование' +
                               ' и адаптация образовательных программ.'}
                            block={'info'}
            />
            <LinkButton text={'Регистрация'} block={'info'}/>
        </div>
    );
};

export default DescriptionInfoBlock;