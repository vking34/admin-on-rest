import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { refreshView, showNotification as showNotificationAction } from 'admin-on-rest';
import ActionDelete from 'material-ui/svg-icons/action/delete';
import FlatButton from 'material-ui/FlatButton';
// import translate from 'admin-on-rest/src/i18n/translate';


class InnerDeleteButton extends Component {

    handleClick = () => {

        const { push, record, showNotification, refreshView } = this.props;
        console.log(this.props);

        fetch(`/${this.props.channel}/${record.parent}/${this.props.target}/${record.id}`,
            {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type' : 'application/json',
                    'Authorization' : 'Bearer ' + localStorage.getItem("token")
                }
            })
            .then(resp => resp.json())
            .then((resp) => {
                if(resp.status === true){
                    showNotification('Deleted', 'warning');
                    // alert('Deleted');
                    // push(`/bizweb-stores/${record.parent}`);
                    refreshView();
                }
                else {
                    // alert('Cant delete');
                    showNotification('Cant delete', 'warning');
                }
            });

    };

    render() {
        return <FlatButton
            secondary
            label="Delete"
            icon={<ActionDelete />}
            onClick={this.handleClick}
            style={{ overflow: 'inherit' }}
        />;
    }
}

InnerDeleteButton.propTypes = {
    record: PropTypes.object,
    showNotification: PropTypes.func,
    channel: PropTypes.string,
    target: PropTypes.string
};

export default connect(null, {
    showNotification: showNotificationAction,
    refreshView: refreshView
})(InnerDeleteButton);

