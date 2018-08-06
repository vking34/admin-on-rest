import React from 'react';
import Chip from 'material-ui/Chip';

const styles = {
    main: { display: 'flex', flexWrap: 'wrap' },
    chip: { margin: 4 },
};


const PagesField = ({ record }) => (
    <span style={styles.main}>
        {record.pagesName.map(p => (
            <Chip key={p} style={styles.chip}>
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