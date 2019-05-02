'use strict';

// tag::vars[]
//Forma antigua con require. Forma nueva con import
const React = require('react');
const ReactDOM = require('react-dom');
import {Fragment} from 'react';
import Login from './Login';

// end::vars[]

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		//Los componentes JSX no se pueden devolver directamente en el return. Siempre tienen que estar dentro de algun contenedor (un <div> por ejemplo). Para saltarnos esa limitacion usamos Fragment
		return (
			<Fragment>
			<div class="hola">
				Soy la pagina de inicio de prueba
				<Login/>
            </div>
            </Fragment>
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