import React from 'react';
import {Link} from "react-router-dom";

const LinkButton = ({text}) => {
    return (
        <div>
            <Link to={'/sign_up'}>{text}</Link>
        </div>
    );
};

export default LinkButton;