import React from 'react';
import MsgElement from './msgElement';

class MsgList extends React.Component {

    render(){
        return(
            <div >
                {this.props.list !== null ? this.props.list.map((e, i) =>
                    <MsgElement key={i} msg={e}/>
                ) : null}
            </div>
        );
    }
}

module.exports = MsgList;