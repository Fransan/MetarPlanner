import React  from 'react';
import * as ReactDOM from 'react-dom';
import MetarForm from './components/MetarForm';

class App extends React.Component { 

	constructor(props) {
		super(props);
	}

	render() { 
		return (
			<MetarForm />
		)
	}
}


ReactDOM.render(
	<App />,
	document.getElementById('react')
)