import React, { Component} from 'react';
import Form from 'react-bootstrap/Form'; //El <Form> seria el elemento contenedor pero no me deja especificar una URL
import Alert from 'react-bootstrap/Alert';
import {Row, Col, Button} from 'react-bootstrap';
import queryString from 'query-string'; //Vale para parsear el location search (los parametros de la URL)
import {Fragment} from 'react';
import LoginLogout from './LoginLogout'

class Login extends Component {
	constructor(props){
		super(props);
		
		this.state = {
			error: false,
			errorDesc: ''
		}
		
		//Guarda el elemento DOM form del login para poder hacer referencia a el (hay que usar su propiedad current)
		this.loginForm = React.createRef();
		this.handleSubmit = this.handleSubmit.bind(this);
	}
	
	//Esta funcion se ejecuta despues de que el componente se ha renderizado
	componentDidMount(){
		const urlParams = queryString.parse(this.props.location.search); //el queryString.parse devuelve un objeto que de prototype tiene null => no tenemos metodo hasOwnProperty...
		const hayError = (typeof urlParams.error) !== "undefined"; //Transformamos el undefined a Boolean es un false, cualquier otrao valor es true (no 0)
		this.setState({
			error: hayError,
			errorDesc: urlParams.error? urlParams:''
		});
	}
	
	//Otra forma de hacerlo (compatible con navegadores anteriores poniendo en el package.json el "babel" @babel/plugin-proposal-class-properties).
	//Seria haciendo: handleSubmit = (event) => { ... }
	handleSubmit(event){
		event.preventDefault();
		
		//FormData y URLSearchParams son interfaces de ECMA6. FormData construye un conjunto de pares clave/valor que representan campos (si se le pasa un form introduce sus inputs)
		//URLSearchParams se usa para trabajar con los parametros de una URL
		let data = new FormData(this.loginForm.current);
		
		const ajaxParams = {
			method: 'POST',
			body: new URLSearchParams(data),
			//mode: 'cors',
			//credentials: 'include'   //Para producir que los navegadores envien una peticion con las credenciales incluidas
			//cache: 'default'
		}

		//fetch es de ESCMA6 y equivale al $.ajax() de JQuery. Se le envia la URL o un objeto Request (new Request). Devuelve un objeto de que implementa Response (es una Promise). Solo falla si hay error de red => Hay que comprobar que el resultado es satisfactorio
		fetch(this.loginForm.current.action, ajaxParams)
		.then( (response) => {
				if (response.redirected){ 
					//La respuesta de spring security es una redireccion (302)
					window.location = response.url;
				}
			})
		.catch(e => console.warn(e));
		
	}
	
	render() {
		let alerta;
		if (this.state.error){
			alerta = <Alert variant="danger">Incorrect User/Password</Alert>;
		}
		return (
			<Fragment>
			<div className="panel panel-default">
				<form onSubmit={this.handleSubmit} action="/reciper/login" ref={this.loginForm} id="formLoginId">
					<Form.Group as={Row} controlId="formLogin">
						<Form.Label column xs sm md lg={2}>User</Form.Label>
						<Col sm={10}>
						<Form.Control name="username" type="text" placeholder="Login" />
						</Col>		
					</Form.Group>
					<Form.Group as={Row} controlId="formPassword">
						<Form.Label column xs sm md lg={2}>Password</Form.Label>
						<Col sm={10}>
						<Form.Control name="password" type="password" placeholder="Password" />
						</Col>
					</Form.Group>
					<Row>
						<Col xs sm md lg={3}>
							<Button variant="outline-primary" size="lg" type="submit" block>Log in</Button>
						</Col>
						<Col xs sm md lg={9}>
							<LoginLogout error={alerta} />
						</Col>
					</Row>
				</form>
				
			</div>
			</Fragment>
		)
	}
}


export default Login;