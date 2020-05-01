import React, { Component } from 'react'
import { NavLink, Redirect } from 'react-router-dom'
import { Button, Form, Grid, Segment, Message } from 'semantic-ui-react'
import AuthContext from '../context/AuthContext'
import { movieApi } from '../misc/MovieApi'

class Signup extends Component {
  static contextType = AuthContext

  state = {
    username: '',
    password: '',
    name: '',
    email: '',
    isLoggedIn: false,
    isError: false,
    errorMessage: ''
  }

  componentDidMount() {
    const Auth = this.context
    const isLoggedIn = Auth.userIsAuthenticated()
    this.setState({ isLoggedIn })
  }

  handleChange = (e) => {
    const { id, value } = e.target
    this.setState({ [id]: value })
  }

  handleSubmit = (e) => {
    e.preventDefault()

    const { username, password, name, email } = this.state
    if (!(username && password && name && email)) {
      this.setState({
        isError: true,
        errorMessage: 'Please, inform all fields!'
      })
      return
    }

    const user = { username, password, name, email }
    movieApi.signup(user)
      .then(response => {
        if (response.status === 201) {
          const { id, name, role, accessToken } = response.data
          const user = { id, name, role, accessToken }

          const Auth = this.context
          Auth.userLogin(user)

          this.setState({
            username: '',
            password: '',
            isLoggedIn: true,
            isError: false,
            errorMessage: ''
          })
        }
      })
      .catch(error => {
        if (error.response && error.response.data) {
          const errorData = error.response.data
          console.log(errorData)
          let errorMessage = 'Invalid fields'
          if (errorData.status === 409) {
            errorMessage = errorData.message
          } else if (errorData.status === 400) {
            errorMessage = errorData.errors[0].defaultMessage
          }
          this.setState({
            isError: true,
            errorMessage
          })
        }
      })
  }

  render() {
    const { isLoggedIn, isError, errorMessage } = this.state
    if (isLoggedIn) {
      return <Redirect to='/' />
    } else {
      return (
        <Grid textAlign='center'>
          <Grid.Column style={{ maxWidth: 450 }}>
            <Form size='large' onSubmit={this.handleSubmit}>
              <Segment>
                <Form.Input
                  fluid
                  autoFocus
                  id='username'
                  icon='user'
                  iconPosition='left'
                  placeholder='Username'
                  onChange={this.handleChange}
                />
                <Form.Input
                  fluid
                  id='password'
                  icon='lock'
                  iconPosition='left'
                  placeholder='Password'
                  type='password'
                  onChange={this.handleChange}
                />
                <Form.Input
                  fluid
                  id='name'
                  icon='address card'
                  iconPosition='left'
                  placeholder='Name'
                  onChange={this.handleChange}
                />
                <Form.Input
                  fluid
                  id='email'
                  icon='at'
                  iconPosition='left'
                  placeholder='Email'
                  onChange={this.handleChange}
                />
                <Button color='purple' fluid size='large'>Signup</Button>
              </Segment>
            </Form>
            <Message>{`Already have an account? `}
              <a href='/login' color='purple' as={NavLink} to="/login">Login</a>
            </Message>
            {isError && <Message negative>{errorMessage}</Message>}
          </Grid.Column>
        </Grid>
      )
    }
  }
}

export default Signup