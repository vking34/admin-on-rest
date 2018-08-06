import React from "react";

class CustomerElement extends React.Component {

    render(){
        return (
            <div className="row">
                <div className="col-xs-3">Page ID: {this.props.customer.pageId}</div>
                <div className="col-xs-3">Customer ID: {this.props.customer.bizwebCustomerId}</div>
            </div>
        );
    }
}

module.exports = CustomerElement;