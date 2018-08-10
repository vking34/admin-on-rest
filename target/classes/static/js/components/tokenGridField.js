import React from 'react';

export default class AccessToken extends React.Component{

    render(){
        return(
            <div>
                <textarea readOnly={true} value={this.props.token ? this.props.token : `No Access Token`} style={{ width : '530px', height : '65px', display: 'block'}}></textarea>
            </div>
        );
    }
}