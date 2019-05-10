'use strict';

//Que este es el punto de entrada a React se define en el webpack.config.json
// tag::vars[]
//import 'bootstrap/dist/css/bootstrap.min.css'; <- Lo hemos metido en el head de index.html Da problemas al construirlo si lo meto en el package.json
//Forma antigua con require. Forma nueva con import
const React = require('react');
const ReactDOM = require('react-dom');
import {Fragment} from 'react';
import Login from './Login';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom'; //Una app que vaya en un browser debe importar react-router-dom en vez de react-router

// end::vars[]

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		//Los componentes JSX no se pueden devolver directamente en el return. Siempre tienen que estar dentro de algun contenedor (un <div> por ejemplo). Para saltarnos esa limitacion usamos <Fragment> o <Container> de react-bootstrap
		//Estamos pasando al componente Login el error que devuelve spring security en la autenticacion (lo devuelve como un parametro)
		//Vamos a usar un Route en vez de poner <Login /> para que en el componente Login pueda tener informacion de los parametros de la URL (el error de spring sec)
		return (
			<Router>
			<Container>
				<Row>
					<Col xs sm md lg={{ span: 6, offset: 3 }}>
						<Route path="/" component={Login} />
					</Col>
				</Row>
			</Container>
			</Router>
		)
	}
}
// end::app[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]