var defaultText = "Department List";

var editText = (state = defaultText, action) => {
    switch (action.type)
    {
        case 'EDIT_TITLE':
            return state;
        default:
            return state;
    }
};

module.exports = editText;