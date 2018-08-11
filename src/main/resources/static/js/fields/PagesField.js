import React from 'react';
import Chip from 'material-ui/Chip';
import ChipStyle from "../css/FieldStyle";


const PagesField = ({ record }) => (
    <span style={ChipStyle.main}>
        {record.pagesName.map(p => (
            <Chip key={p} style={ChipStyle.chip}>
                {p}
            </Chip>
        ))}
    </span>
);

PagesField.defaultProps = {
    addLabel: true,
    source: 'pagesName',
};

export default PagesField;