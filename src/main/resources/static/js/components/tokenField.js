import React from 'react';

class AccessToken extends React.Component{

    val = 'This is the token field';

    render(){
        return(
            <div >
                <span>User Access Token:</span>
                <br/>
                <textarea readOnly={true} value={this.props.token ? this.props.token : this.val} style={{ width : '600px', height : '60px', display: 'block'}}></textarea>
            </div>
        );
    }
}

module.exports = AccessToken;