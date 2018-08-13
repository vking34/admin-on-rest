import React from 'react';

class AccessToken extends React.Component{

    render(){
        return(
            <div >
                <br/>
                <span style={{ fontSize : '13px'}}>User Access Token:</span>
                <br/>
                <textarea readOnly={true} value={this.props.token ? this.props.token : `Access Token is not available`} style={{ width : '600px', height : '60px', display: 'block'}}></textarea>
            </div>
        );
    }
}

module.exports = AccessToken;