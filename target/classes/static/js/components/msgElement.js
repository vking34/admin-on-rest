import React from 'react';

class MsgElement extends React.Component{

    render(){
        return(
            <div className="row">
                <div className="col-xs-3">User ID: {this.props.msg.mpUserId}</div>
                <div className="col-xs-3">FB Page ID: {this.props.msg.fbPageId}</div>
            </div>
        );
    }
}

module.exports = MsgElement;