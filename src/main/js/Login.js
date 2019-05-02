import React, { Component } from 'react';

class Login extends Component {
	constructor(props){
		super(props);
		this.state = {
			value: props.value? props.value : '',
			error: false
		}
	}
	
	render () {
		return (
			<form name="loginForm" method="POST" action="perform_login">
				<input name="username" type="text"/>
				<input name="password" type="password"/>
				<input name="submit" type="submit" value="Login"/>
			</form>
		)
	}
}


export default Login;