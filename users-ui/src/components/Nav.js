import React, {Component} from 'react'
import {NavLink} from 'react-router-dom'

class Nav extends Component {

  render() {
    return (
      <ul className='nav'>
        <li>
          <NavLink id='new-user-link' exact activeClassName='active' to='/'>Create</NavLink>
          <NavLink id='view-users' activeClassName='active' to='/myusers'>View all Users</NavLink>
          <NavLink activeClassName='active' to='/myprojects'>Your Stars</NavLink>
        </li>
      </ul>
    )
  }
}

export default Nav
