import React from 'react';
import AuthNavBlock from "../../../features/auth/ui/AuthNavBlock/AuthNavBlock";
import DescriptionInfoBlock from "../../../features/info/ui/DescriptionInfoBlock/DescriptionInfoBlock";
import classes from "./InfoBlock.module.scss";

const InfoBlock = () => {
    return (
        <div>
            <div className={classes.auth}>
                <AuthNavBlock/>
            </div>
            <div className={classes.wrap}>
                <DescriptionInfoBlock/>
            </div>
        </div>
    );
};

export default InfoBlock;