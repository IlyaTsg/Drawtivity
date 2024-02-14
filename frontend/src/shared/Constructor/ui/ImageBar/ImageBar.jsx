import React from 'react';
import classes from './ImageBar.module.scss';

const ImageBar = () => {
  return (
    <div className={classes.wrap}>
      <div className={classes.first}>
        -275
      </div>
      <div>
        0
      </div>
      <div>
        275
      </div>
    </div>
  );
};

export default ImageBar;