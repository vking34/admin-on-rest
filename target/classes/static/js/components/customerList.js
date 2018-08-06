import React from "react";
import CustomerElement from "./customerElement";

class CustomerList extends React.Component {

    render(){
        return(
            <div >
                {
                    this.props.customers !== null ?
                    this.props.customers.map((e, i) => <CustomerElement key={i} customer={e}/>)
                    : null
                }
            </div>
        );
    }
}

module.exports = CustomerList;