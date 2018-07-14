
var defaultArr = [];

var editDepartment = (state = defaultArr, action) => {

    switch (action.type){
        case 'INIT':
            return [...state, ...action.departments];
        case 'ADD_DEPARTMENT':
            return [...state, action.new_department];
        case 'REMOVE_DEPARTMENT':
            return state.filter((d, i) => i != action.index);
        case 'EDIT_DEPARTMENT':
            console.log("reducer: editing " + action.index);
            // return state.map((e, i) => {
            //    if(i == action.index){
            //        return Object.assign({}, e, {title: action.department.title, sub_title: action.department.sub_title});
            //    }
            //    return e;
            // });
            let i = -1;
            const updatedState = state.map(d =>
            {
                // if(d.department_id === action.id){
                //     return { ...d, ...action.department};
                // }
                // return d;
                i++;
                if(i === action.index){
                    return { ...d, ...action.department};
                }
                return d;
            });

            return updatedState;

        case 'DO_NOTHING':
            return state;
        default:
            return state;
    }
};

module.exports = editDepartment;