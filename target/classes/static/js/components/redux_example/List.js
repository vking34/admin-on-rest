import React from 'react';
import Note from './Note';

import {connect} from 'react-redux';


class List extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            arr: ['Android', 'iOS', 'NodeJS']
        }
    }

    render(){
        return(
          <div>
              {this.props.arr.map((e,i) =>
                  <Note key={i} >{e}</Note>
              )}
          </div>
        );
    }
}

module.exports = connect(function (state) {
    return {
        arr : state.arr
    };
})(List);
