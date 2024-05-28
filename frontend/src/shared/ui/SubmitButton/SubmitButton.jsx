import React from 'react';
import classNames from 'classnames';
import classes from '../LinkButton/LinkButton.module.scss';


const SubmitButton = ({ handler, text, disabled }) => {
  const block = 'info';
  const wrapStyle = classNames(classes.wrap, {
    [classes.infoStyle]: block === 'info',
  });
  return (
    <button className={wrapStyle} onClick={handler} disabled={disabled}>
      {text}
    </button>
  );

};

export default SubmitButton;