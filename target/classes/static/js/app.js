var React = require('react');
var ReactDOM = require('react-dom');
var {Provider} = require('react-redux');
var List = require('./components/department/List');
var Title = require('./components/department/Title');
var Search = require('./components/department/Search');
var store = require('./store/department_store');



ReactDOM.render(
    <div className="container">
        <Title>Department List</Title>
        <br/>

            <Search text="Search by title"/>
        <Provider store={store}>
            <List/>
        </Provider>
    </div>,
    document.getElementById('root')
);

// require('./store/redux_store');