import React, { Component} from 'react';
import Form from 'react-bootstrap/Form'; //El <Form> seria el elemento contenedor pero no me deja especificar una URL
import Alert from 'react-bootstrap/Alert';
import {Row, Col, Button} from 'react-bootstrap';
import queryString from 'query-string'; //Vale para parsear el location search (los parametros de la URL)
import {Fragment} from 'react';


class LoginLogout extends Component {
	constructor(props){
		super(props);

	}
	
	
	render() {
		let alerta;
		if (this.props.error){
			alerta = <Alert variant="danger">Incorrect User/Password</Alert>;
		}
		console.log(this.props);
		return (
			<Fragment>
				{alerta}
			</Fragment>
		)
	}
}


export default LoginLogout;