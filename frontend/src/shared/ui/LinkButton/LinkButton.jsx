import React from 'react'
import { Link } from 'react-router-dom'
import classes from './LinkButton.module.scss'
import classNames from 'classnames'

const LinkButton = ({ text, block, link = '/sign_up' }) => {
  const wrapStyle = classNames(classes.wrap, {
    [classes.infoStyle]: block === 'info',
    [classes.linkStyle]: block === 'link',
  })
  return (
    <div className={wrapStyle}>
      <Link to={link}>{text}</Link>
    </div>
  )
}

export default LinkButton