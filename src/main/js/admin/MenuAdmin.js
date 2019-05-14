import React from 'react';
import {Fragment} from 'react';
import Alert from 'react-bootstrap/Tabs';

class MenuAdmin extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
				<Tab eventKey="home" title="Home">
				    Hola
				 </Tab>
				 <Tab eventKey="profile" title="Profile">
				    Adios
				 </Tab>
				 <Tab eventKey="contact" title="Contact" disabled>
				    Puede
				 </Tab>
			</Tabs>
		)
	}
}

export default MenuAdmin;