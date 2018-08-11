import React from 'react';
import Chip from 'material-ui/Chip';
import ChipStyle from "../css/FieldStyle";

const ChannelsField = ({ record }) => (
    <span style={ChipStyle.main}>
        {record.bwChannels.split(',').map(p => (
            <Chip key={p} style={ChipStyle.chip}>
                {p}
            </Chip>
        ))
        }
    </span>
);

ChannelsField.defaultProps = {
    addLabel: true,
    source: 'bwChannels',
};

export default ChannelsField;