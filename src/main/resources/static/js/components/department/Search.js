import React from 'react';
import { connect } from 'react-redux';

class Search extends React.Component{

    search(){
    }

    render(){
        return(
            <div className="row">
                <div className="col-xs-6"></div>
                <div className="col-xs-6">
                    <form>
                        <div className="input-group">
                            <input type="text" className="form-control" placeholder="Search by title" name="title"
                                   autoComplete="off" ref="title" />
                            <div className="input-group-btn">
                                <button className="btn btn-default" type="button" onClick={() => this.search()}><i className="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <br/>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => state;

module.exports = connect(mapStateToProps)(Search);