import React from 'react';

class AccessToken extends React.Component{

    render(){
        return(
            <div style={{ width : '80px', backgroundColor: 'yellow' }}>
                <span>User Access Token:</span>
                <br/>
                <span style={{ width : '60px'}}>{this.props.token}</span>
            </div>
        );
    }
}

module.exports = AccessToken;