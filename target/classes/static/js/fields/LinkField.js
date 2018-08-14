import React from 'react';

class LinkField extends React.Component{

    render(){
        return(
            <a href={`#/${this.props.channel}/${this.props.id}`}>
                {this.props.id}
            </a>
        );
    }
}

module.exports = LinkField;