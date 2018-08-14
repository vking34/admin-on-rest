import React from 'react';

export default class AccessToken extends React.Component{

    render(){
        return(
            <div>
                <textarea readOnly={true} value={this.props.token ? this.props.token : `Access Token is not available`} style={{ width : '530px', height : '65px', display: 'block'}}></textarea>
            </div>
        );
    }
}