import React from 'react';

class Note extends React.Component{
    render(){
        return(
          <div>
              <p>{this.props.children}</p>
              <button>x</button>
          </div>
        );
    }
}

module.exports = Note;
