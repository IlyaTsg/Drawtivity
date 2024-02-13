import React from 'react'
import AuthNavBlock from '../../../features/auth/ui/AuthNavBlock/AuthNavBlock'
import DescriptionInfoBlock from '../../../features/info/ui/DescriptionInfoBlock/DescriptionInfoBlock'
import classes from './InfoBlock.module.scss'

const InfoBlock = () => {
  return (
    <>
      <div className={classes.wrap}>
        <DescriptionInfoBlock />
      </div>
    </>
  )
}

export default InfoBlock