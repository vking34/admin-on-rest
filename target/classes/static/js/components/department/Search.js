import React from 'react';

class Search extends React.Component{

    render(){
        return(
            <div className="row">
                <div className="col-xs-6"></div>
                <div className="col-xs-6">
                    <form action="/departments/search/" method="get">
                        <div className="input-group">
                            <input type="text" className="form-control" placeholder={this.props.text} name="title"
                                   autoComplete="off"/>
                            <div className="input-group-btn">
                                <button className="btn btn-default" type="submit"><i className="fas fa-search"></i>
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

module.exports = Search;