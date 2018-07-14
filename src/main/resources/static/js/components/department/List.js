var React = require('react');
var Department = require('./Department');
var Options = require('./Options');
import { getDepartments, createDepartment, removeDepartment } from "../../actions/actions";
import {connect} from 'react-redux';

class List extends React.Component{

    // constructor(props)
    // {
    //     super(props);
    //     this.remove = this.remove.bind(this);
    //     this.addDepartment = this.addDepartment.bind(this);
    // }

    componentDidMount(){
        this.props.getDepartments();
    }

    // remove(id, index) {
    //     this.props.removeDepartment(id, index);
    // }



    render(){
        return(
            <div>
                <div>{this.props.text}</div>
                <div className="row">
                    <div className="col-xs-1" style={{backgroundColor: "lavender"}}>Order</div>
                    <div className="col-xs-2" style={{backgroundColor: "lavenderblush"}}>Department ID</div>
                    <div className="col-xs-2" style={{backgroundColor: "lavender"}}>Title</div>
                    <div className="col-xs-3" style={{backgroundColor: "lavenderblush"}}>Sub-title</div>
                    <div className="col-xs-2" style={{backgroundColor: "lavender"}}>Number of members</div>
                    <div className="col-xs-1" ></div>
                    <div className="col-xs-1" ></div>
                </div>

                <div>
                    {this.props.departments.map((department, i) =>
                        <Department key={i} ord={i} info={department}  />
                    )
                    }
                </div>
                <Options />
            </div>
        );
    }
}

module.exports = connect(function (state) {
    return {
        departments: state.departments,
        text: state.text
    }
},
    {
        getDepartments
    })(List);
