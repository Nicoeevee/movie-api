import React from 'react'
import {Link} from 'react-router-dom'
import {Container, Menu, Segment} from 'semantic-ui-react'
import {useAuth} from '../context/AuthContext'

function Navbar() {
  const {activeItem, getUser, userIsAuthenticated, userLogout, handleItemClick} = useAuth()
  console.log(activeItem)
  const logout = () => {
    userLogout()
  }

  const enterMenuStyle = () => {
    return userIsAuthenticated() ? {"display": "none"} : {"display": "block"}
  }

  const logoutMenuStyle = () => {
    return userIsAuthenticated() ? {"display": "block"} : {"display": "none"}
  }

  const adminPageStyle = () => {
    const user = getUser()
    return user && user.data.rol[0] === 'ADMIN' ? {"display": "block"} : {"display": "none"}
  }

  const userPageStyle = () => {
    const user = getUser()
    return user && user.data.rol[0] === 'USER' ? {"display": "block"} : {"display": "none"}
  }

  const getUserName = () => {
    const user = getUser()
    return user ? user.data.name : ''
  }

  return (
    <Segment inverted style={{borderRadius: 0}}>
      <Menu inverted stackable secondary>
        <Container>
          <Menu.Item header>Movie-UI</Menu.Item>
          <Menu.Item as={Link} name={'home'} exact='true' to="/" onClick={handleItemClick}
                     active={activeItem === 'home'}>Home</Menu.Item>
          <Menu.Item as={Link} name={'adminPage'} to="/adminpage" onClick={handleItemClick}
                     active={activeItem === 'adminPage'}
                     style={adminPageStyle()}>AdminPage</Menu.Item>
          <Menu.Item as={Link} name={'userPage'} to="/userpage" onClick={handleItemClick}
                     active={activeItem === 'userPage'}
                     style={userPageStyle()}>Movies</Menu.Item>
          <Menu.Menu position='right'>
            <Menu.Item as={Link} to="/login" style={enterMenuStyle()}>Login</Menu.Item>
            <Menu.Item as={Link} to="/signup" style={enterMenuStyle()}>Sign Up</Menu.Item>
            <Menu.Item header style={logoutMenuStyle()}>{`Hi ${getUserName()}`}</Menu.Item>
            <Menu.Item as={Link} to="/" style={logoutMenuStyle()} onClick={logout}>Logout</Menu.Item>
          </Menu.Menu>
        </Container>
      </Menu>
    </Segment>
  )
}

export default Navbar
