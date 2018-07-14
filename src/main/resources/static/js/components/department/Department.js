var React = require('react');
import { editDepartment ,removeDepartment } from "../../actions/actions";
import {connect} from 'react-redux';

class Department extends React.Component{

    edit(){
        let department = {};
        department.title = $("#newTitle_edit" + this.props.ord).val();
        department.sub_title = $("#newSubTitle_edit" + this.props.ord).val();

        console.log(department);

        this.props.editDepartment(this.props.info.department_id, department, this.props.ord);
    }

    render(){
        return(
            <div className="row">
                <div className="col-xs-1" style={{backgroundColor: "lavender"}}>{this.props.ord}</div>
                <div className="col-xs-2" style={{backgroundColor: "lavenderblush"}}>{this.props.info.department_id}</div>
                <div className="col-xs-2" style={{backgroundColor: "lavender"}}>{this.props.info.title}</div>
                <div className="col-xs-3" style={{backgroundColor: "lavenderblush"}}>{this.props.info.sub_title}</div>
                <div className="col-xs-2" style={{backgroundColor: "lavender"}}>{this.props.info.numOfEmployee}</div>
                <div className="col-xs-1" >
                    <button className="btn btn-default" type="button" data-toggle="modal" data-target={"#myModal" + this.props.ord}><i className="fas fa-edit"></i></button>

                    <div className="modal fade" id={"myModal" + this.props.ord} tabIndex="-1" role="dialog"
                         aria-labelledby="myModalLabel">
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span></button>
                                    <h4 className="modal-title" id="myModalLabel1">Edit Department {this.props.info.department_id}</h4>
                                </div>
                                <div className="modal-body">

                                    <div className="input-group">
                                        <span className="input-group-addon" >Title</span>
                                        <input id={"newTitle_edit" + this.props.ord} type="text" className="form-control"
                                               placeholder="Enter new title" aria-describedby="basic-addon1"/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon" >Sub-Title</span>
                                        <input id={"newSubTitle_edit" + this.props.ord} type="text" className="form-control"
                                               placeholder="Enter Sub title" aria-describedby="basic-addon1"/>
                                    </div>

                                </div>
                                <div className="modal-footer">
                                    <button id={"close_edit" + this.props.ord } type="button" className="btn btn-default"
                                            data-dismiss="modal">Close
                                    </button>
                                    <button id={"submit_edit_btn" + this.props.ord } type="button" className="btn btn-primary"
                                            onClick={() => this.edit()}>Edit Department
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div className="col-xs-1">
                    <button className="btn btn-default" type="submit" onClick={() => {this.props.removeDepartment(this.props.info.department_id, this.props.ord)}}><i className="fas fa-trash-alt"></i></button>
                </div>
            </div>
        );
    }
}


const mapStateToProps = state => state;

module.exports = connect(mapStateToProps, {
    editDepartment,
    removeDepartment
} )(Department);