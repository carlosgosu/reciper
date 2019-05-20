'use strict';

import React from 'react';
import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom'; //Una app que vaya en un browser debe importar react-router-dom en vez de react-router
import MenuAdmin from './admin/MenuAdmin';
import Login from './Login';

class Admin extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		//Los componentes JSX no se pueden devolver directamente en el return. Siempre tienen que estar dentro de algun contenedor (un <div> por ejemplo). Para saltarnos esa limitacion usamos <Fragment> o <Container> de react-bootstrap
		//Estamos pasando al componente Login el error que devuelve spring security en la autenticacion (lo devuelve como un parametro)
		//Vamos a usar un Route en vez de poner <Login /> para que en el componente Login pueda tener informacion de los parametros de la URL (el error de spring sec)
		return (
			<Switch>
				<Route exact path="/admin" component={MenuAdmin} />
				<Route exact path="/autenticacion" component={Login} />
			</Switch>
		)
	}
}