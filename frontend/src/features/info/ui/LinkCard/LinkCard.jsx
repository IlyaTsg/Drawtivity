import React from 'react';
import TextInfoBlock from "../../../../shared/ui/TextInfoBlock/TextInfoBlock";
import LinkButton from "../../../../shared/ui/LinkButton/LinkButton";
import classes from './LinkCard.module.scss'
const LinkCard = ({title, label, text, link}) => {
    return (
        <div className={classes.wrap}>
            <TextInfoBlock
                           title={title}
                           label={label}
                            block={'link'}
            />
            <LinkButton text={text} block={'link'}/>
        </div>
    );
};

export default LinkCard;