var React = require('react');

import { createDepartment } from "../../actions/actions";

import {connect} from 'react-redux';

class Options extends React.Component{

    addDepartment(){
        let department = {};
        department.department_id = $('#newID_add').val();
        department.title = $("#newTitle_add").val();
        department.sub_title = $("#newSubTitle_add").val();
        department.numOfEmployee = 0;

        this.props.createDepartment(department);
    }

    render(){
        return(
            <div className="row">
                <div className="col-xs-4">
                    <button id="add_department_btn" type="button" className="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" ><i className="fas fa-plus-circle"></i>  Add Department
                    </button>

                    <div className="modal fade" id="myModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                    <h4 className="modal-title" id="myModalLabel">Add Department</h4>
                                </div>
                                <div className="modal-body">

                                    <div className="input-group">
                                        <span className="input-group-addon" id="basic-addon1_add">Department ID</span>
                                        <input ref="id_add" id="newID_add" type="number" className="form-control"
                                               placeholder="Enter Department ID" aria-describedby="basic-addon1"/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon" id="basic-addon2_add">Title</span>
                                        <input ref="title_add" id="newTitle_add" type="text" className="form-control"
                                               placeholder="Enter new title" aria-describedby="basic-addon1"/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon" id="basic-addon3_add">Sub-Title</span>
                                        <input ref="subtitle_add" id="newSubTitle_add" type="text" className="form-control"
                                               placeholder="Enter Sub title" aria-describedby="basic-addon1"/>
                                    </div>

                                </div>
                                <div className="modal-footer">
                                    <button id="close_add" type="button" className="btn btn-default"
                                            data-dismiss="modal">Close
                                    </button>
                                    <button id="submit_add_btn" type="button" className="btn btn-primary"
                                            onClick={() => this.addDepartment()}>Add Department
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => state;

module.exports = connect(mapStateToProps, {
    createDepartment
})(Options);
