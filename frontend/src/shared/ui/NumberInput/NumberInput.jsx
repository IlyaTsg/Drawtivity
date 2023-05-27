import React from 'react';
import classes from './NumberInput.module.scss'
const NumberInput = () => {
    return (
        <div className={classes.wrapper}>
            <label>Процент отклонения от правильных точек: </label>
            <input type="number" className={classes.num_input}/>
        </div>
    );
};

export default NumberInput;