'use strict';

// tag::vars[]
//import 'bootstrap/dist/css/bootstrap.min.css'; <- Lo hemos metido en el head de index.html Da problemas al construirlo si lo meto en el package.json
//Forma antigua con require. Forma nueva con import
const React = require('react');
const ReactDOM = require('react-dom');
import {Fragment} from 'react';
import Login from './Login';

import {Container, Row, Col} from 'react-bootstrap';


// end::vars[]

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		//Los componentes JSX no se pueden devolver directamente en el return. Siempre tienen que estar dentro de algun contenedor (un <div> por ejemplo). Para saltarnos esa limitacion usamos <Fragment> o <Container> de react-bootstrap
		return (
			<Container>
				<Row>
					<Col xs sm md lg={{ span: 6, offset: 3 }}>
						<Login />
					</Col>
				</Row>
			</Container>
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