var React = require('react');

class Title extends React.Component{
    render(){
        return(
            <div>
                <h1>{this.props.children}</h1>
            </div>
        );
    }
}

module.exports = Title;